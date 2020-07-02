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

                    for (int i = 0; i < readedblocks.size(); i++) {
                        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(i, readedblocks.get(i).getX(), readedblocks.get(i).getY(), 150, 70, ProjectManager.getActProjectVar().projectType.getProjectblocks().get(readedblocks.get(i).getPlatformspecificblockid()), ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));
                    }

                    for (int i = 0; i < readedblocks.size(); i++) {

                        if (readedblocks.get(i).getIndex_rechts() != -1) {
                            ProjectManager.getActProjectVar().blocks.get(i).setRight(ProjectManager.getActProjectVar().blocks.get(readedblocks.get(i).getIndex_rechts())); //Set Nachbar rechts
                        }
                        if (readedblocks.get(i).getIndex_links() != -1) {
                            ProjectManager.getActProjectVar().blocks.get(i).setLeft(ProjectManager.getActProjectVar().blocks.get(readedblocks.get(i).getIndex_links())); //set Nachbar links
                        }


                        if (readedblocks.get(i).getIndex_rechts() != -1) { //Wenn der Block einen Rechten Nachbar hat

                            if (readedblocks.get(i).getNodes() == null) {

                                ProjectManager.getActProjectVar().wires.add(ProjectManager.getActProjectVar().projectType.getWireGenerator().generate(ProjectManager.getActProjectVar().blocks.get(i), ProjectManager.getActProjectVar().blocks.get(i).getRight())); //Eine Wire mit den entsprechenen Blöcken wird erstellt
                                ProjectManager.getActProjectVar().blocks.get(i).setWire_right(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1)); // Der Rechte Block bekommt die Wire zugeteilt
                                ProjectManager.getActProjectVar().blocks.get(i).getRight().setWire_left(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));// Der Linke Block bekommt die Wire zugeteilt
                                ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1).setSpace_between_blocks(readedblocks.get(i).isIsspacebetweenrightblock()); //Die Wire wird sichtbar gemacht

                            } else {
                                for (int j = 0; j < readedblocks.get(i).getNodes().size(); j++) {
                                    if (j == 0) {
                                        ProjectManager.getActProjectVar().wires.add(ProjectManager.getActProjectVar().projectType.getWireGenerator().generate(ProjectManager.getActProjectVar().blocks.get(i)));
                                        ProjectManager.getActProjectVar().blocks.get(i).setWire_right(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1)); // Der linke Block bekommt die Wire zugeteilt

                                    } else {
                                        ProjectManager.getActProjectVar().wires.add(ProjectManager.getActProjectVar().projectType.getWireGenerator().generate(ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1)));
                                        ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1).setWire_right(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));
                                    }
                                    ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1).setSpace_between_blocks(true);


                                    ProjectManager.getActProjectVar().wireNodes.add(ProjectManager.getActProjectVar().projectType.getWireNodeGenerator().generate(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1), readedblocks.get(i).getNodes().get(j).get(0), readedblocks.get(i).getNodes().get(j).get(1), readedblocks.get(i).getNodes().get(j).get(2), readedblocks.get(i).getNodes().get(j).get(3)));

                                    ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1).setRight_connection(ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1));

                                    ProjectManager.getActProjectVar().visiblewires.add(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));


                                }
                                ProjectManager.getActProjectVar().wires.add(ProjectManager.getActProjectVar().projectType.getWireGenerator().generate(ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1), ProjectManager.getActProjectVar().blocks.get(readedblocks.get(i).getIndex_rechts())));
                                ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1).setWire_right(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));
                                ProjectManager.getActProjectVar().blocks.get(i).getRight().setWire_left(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));// Der rechte Block bekommt die Wire zugeteilt
                                ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1).setSpace_between_blocks(true);


                            }
                        }

                    }


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
