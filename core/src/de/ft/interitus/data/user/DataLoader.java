package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.UI.MenuBar;
import de.ft.interitus.UI.UI;
import de.ft.interitus.Var;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.projecttypes.ProjectVar;
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

                ClearActOpenProgramm.clear();


                try {
                    Zip.unzip(handle.file().getAbsolutePath(), Data.tempfolder.getAbsolutePath());
                    FileHandle file = new FileHandle(Data.tempfolder.getAbsolutePath()+"/"+"Program.itid");
                    FileHandle settingsfile = new FileHandle(Data.tempfolder.getAbsolutePath()+"/"+"Settings.itps");
                    JSONObject settings = new JSONObject(settingsfile.readString());
                    ProjectVar.vcs = settings.getInt("vcs");

                    if(ProjectVar.vcs== VCS.NONE) {
                        MenuBar.menuItem_speichern.setText("Speichern");
                    }else if(ProjectVar.vcs==VCS.ITEV) {
                        MenuBar.menuItem_speichern.setText("Revision speichern");
                    }

                    String typetemp = settings.getString("type");
                    for(int i=0;i< ProjectTypesVar.projectTypes.size();i++) {
                        if(ProjectTypesVar.projectTypes.get(i).getName().contains(typetemp)) {
                            ProjectVar.projectType = ProjectTypesVar.projectTypes.get(i);
                        }
                    }



                    if(ProjectVar.projectType==null) {

                        Dialogs.showErrorDialog(UI.stage,"Das Plugin das mit diesem Projekt verbunden ist, ist nicht installiert."); //TODO more Informations (link to plugin)

                        this.interrupt();
                        return;
                    }

                    FileInputStream fis = new FileInputStream(file.file());
                    ObjectInputStream ois = new ObjectInputStream(fis);

                ArrayList<SaveBlock> readedblocks = ((ArrayList<SaveBlock>) ois.readObject());

                for(int i=0;i<readedblocks.size();i++) {
                    BlockVar.blocks.add( ProjectVar.projectType.getBlockGenerator().generateBlock(i,readedblocks.get(i).getX(),readedblocks.get(i).getY(),150,70,new Wait(), ProjectVar.projectType.getBlockUpdateGenerator(), ProjectVar.projectType.getBlocktoSaveGenerator()));
                }

                for(int i=0;i<readedblocks.size();i++) {

                    BlockVar.blocks.get(i).setRight(readedblocks.get(i).getIndex_rechts()); //Set Nachbar rechts
                    BlockVar.blocks.get(i).setLeft(readedblocks.get(i).getIndex_links()); //set Nachbar links

                    if(readedblocks.get(i).getIndex_rechts()!=-1) { //Wenn der Block einen Rechten Nachbar hat

                        if(readedblocks.get(i).getNodes()==null) {

                            BlockVar.wires.add( ProjectVar.projectType.getWireGenerator().generate(BlockVar.blocks.get(i), BlockVar.blocks.get(BlockVar.blocks.get(i).getRight()))); //Eine Wire mit den entsprechenen Blöcken wird erstellt
                            BlockVar.blocks.get(i).setWire_right(BlockVar.wires.get(BlockVar.wires.size() - 1)); // Der Rechte Block bekommt die Wire zugeteilt
                            BlockVar.blocks.get(BlockVar.blocks.get(i).getRight()).setWire_left(BlockVar.wires.get(BlockVar.wires.size() - 1));// Der Linke Block bekommt die Wire zugeteilt
                            BlockVar.wires.get(BlockVar.wires.size() - 1).setSpace_between_blocks(readedblocks.get(i).isIsspacebetweenrightblock()); //Die Wire wird sichtbar gemacht

                        }else{
                            for(int j=0;j<readedblocks.get(i).getNodes().size();j++) {
                                if(j==0) {
                                    BlockVar.wires.add( ProjectVar.projectType.getWireGenerator().generate(BlockVar.blocks.get(i)));
                                    BlockVar.blocks.get(i).setWire_right(BlockVar.wires.get(BlockVar.wires.size() - 1)); // Der linke Block bekommt die Wire zugeteilt

                                }else{
                                    BlockVar.wires.add( ProjectVar.projectType.getWireGenerator().generate(BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1)));
                                    BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1).setWire_right(BlockVar.wires.get(BlockVar.wires.size()-1));
                                }
                                BlockVar.wires.get(BlockVar.wires.size()-1).setSpace_between_blocks(true);


                              BlockVar.wireNodes.add( ProjectVar.projectType.getWireNodeGenerator().generate(BlockVar.wires.get(BlockVar.wires.size()-1), readedblocks.get(i).getNodes().get(j).get(0), readedblocks.get(i).getNodes().get(j).get(1), readedblocks.get(i).getNodes().get(j).get(2), readedblocks.get(i).getNodes().get(j).get(3)));

                               BlockVar.wires.get(BlockVar.wires.size()-1).setRight_connection(BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1));

                               BlockVar.visiblewires.add(BlockVar.wires.get(BlockVar.wires.size()-1));




                            }
                            BlockVar.wires.add( ProjectVar.projectType.getWireGenerator().generate(BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1),BlockVar.blocks.get(readedblocks.get(i).getIndex_rechts())));
                            BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1).setWire_right(BlockVar.wires.get(BlockVar.wires.size()-1));
                            BlockVar.blocks.get(BlockVar.blocks.get(i).getRight()).setWire_left(BlockVar.wires.get(BlockVar.wires.size() - 1));// Der rechte Block bekommt die Wire zugeteilt
                            BlockVar.wires.get(BlockVar.wires.size()-1).setSpace_between_blocks(true);


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
