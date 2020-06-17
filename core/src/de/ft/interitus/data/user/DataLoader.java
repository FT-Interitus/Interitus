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
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.VCS;
import de.ft.interitus.projecttypes.device.BlockTypes.Ev3.Wait;
import de.ft.interitus.projecttypes.device.BlockTypes.ProjectTypesVar;
import de.ft.interitus.utils.ClearActOpenProgramm;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class DataLoader {
    public static void load(final FileHandle handle) {


        Thread laden = new Thread() {
            public void run() {

               // ClearActOpenProgramm.clear();


                try {
                    Zip.unzip(handle.file().getAbsolutePath(), Data.tempfolder.getAbsolutePath());
                    FileHandle file = new FileHandle(Data.tempfolder.getAbsolutePath()+"/"+"Program.itid");
                    FileHandle settingsfile = new FileHandle(Data.tempfolder.getAbsolutePath()+"/"+"Settings.itps");
                    JSONObject settings = new JSONObject(settingsfile.readString());


                    ProjectTypes temptype = null;
                    String typetemp = settings.getString("type");
                    for(int i=0;i< ProjectTypesVar.projectTypes.size();i++) {
                        if(ProjectTypesVar.projectTypes.get(i).getName().contains(typetemp)) {
                            temptype = ProjectTypesVar.projectTypes.get(i);
                        }
                    }



                    if(temptype==null) {

                        Dialogs.showErrorDialog(UI.stage,"Das Plugin das mit diesem Projekt verbunden ist, ist nicht installiert."); //TODO more Informations (link to plugin)

                        this.interrupt();
                        return;
                    }else{
                       Var.openprojects.add( temptype.init());

                       Var.openprojectindex = Var.openprojects.size()-1;
                        BlockTappedBar.init();

                       System.out.println("changed tab");
                       //TODO Change Tab to this
                    }

                    Var.openprojects.get(Var.openprojectindex).vcs = settings.getInt("vcs");

                    if(Var.openprojects.get(Var.openprojectindex).vcs== VCS.NONE) {
                        MenuBar.menuItem_speichern.setText("Speichern");
                    }else if(Var.openprojects.get(Var.openprojectindex).vcs==VCS.ITEV) {
                        MenuBar.menuItem_speichern.setText("Revision speichern");
                    }

                    FileInputStream fis = new FileInputStream(file.file());
                    ObjectInputStream ois = new ObjectInputStream(fis);

                ArrayList<SaveBlock> readedblocks = ((ArrayList<SaveBlock>) ois.readObject());

                for(int i=0;i<readedblocks.size();i++) {
                    Var.openprojects.get(Var.openprojectindex).blocks.add( Var.openprojects.get(Var.openprojectindex).projectType.getBlockGenerator().generateBlock(i,readedblocks.get(i).getX(),readedblocks.get(i).getY(),150,70,new Wait(), Var.openprojects.get(Var.openprojectindex).projectType.getBlockUpdateGenerator(), Var.openprojects.get(Var.openprojectindex).projectType.getBlocktoSaveGenerator()));
                }

                for(int i=0;i<readedblocks.size();i++) {

                    Var.openprojects.get(Var.openprojectindex).blocks.get(i).setRight(readedblocks.get(i).getIndex_rechts()); //Set Nachbar rechts
                    Var.openprojects.get(Var.openprojectindex).blocks.get(i).setLeft(readedblocks.get(i).getIndex_links()); //set Nachbar links

                    if(readedblocks.get(i).getIndex_rechts()!=-1) { //Wenn der Block einen Rechten Nachbar hat

                        if(readedblocks.get(i).getNodes()==null) {

                            Var.openprojects.get(Var.openprojectindex).wires.add( Var.openprojects.get(Var.openprojectindex).projectType.getWireGenerator().generate(Var.openprojects.get(Var.openprojectindex).blocks.get(i), Var.openprojects.get(Var.openprojectindex).blocks.get(Var.openprojects.get(Var.openprojectindex).blocks.get(i).getRight()))); //Eine Wire mit den entsprechenen Blöcken wird erstellt
                            Var.openprojects.get(Var.openprojectindex).blocks.get(i).setWire_right(Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size() - 1)); // Der Rechte Block bekommt die Wire zugeteilt
                            Var.openprojects.get(Var.openprojectindex).blocks.get(Var.openprojects.get(Var.openprojectindex).blocks.get(i).getRight()).setWire_left(Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size() - 1));// Der Linke Block bekommt die Wire zugeteilt
                            Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size() - 1).setSpace_between_blocks(readedblocks.get(i).isIsspacebetweenrightblock()); //Die Wire wird sichtbar gemacht

                        }else{
                            for(int j=0;j<readedblocks.get(i).getNodes().size();j++) {
                                if(j==0) {
                                    Var.openprojects.get(Var.openprojectindex).wires.add( Var.openprojects.get(Var.openprojectindex).projectType.getWireGenerator().generate(Var.openprojects.get(Var.openprojectindex).blocks.get(i)));
                                    Var.openprojects.get(Var.openprojectindex).blocks.get(i).setWire_right(Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size() - 1)); // Der linke Block bekommt die Wire zugeteilt

                                }else{
                                    Var.openprojects.get(Var.openprojectindex).wires.add( Var.openprojects.get(Var.openprojectindex).projectType.getWireGenerator().generate(Var.openprojects.get(Var.openprojectindex).wireNodes.get(Var.openprojects.get(Var.openprojectindex).wireNodes.size()-1)));
                                    Var.openprojects.get(Var.openprojectindex).wireNodes.get(Var.openprojects.get(Var.openprojectindex).wireNodes.size()-1).setWire_right(Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size()-1));
                                }
                                Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size()-1).setSpace_between_blocks(true);


                              Var.openprojects.get(Var.openprojectindex).wireNodes.add( Var.openprojects.get(Var.openprojectindex).projectType.getWireNodeGenerator().generate(Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size()-1), readedblocks.get(i).getNodes().get(j).get(0), readedblocks.get(i).getNodes().get(j).get(1), readedblocks.get(i).getNodes().get(j).get(2), readedblocks.get(i).getNodes().get(j).get(3)));

                               Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size()-1).setRight_connection(Var.openprojects.get(Var.openprojectindex).wireNodes.get(Var.openprojects.get(Var.openprojectindex).wireNodes.size()-1));

                               Var.openprojects.get(Var.openprojectindex).visiblewires.add(Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size()-1));




                            }
                            Var.openprojects.get(Var.openprojectindex).wires.add( Var.openprojects.get(Var.openprojectindex).projectType.getWireGenerator().generate(Var.openprojects.get(Var.openprojectindex).wireNodes.get(Var.openprojects.get(Var.openprojectindex).wireNodes.size()-1),Var.openprojects.get(Var.openprojectindex).blocks.get(readedblocks.get(i).getIndex_rechts())));
                            Var.openprojects.get(Var.openprojectindex).wireNodes.get(Var.openprojects.get(Var.openprojectindex).wireNodes.size()-1).setWire_right(Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size()-1));
                            Var.openprojects.get(Var.openprojectindex).blocks.get(Var.openprojects.get(Var.openprojectindex).blocks.get(i).getRight()).setWire_left(Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size() - 1));// Der rechte Block bekommt die Wire zugeteilt
                            Var.openprojects.get(Var.openprojectindex).wires.get(Var.openprojects.get(Var.openprojectindex).wires.size()-1).setSpace_between_blocks(true);


                        }
                        }

                }




                }catch (Exception e) {
                    DisplayErrors.customErrorstring ="Fehler beim Laden des Projekts";
                    DisplayErrors.error =e;

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
