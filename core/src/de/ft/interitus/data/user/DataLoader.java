package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Var;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.projecttypes.device.BlockTypes.Ev3.Wait;
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
                    FileHandle file = new FileHandle(Data.tempfolder.getAbsolutePath()+"/"+"Blocks.itid");
                    FileInputStream fis = new FileInputStream(file.file());
                    ObjectInputStream ois = new ObjectInputStream(fis);

                ArrayList<SaveBlock> readedblocks = ((ArrayList<SaveBlock>) ois.readObject());

                for(int i=0;i<readedblocks.size();i++) {
                    BlockVar.blocks.add(Var.actProjekt.getBlockGenerator().generateBlock(i,readedblocks.get(i).getX(),readedblocks.get(i).getY(),150,70,new Wait(),Var.actProjekt.getBlockUpdateGenerator(),Var.actProjekt.getBlocktoSaveGenerator()));
                }

                for(int i=0;i<readedblocks.size();i++) {//TODO aktuelles problem liegt darin, das beim laden von zwei nodes die mit index 1 wire_right nicht definiert hat und damit eine kettenreaktion auslößt

                    BlockVar.blocks.get(i).setRight(readedblocks.get(i).getIndex_rechts()); //Set Nachbar rechts
                    BlockVar.blocks.get(i).setLeft(readedblocks.get(i).getIndex_links()); //set Nachbar links

                    if(readedblocks.get(i).getIndex_rechts()!=-1) { //Wenn der Block einen Rechten Nachbar hat

                        if(readedblocks.get(i).getNodes()==null) {

                            BlockVar.wires.add(Var.actProjekt.getWireGenerator().generate(BlockVar.blocks.get(i), BlockVar.blocks.get(BlockVar.blocks.get(i).getRight()))); //Eine Wire mit den entsprechenen Blöcken wird erstellt
                            BlockVar.blocks.get(i).setWire_right(BlockVar.wires.get(BlockVar.wires.size() - 1)); // Der Rechte Block bekommt die Wire zugeteilt
                            BlockVar.blocks.get(BlockVar.blocks.get(i).getRight()).setWire_left(BlockVar.wires.get(BlockVar.wires.size() - 1));// Der Linke Block bekommt die Wire zugeteilt
                            BlockVar.wires.get(BlockVar.wires.size() - 1).setSpace_between_blocks(readedblocks.get(i).isIsspacebetweenrightblock()); //Die Wire wird sichtbar gemacht

                        }else{
                            for(int j=0;j<readedblocks.get(i).getNodes().size();j++) {
                                if(j==0) {
                                    BlockVar.wires.add(Var.actProjekt.getWireGenerator().generate(BlockVar.blocks.get(i)));
                                    BlockVar.blocks.get(i).setWire_right(BlockVar.wires.get(BlockVar.wires.size() - 1)); // Der linke Block bekommt die Wire zugeteilt

                                }else{
                                    BlockVar.wires.add(Var.actProjekt.getWireGenerator().generate(BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1)));
                                    BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1).setWire_right(BlockVar.wires.get(BlockVar.wires.size()-1));
                                }
                                BlockVar.wires.get(BlockVar.wires.size()-1).setSpace_between_blocks(true);


                              BlockVar.wireNodes.add(Var.actProjekt.getWireNodeGenerator().generate(BlockVar.wires.get(BlockVar.wires.size()-1), readedblocks.get(i).getNodes().get(j).get(0), readedblocks.get(i).getNodes().get(j).get(1), readedblocks.get(i).getNodes().get(j).get(2), readedblocks.get(i).getNodes().get(j).get(3)));

                               BlockVar.wires.get(BlockVar.wires.size()-1).setRight_connection(BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1)); //TODO fehler

                               BlockVar.visiblewires.add(BlockVar.wires.get(BlockVar.wires.size()-1));




                            }
                            BlockVar.wires.add(Var.actProjekt.getWireGenerator().generate(BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1),BlockVar.blocks.get(readedblocks.get(i).getIndex_rechts())));
                            BlockVar.wireNodes.get(BlockVar.wireNodes.size()-1).setWire_right(BlockVar.wires.get(BlockVar.wires.size()-1));
                            BlockVar.blocks.get(BlockVar.blocks.get(i).getRight()).setWire_left(BlockVar.wires.get(BlockVar.wires.size() - 1));// Der rechte Block bekommt die Wire zugeteilt
                            BlockVar.wires.get(BlockVar.wires.size()-1).setSpace_between_blocks(true);


                        }
                        }

                }



                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }

                File tempblockfile = new File(Data.tempfolder + "/" + "Blocks.itid");
                tempblockfile.delete();



                Var.isloading = false;

            }

        };

        Var.isloading = true;
        laden.start();


    }
}
