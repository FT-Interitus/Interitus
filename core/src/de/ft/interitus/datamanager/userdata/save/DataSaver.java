/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager.userdata.save;

import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import de.ft.interitus.Block.Saving.SaveBlockV1;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.BlockCalculator;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.datamanager.userdata.Zip;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.utils.ArrayList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataSaver {

    static public void save(final FileHandle handle) {
        save(handle, ProjectManager.getActProjectVar());

    }


    static public void save(final FileHandle handle, final ProjectVar projectVar) {


        Thread speichern = new Thread() {
            public void run() {


                ArrayList<SaveBlockV1> saveBlocks = BlockCalculator.save(projectVar);


                String generateprojektname = "project" + System.currentTimeMillis();
                String generateprojektsettingsname = "projectstates" + System.currentTimeMillis();
                String generaterunconfigurationen = "runconfig" + System.currentTimeMillis();
                String generateprojectsettings = "projectsettings" + System.currentTimeMillis();
                Gson gson = new Gson();


                try {
                    FileOutputStream fos = new FileOutputStream(Data.tempfolder + "/" + generateprojektname);
                    PrintWriter printWriter = new PrintWriter(fos);
                    printWriter.print(gson.toJson(saveBlocks));
                    printWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    FileOutputStream fos = new FileOutputStream(Data.tempfolder + "/" + generaterunconfigurationen);
                    PrintWriter printWriter = new PrintWriter(fos);
                    printWriter.print(gson.toJson(projectVar.deviceConfigurations));
                    printWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    FileOutputStream fos = new FileOutputStream(Data.tempfolder + "/" + generateprojectsettings);
                    PrintWriter printWriter = new PrintWriter(fos);
                    printWriter.print(gson.toJson(projectVar.projectSettings));
                    printWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                for (Addon addon : projectVar.enabledAddons) {


                    try {
                        FileOutputStream fos = new FileOutputStream(Data.tempfolder + "/" + addon.getName());

                        PrintWriter printWriter = new PrintWriter(fos);
                        printWriter.print(gson.toJson(addon.getAddonSettings()));
                        printWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                JSONObject settings = new JSONObject();
                settings.put("vcs", projectVar.vcs);
                settings.put("type", projectVar.projectType.getName());
                settings.put("zoom", projectVar.zoom);
                settings.put("pos_x", projectVar.cam_pos.x);
                settings.put("pos_y", projectVar.cam_pos.y);
                settings.put("time", projectVar.programmingtime + (System.currentTimeMillis() - projectVar.currentstarttime));
                settings.put("it_version", Var.PROGRAMM_VERSION_ID); //Saves Interitus Version in Project File
                settings.put("pl_name", PluginManagerHandler.getPluginArgs(projectVar.projectType.getPluginRegister(), "name"));
                settings.put("pl_version", ((double) PluginManagerHandler.getPluginArgs(projectVar.projectType.getPluginRegister(), "version")));
                ArrayList<JSONObject> addons = new ArrayList<>();
                for (int i = 0; i < projectVar.enabledAddons.size(); i++) {
                    JSONObject tempjson = new JSONObject();
                    tempjson.put("pl_name", PluginManagerHandler.getPluginArgs(projectVar.enabledAddons.get(i).getPlugin(), "name").toString());
                    tempjson.put("addon_name", projectVar.enabledAddons.get(i).getName());

                    addons.add(tempjson);
                }
                settings.put("addons", addons);
                try {
                    Files.write(Paths.get(Data.tempfolder + "/" + generateprojektsettingsname), settings.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {


                    ArrayList<String> names = new ArrayList<>();
                    names.add("Program.itid");
                    names.add("Settings.itps");
                    names.add("RunConfig.itrc");
                    names.add("ProjectSettings.itps");
                    for (Addon addon : projectVar.enabledAddons) {
                        names.add(addon.getName() + ".ita");
                    }

                    String[] addonnames = new String[projectVar.enabledAddons.size() + 4];
                    addonnames[0] = Data.tempfolder + "/" + generateprojektname;
                    addonnames[1] = Data.tempfolder + "/" + generateprojektsettingsname;
                    addonnames[2] = Data.tempfolder + "/" + generaterunconfigurationen;
                    addonnames[3] = Data.tempfolder + "/" + generateprojectsettings;

                    for (int i = 0; i < projectVar.enabledAddons.size(); i++) {
                        addonnames[i + 4] = Data.tempfolder + "/" + projectVar.enabledAddons.get(i).getName();
                    }

                    Zip.zipFiles(names, handle.file().getAbsolutePath(), addonnames);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                File tempblockfile = new File(Data.tempfolder + "/" + generateprojektname);
                tempblockfile.delete();


                projectVar.changes = false;


            }


        };


        speichern.start();

    }
}
