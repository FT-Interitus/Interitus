/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager.userdata.load;

import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Block.Saving.SaveBlockV1;
import de.ft.interitus.Program;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.UI.MenuBar;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.BlockCalculator;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.datamanager.userdata.Zip;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectType;
import de.ft.interitus.projecttypes.VCS;
import de.ft.interitus.utils.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    private static final ArrayList<Addon> enabledAddons = new ArrayList<>();
    private static boolean wascreated = false;
   private static Gson gson = new Gson();

    public static void load(final FileHandle handle, final String name, final String path) {

        Thread laden = new Thread() {
            public void run() {


                try {
                    Zip.unzip(handle.file().getAbsolutePath(), Data.tempfolder.getAbsolutePath());
                    FileHandle file = new FileHandle(Data.tempfolder.getAbsolutePath() + "/" + "Program.itid");
                    FileHandle settingsfile = new FileHandle(Data.tempfolder.getAbsolutePath() + "/" + "Settings.itps");
                    FileHandle runconfig = new FileHandle(Data.tempfolder.getAbsolutePath() + "/" + "RunConfig.itrc");
                    FileHandle projectsettingsfile = new FileHandle(Data.tempfolder.getAbsolutePath() + "/" + "ProjectSettings.itps");
                    JSONObject settings = new JSONObject(settingsfile.readString());


                    ProjectType temptype = null;
                    String typetemp = settings.getString("type");
                    for (int i = 0; i < ProjectTypesVar.projectTypes.size(); i++) {
                        if (ProjectTypesVar.projectTypes.get(i).getName().contains(typetemp)) {
                            temptype = ProjectTypesVar.projectTypes.get(i);
                        }
                    }


                    if (temptype == null) {
                        Dialogs.showErrorDialog(UI.stage, "Das Plugin mit dem Namen \"" + settings.getString("pl_name") + "\", das mit diesem Projekt verbunden ist, ist nicht installiert.");
                        Var.isloading = false;
                        this.interrupt();
                        return;
                    } else {
                        enabledAddons.clear();
                        boolean found;
                        String tempaddonname = "";
                        String temppluginname = "";
                        for (Object addonname : settings.getJSONArray("addons")) {
                            found = false;
                            for (Addon addon : ProjectTypesVar.addons) {
                                tempaddonname = ((JSONObject) addonname).getString("addon_name");
                                temppluginname = ((JSONObject) addonname).getString("pl_name");
                                if (((JSONObject) addonname).getString("addon_name").contentEquals(addon.getName())) {
                                    enabledAddons.add(addon);
                                    FileHandle addonconfig = new FileHandle(Data.tempfolder.getAbsolutePath() + "/" + "" + addon.getName() + ".ita");

                                    try {
                                        addon.setAddonSettings(gson.fromJson(Files.readString(addonconfig.file().toPath(),StandardCharsets.UTF_8),Object.class));

                                    }catch (Exception e) {
                                        addon.setAddonSettings(null);
                                    }

                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                Dialogs.showErrorDialog(UI.stage, "Das verbundenes Addon " + tempaddonname + " vom Plugin " + temppluginname + " konnte nicht gefunden werden!");
                                Var.isloading = false;
                                this.interrupt();
                                return;
                            }

                        }


                        //ThreadManager.stopall();

                        if (settings.getDouble("it_version") != Var.PROGRAMM_VERSION_ID) {
                            NotificationManager.sendNotification(new Notification(AssetLoader.information, "Warnung", "Das Project stammt aus\neiner anderen Interitus Version"));
                        }

                        if (settings.getDouble("pl_version") != ((double) PluginManagerHandler.getPluginArgs(temptype.getPluginRegister(), "version"))) {
                            NotificationManager.sendNotification(new Notification(AssetLoader.information, "Warnung", "Das Project stammt aus\neiner anderen Projekt-Type Version"));
                        }


                        ProjectManager.addProject(temptype.init());
                        wascreated = true;
                        Var.openprojects.get(Var.openprojects.size() - 1).zoom = settings.getFloat("zoom"); //Befor change to not ignore this change
                        Var.openprojects.get(Var.openprojects.size() - 1).cam_pos.set(settings.getFloat("pos_x"), settings.getFloat("pos_y"));


                        ProjectManager.change(Var.openprojects.size() - 1);


                        ProjectManager.getActProjectVar().setFilename(name);
                        ProjectManager.getActProjectVar().path = path;
                        try {

                          //  FileInputStream projectsettings_fis = new FileInputStream(projectsettingsfile.file());


                                ProjectManager.getActProjectVar().projectSettings = gson.fromJson(Files.readString(projectsettingsfile.file().toPath(),StandardCharsets.UTF_8), Object.class);

                        } catch (Exception ignored) {
                            Program.logger.warning("ProjectType has no saveable Settings");


                        }

                        ProjectManager.getActProjectVar().enabledAddons.addAll(enabledAddons);

                        BlockTappedBar.init();


                        ProjectManager.getActProjectVar().vcs = settings.getInt("vcs");
                        ProjectManager.getActProjectVar().programmingtime = settings.getLong("time");


                        if (ProjectManager.getActProjectVar().vcs == VCS.NONE) {
                            MenuBar.menuItem_speichern.setText("Speichern");
                        } else if (ProjectManager.getActProjectVar().vcs == VCS.ITEV) {
                            MenuBar.menuItem_speichern.setText("Revision speichern");
                        }




                        List<SaveBlockV1> readedblocks = null;




                        try {
                            readedblocks =  stringToArray( Files.readString(file.file().toPath(),StandardCharsets.UTF_8),SaveBlockV1[].class);
                        } catch (Exception f) {
                            f.printStackTrace();

                                Dialogs.showErrorDialog(UI.stage, "Das Projekt kann in dieser Interitus Version nicht geöffnet werden!");


                        }
                      ProjectManager.getActProjectVar().blocks=BlockCalculator.extractV1(new ArrayList<SaveBlockV1>(readedblocks));

                        ProjectManager.getActProjectVar().deviceConfigurations =  gson.fromJson(Files.readString(runconfig.file().toPath(),StandardCharsets.UTF_8),new ArrayList<DeviceConfiguration>().getClass());;

                    }
                } catch (JSONException g) {
                    g.printStackTrace();
                    try {
                        if (wascreated) {

                            ProjectManager.closeProject(Var.openprojects.size()-1);

                        }
                        Dialogs.showErrorDialog(UI.stage, "Fehler beim Laden des Projekts\nDie Projekt Datei ist womöglich beschädigt!");
                    }catch (Exception f) {
                        f.printStackTrace();
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    try {
                        if (wascreated) {
                            ProjectManager.closeProject(Var.openprojects.size()-1);
                        }
                        Dialogs.showErrorDialog(UI.stage, "Fehler beim Laden des Projekts\nDie Projekt Datei ist womöglich beschädigt!");
                    }catch (Exception f) {
                        f.printStackTrace();
                    }

                }



                File tempblockfile = new File(Data.tempfolder + "/" + "Program.itid");
                tempblockfile.delete();


                Var.isloading = false;

            }

        };

        Var.isloading = true;
        laden.start();


    }

    private static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }

}
