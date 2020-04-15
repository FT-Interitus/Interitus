package de.ft.robocontrol.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.ProgrammingSpace;
import de.ft.robocontrol.data.user.changes.DataManager;
import de.ft.robocontrol.utils.CheckKollision;

import java.util.Timer;
import java.util.TimerTask;


public class BlockUpdate extends Thread {
    public Block block; //Der zugehörige Block den die Klasse updated
    public boolean isrunning = true; //Läuft der Thread gerade?
    public Timer time; //das  ist der Timer in dem alle Update Vorgänge laufen
    boolean toggle; // Ist der Block von der mouse gehovert?
    Vector2 temp1; //Temp vectoren für berechnungs zwischen schritte
    Vector2 temp2;//Temp vectoren für berechnungs zwischen schritte
    Vector3 temp3;//Temp vectoren für berechnungs zwischen schritte
    Vector3 temp4;//Temp vectoren für berechnungs zwischen schritte
    private boolean isconnectorclicked = false;//Ist der connector des zuständigen Blocks ausgelöst
   private Wire tempwire;

    BlockUpdate(Block block) {
        this.block = block; //Der Block wird zu gewiesen

        temp1 = new Vector2(0, 0);//Temp Vectoren init
        temp2 = new Vector2(0, 0);//Temp Vectoren init
        temp3 = new Vector3(0, 0, 0);//Temp Vectoren init
        temp4 = new Vector3(0, 0, 0);//Temp Vectoren init
    }

    @Override
    public void run() {
        time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                try {


                    if (block == null) { //Wenn kein Block mehr verbunden ist wird der timer beendet und damit auch der thread
                        time.cancel();
                    }
                    if (block.getIndex() == -1) { //Wenn der Block gelöscht wird d.h. er hat einen Index von -1 wird der Timer beendet und damit auch der Thread
                        time.cancel();
                        time.purge();
                    }

                    toggle = de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block); //Wird der Block von der Mouse gehovert?

                    //TODO
                    //TODO      Kommentierung fortsetzen (Abgebrochen da hier noch sehr viele Änderungen passieren werden)
                    //TODO




                    if (isconnectorclicked && Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) { //Um vorzeitige wire wieder aufzulösen und ggf zu richtigen umzuwandeln

                        isconnectorclicked = false;
                        BlockVar.showleftdocker = false;
                        if (BlockVar.connetor_offerd_hoverd_block != null) {


                            block.setWire_right(new Wire(block,BlockVar.connetor_offerd_hoverd_block));
                            BlockVar.connetor_offerd_hoverd_block.setWire_left(block.getWire_right());
                            block.getWire_right().setSpace_between_blocks(true);
                            BlockVar.wires.add(block.getWire_right());

                            block.setRight(BlockVar.connetor_offerd_hoverd_block);


                            BlockVar.connetor_offerd_hoverd_block = null;
                        }

                        try {
                            tempwire.getLeft_connection().setWire_right(null);
                            BlockVar.visiblewires.remove(tempwire);
                            BlockVar.wires.remove(tempwire);
                            tempwire = null;
                        }catch (Exception e) {

                        }

                        BlockVar.movingwires =null;


                    }

                    if (!isconnectorclicked && BlockVar.showleftdocker) { //Wenn der eigene Wire connector nicht ausgelöst ist aber ein anderer
                        if (CheckKollision.checkmousewithobject((int) block.getWireconnector_left().x, (int) block.getWireconnector_left().y, 20, 20, (int) ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y)) { //Wenn die Maus über meinen connection offerer fährt...
                            if (BlockVar.connetor_offerd_hoverd_block != block) { //Und der zugehörige Block noch nicht in der Variable steht
                                BlockVar.connetor_offerd_hoverd_block = block; //Schreibt sich der Block in die Variable welcher Block beim loslassen als wire nachbar verbunden werden würde
                            }
                        } else {
                            if (BlockVar.connetor_offerd_hoverd_block == block) { //Wenn die Maus nicht mehr über dem Wire connection offerer ist
                                BlockVar.connetor_offerd_hoverd_block = null; //Wird der Block wieder aus der Variable entfernt
                            }
                        }
                    }


                    if (CheckKollision.checkmousewithobject((int) block.getwireconnector_right().x, (int) block.getwireconnector_right().y, 20, 20, (int) BlockVar.mousepressedold.x, (int) BlockVar.mousepressedold.y) && Gdx.input.isButtonPressed(0)) {
                        if (!(BlockVar.markedblock == block)) {
                            if (!isconnectorclicked) {

                               tempwire = new Wire(block);
                                tempwire.setMovebymouse(true);
                                tempwire.setSpace_between_blocks(true);
                                BlockVar.wires.add(tempwire);
                                BlockVar.movingwires =tempwire;


                                isconnectorclicked = true;
                                if (block.isMarked()) {


                                    block.setMoving(false);
                                    BlockVar.ismoving = false;
                                    BlockVar.marked = false;
                                }

                            } else if (!CheckKollision.checkmousewithobject((int) block.getwireconnector_right().x, (int) block.getwireconnector_right().y, 20, 20, (int) ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y)) {

                                BlockVar.showleftdocker = true;
                            }
                        }
                        //System.out.println("hry");
                    }

                    if (de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block, BlockVar.mousepressedold) && Gdx.input.isButtonPressed(0) && BlockVar.ismoving == false && !block.isMarked() && !BlockVar.marked && BlockVar.markedblock == null) {

                        if (!CheckKollision.checkmousewithobject((int) block.getwireconnector_right().x, (int) block.getwireconnector_right().y, 20, 20, (int) BlockVar.mousepressedold.x, (int) BlockVar.mousepressedold.y)) {


                            // MainGame.logger.debug("Marked Block" + block.getIndex());
                            BlockVar.marked = true;
                            block.setMarked(true);
                            BlockVar.markedblock = block;
                            BlockVar.unterschiedsave.set(ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - block.getX(), ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - block.getY());

                        }
                    }


