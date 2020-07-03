package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.UI.MenuBar;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.Var;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.VCS;
import de.ft.interitus.projecttypes.device.BlockTypes.ProjectTypesVar;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DataLoader {
    public static void load(final FileHandle handle, final String name, final String path) {


        Thread laden = new Thread() {
            public void run() {


                try {
                    Zip.unzip(handle.file().getAbsolutePath(), Data.tempfolder.getAbsolutePath());
                    FileHandle file = new FileHandle(Data.tempfolder.getAbsolutePath() + "/" + "Program.itid");
                    FileHandle settingsfile = new FileHandle(Data.tempfolder.getAbsolutePath() + "/" + "Settings.itps");
                    JSONObject settings = new JSONObject(settingsfile.readString());


                    ProjectTypes temptype = null;
                    String typetemp = settings.getString("type");
                    for (int i = 0; i < ProjectTypesVar.projectTypes.size(); i++) {
                        if (ProjectTypesVar.projectTypes.get(i).getName().contains(typetemp)) {
                            temptype = ProjectTypesVar.projectTypes.get(i);
                        }
                    }


                    if (temptype == null) {

                        Dialogs.showErrorDialog(UI.stage, "Das Plugin das mit diesem Projekt verbunden ist, ist nicht installiert."); //TODO more Informations (link to plugin)

                        this.interrupt();
                        return;
                    } else {
                        //ThreadManager.stopall();
                        Var.openprojects.add(temptype.init());

                        Var.openprojects.get(Var.openprojects.size() - 1).zoom = settings.getFloat("zoom"); //Befor change to not ignore this change
                        Var.openprojects.get(Var.openprojects.size() - 1).cam_pos.set(settings.getFloat("pos_x"), settings.getFloat("pos_y"));

                        ProjectManager.change(Var.openprojects.size() - 1);


                        ProjectManager.getActProjectVar().filename = name;
                        ProjectManager.getActProjectVar().path = path;

                        BlockTappedBar.init();



                    }

                    ProjectManager.getActProjectVar().vcs = settings.getInt("vcs");
                    ProjectManager.getActProjectVar().programmingtime = settings.getLong("time");


                    if (ProjectManager.getActProjectVar().vcs == VCS.NONE) {
                        MenuBar.menuItem_speichern.setText("Speichern");
                    } else if (ProjectManager.getActProjectVar().vcs == VCS.ITEV) {
                        MenuBar.menuItem_speichern.setText("Revision speichern");
                    }

                    FileInputStream fis = new FileInputStream(file.file());
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    ArrayList<SaveBlock> readedblocks = ((ArrayList<SaveBlock>) ois.readObject());

                    BlockCalculator.extract(readedblocks);


                } catch (Exception e) {
                    e.printStackTrace();
                    Var.openprojects.remove(Var.openprojects.size() - 1);
                    DisplayErrors.customErrorstring = "Fehler beim Laden des Projekts";
                    DisplayErrors.error = e;

                }

                File tempblockfile = new File(Data.tempfolder + "/" + "Program.itid");
                tempblockfile.delete();


                Var.isloading = false;

            }

        };

        Var.isloading = true;
        laden.start();


    }
}