                    if (BlockVar.ismoving == false && !block.isMoving() && block.isMarked() && Gdx.input.isButtonPressed(0)) {

                        if (!CheckKollision.checkmousewithobject((int) block.getwireconnector_right().x, (int) block.getwireconnector_right().y, 20, 20, (int) BlockVar.mousepressedold.x, (int) BlockVar.mousepressedold.y)) {


                            int feld = 2;
                            if (Math.abs(BlockVar.mousepressedold.x - ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x) > feld || Math.abs(BlockVar.mousepressedold.y - ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y) > feld) {
                                if (block.isMoving() == false && BlockVar.ismoving == false) {
                                    BlockVar.unterschiedsave.set(ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - block.getX(), ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - block.getY());


                                    DataManager.change(block, false, false);
                                    block.setMoving(true);
                                    BlockVar.ismoving = true;

                                }


                            }
                        }

                    }


                    if (block.isMoving() && Gdx.input.isButtonPressed(0)) {

                        block.setX((int) (ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - BlockVar.unterschiedsave.x));
                        block.setY((int) (ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - BlockVar.unterschiedsave.y));

                        //Wenn der Mauszeiger die Ablagefläche berührt
                        // if(CheckKollision.checkmousewithobject(,Gdx.input.getY())) {
                        // gotodelete = true;
                        // }else{
                        //gotodelete = false;
                        //}

                        if(block.getWire_left()!=null) {
                            if (!block.getWire_left().isSpace_between_blocks()) {
                                if (block.getLeft() != null) {
                                    block.getLeft().setRight(null);

                                    block.getWire_left().getLeft_connection().setWire_right(null); //löschen der Wire die zwischen den Blöcken war
                                    BlockVar.wires.remove(block.getWire_left());
                                    block.setWire_left(null);



                                }
                                block.setLeft(null);

                            }
                        }

                        if(block.getWire_right()!=null) {
                            if(!block.getWire_right().isSpace_between_blocks()) {
                                if (block.getRight() != null) {
                                    block.getRight().setLeft(null);

                                    block.getWire_right().getRight_connection().setWire_left(null);//löschen der Wire die zwischen den Blöcken war
                                    BlockVar.wires.remove(block.getWire_right());
                                    block.setWire_right(null);

                                }
                                block.setRight(null);
                            }

                        }







                    } else if (block.isMoving()) {
                        BlockVar.ismoving = false;
                        block.setMoving(false);


                    }


                    if (block.isShowdupulicate_links() || block.isShowdupulicate_rechts()) {
                        if (BlockVar.showduplicat.indexOf(block) == -1) {
                            BlockVar.showduplicat.add(block);
                        }
                    } else {
                        BlockVar.showduplicat.remove(block);
                        BlockVar.biggestblock = null;
                    }

                    //    System.out.println(BlockVar.showduplicat.size());
                    int biggestvalue = 0;
                    int biggestindex = -1;
                    for (int i = 0; i < BlockVar.showduplicat.size(); i++) {

                        try {           //TODO unerklärte Nullpointer bitte behebn //THORIE in der for wird der wert geändert
                            if (BlockVar.showduplicat.get(i).getDublicatmarkedblockuberlappungsflache() > biggestvalue) {
                                biggestvalue = BlockVar.showduplicat.get(i).getDublicatmarkedblockuberlappungsflache();
                                biggestindex = i;
                            }
                        } catch (Exception e) {
                            // e.printStackTrace();
                        }
                    }
                    try {
                        BlockVar.biggestblock = BlockVar.showduplicat.get(biggestindex);
                    } catch (IndexOutOfBoundsException e) {

                    }

                    try {


                        if (CheckKollision.object(BlockVar.markedblock.getX(), BlockVar.markedblock.getY(), BlockVar.markedblock.getH(), BlockVar.markedblock.getW(), block.getX(), block.getY(), block.getH(), block.getW()) && CheckKollision.flache(BlockVar.markedblock.getX(), BlockVar.markedblock.getY(), BlockVar.markedblock.getW(), BlockVar.markedblock.getH(), block.getX(), block.getY()) > 7000 && block != BlockVar.markedblock) {
                            // System.out.println("überschneidung von markedblock und einem block");

                            if (BlockVar.uberlapptmitmarkedblock.indexOf(block) == -1) {
                                block.moved = false;

                                BlockVar.uberlapptmitmarkedblock.add(block);
                            }
                        } else {
                            BlockVar.uberlapptmitmarkedblock.remove(block);


                        }


                        int biggestvalue2 = 0;
                        int biggestindex2 = -1;
                        for (int i = 0; i < BlockVar.uberlapptmitmarkedblock.size(); i++) {
                            //if(BlockVar.uberlapptmitmarkedblock.get(i).)

                            //System.out.println("flaeche   " + BlockVar.uberlapptmitmarkedblock.get(i).getBlockMarkedblockuberlappungsflache() + "i:   " + i);
                            if (BlockVar.uberlapptmitmarkedblock.get(i).getBlockMarkedblockuberlappungsflache() > biggestvalue2) {
                                biggestvalue2 = BlockVar.uberlapptmitmarkedblock.get(i).getBlockMarkedblockuberlappungsflache(); //TODO h
                                biggestindex2 = i;
                            }
                        }
                        try {
                            if (BlockVar.uberlapptmitmarkedblock.get(biggestindex2) != BlockVar.blockmitdergrostenuberlappungmitmarkiertemblock) {
                                BlockVar.blockmitdergrostenuberlappungmitmarkiertemblock = BlockVar.uberlapptmitmarkedblock.get(biggestindex2);
                            }


                        } catch (IndexOutOfBoundsException e) {
                        }
                    } catch (NullPointerException e) {
                    }


                    if (block == BlockVar.blockmitdergrostenuberlappungmitmarkiertemblock && block.moved == false) {
                        block.moved = true;


                        int a = BlockVar.blocks.indexOf(BlockVar.blockmitdergrostenuberlappungmitmarkiertemblock);
                        //System.out.println(a);
                        block.setX(block.getX() + block.getW());

                        block.seted = false;

                        try {

                            while (BlockVar.blocks.get(a).getRight().getIndex() != -1) {

                                //block.getRight().setX(block.getRight().getX() + block.getW());

                                BlockVar.blocks.get(a).getRight().setX(BlockVar.blocks.get(a).getX() + BlockVar.blocks.get(a).getW());
                                a = BlockVar.blocks.indexOf(BlockVar.blocks.get(a).getRight());
                            }

                        } catch (NullPointerException e) {
                        }


                    }

                    if (block.seted == false && BlockVar.biggestblock == block && !Gdx.input.isButtonPressed(0)) {
//System.out.println("funzt");
                        block.seted = true;
                    }

                    if (block.seted == false && BlockVar.biggestblock != block) {

                        // System.out.println("jezt muss das ruckgangig gemacht werdn");

                        int b = BlockVar.blocks.indexOf(block);

                        block.setX(block.getX() - block.getW());


                        try {

                            while (BlockVar.blocks.get(b).getRight().getIndex() != -1) {

                                //block.getRight().setX(block.getRight().getX() + block.getW());

                                BlockVar.blocks.get(b).getRight().setX(BlockVar.blocks.get(b).getRight().getX() - BlockVar.blocks.get(b).getW());
                                b = BlockVar.blocks.indexOf(BlockVar.blocks.get(b).getRight());
                            }

                        } catch (NullPointerException e) {
                        }


                        block.seted = true;
                    }


                    if (de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block) == false && Gdx.input.isButtonPressed(0) && !block.isMoving() && block.isMarked()) {
                        block.setMarked(false);
                        BlockVar.marked = false;
                        BlockVar.markedblock = null;
                    }


                    if (BlockVar.marked && !block.isMarked()) {

                        if (CheckKollision.checkblockwithduplicate(BlockVar.markedblock, block, 0) && block.getRight() == null && BlockVar.markedblock.getWire_left()==null) {
                            if (BlockVar.markedblock.isMoving()) {
                                //System.out.println("Kollision!");


                                block.setShowdupulicate_rechts(true);


                            } else {


                                if (block.getRight() != BlockVar.markedblock && BlockVar.markedblock.getLeft() != block && block.getRight() == null && BlockVar.biggestblock == block) {

                                    //System.out.println("test");
                                    block.setShowdupulicate_rechts(false);

                                    block.setWire_right(new Wire(block,BlockVar.markedblock));
                                    BlockVar.markedblock.setWire_left(block.getWire_right());
                                    BlockVar.wires.add(block.getWire_right());

                                    block.setRight(BlockVar.markedblock);
                                    BlockVar.markedblock.setY(block.getY());
                                    BlockVar.markedblock.setX(block.getX_dup_rechts());
                                }


                            }
                        } else {
                            block.setShowdupulicate_rechts(false);
                        }


                        if (CheckKollision.checkblockwithduplicate(BlockVar.markedblock, block, 1) && block.getLeft() == null &&BlockVar.markedblock.getWire_right()==null) {  //TODO Fehler beheben

                            if (BlockVar.markedblock.isMoving()) {


                                block.setShowdupulicate_links(true);


                            } else {


                                if (block.getRight() != BlockVar.markedblock && BlockVar.markedblock.getLeft() != block && block.getLeft() == null && BlockVar.biggestblock == block) {


                                    block.setShowdupulicate_links(false);


                                    block.setWire_left(new Wire(BlockVar.markedblock,block));
                                    BlockVar.markedblock.setWire_right(block.getWire_left());
                                    BlockVar.wires.add(block.getWire_left());

                                    block.setLeft(BlockVar.markedblock);
                                    BlockVar.markedblock.setY(block.getY());
                                    BlockVar.markedblock.setX(block.getX_dup_links());
                                }


                            }
                        } else {
                            block.setShowdupulicate_links(false);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace(); //FOR Debug to find errors
                }

            }
        }, 0, 20);

    }

    public boolean isIsconnectorclicked() {
        return isconnectorclicked;
    }
}
