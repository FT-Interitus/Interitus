package de.ft.interitus.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Var;
import de.ft.interitus.data.user.changes.DataManager;
import de.ft.interitus.utils.CheckKollision;

import java.util.Timer;
import java.util.TimerTask;


public abstract class BlockUpdate extends Thread {
    public Block block; //Der zugehörige Block den die Klasse updated
    public boolean isrunning = true; //Läuft der Thread gerade?
    public Timer time; //das  ist der Timer in dem alle Update Vorgänge laufen
    public boolean isconnectorclicked = false;//Ist der connector des zuständigen Blocks ausgelöst
    public boolean geschoben = false;
    public Wire tempwire;
    boolean toggle; // Ist der Block von der mouse gehovert?
    Vector2 temp1; //Temp vectoren für berechnungs zwischen schritte
    Vector2 temp2;//Temp vectoren für berechnungs zwischen schritte
    Vector3 temp3;//Temp vectoren für berechnungs zwischen schritte
    Vector3 temp4;//Temp vectoren für berechnungs zwischen schritte
    private boolean IsMousealreadypressed = false;


   public BlockUpdate(Block block) {
        this.block = block; //Der Block wird zugewiesen

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

                if(!Var.isdialogeopend) {

                try {


                    if (block == null) { //Wenn kein Block mehr verbunden ist wird der timer beendet und damit auch der thread
                        time.cancel();
                    }
                    if (block.getIndex() == -1) { //Wenn der Block gelöscht wird d.h. er hat einen Index von -1 wird der Timer beendet und damit auch der Thread
                        time.cancel();
                        time.purge();
                    }

                    toggle = de.ft.interitus.utils.CheckKollision.checkmousewithblock(block); //Wird der Block von der Mouse gehovert?

                    //TODO
                    //TODO      Kommentierung fortsetzen (Abgebrochen da hier noch sehr viele Änderungen passieren werden)
                    //TODO


                    if (!isIsconnectorclicked() && BlockVar.showleftdocker && CheckKollision.object(block.getX_entrance(), block.getY_entrance(), block.getH_entrance(), block.getW_entrance(), (int) ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y, 1, 1) && Gdx.input.isButtonJustPressed(0) && block.getLeft() == -1) { //TODO Durch das Just pressed kann es sein das es manchmal verpasst wird dieses Event auszuführen
                        BlockVar.showleftdocker = false;
                        BlockVar.movingwires.setMovebymouse(false);
                        BlockVar.movingwires.setRight_connection(block);
                        BlockVar.movingwires.setSpace_between_blocks(true);
                        block.setWire_left(BlockVar.movingwires);
                        try {
                            BlockVar.wire_beginn.setRight(block.getIndex());
                        } catch (NullPointerException e) {

                            //Falls die eine Node dazwischen ist und der Nachbar über die Node gesetzt werden muss

                            System.out.println("Konnte Nachbar nicht setzten");


                        }

                        BlockVar.blocks.get(BlockVar.movingwires.getRight_connection().getLeft()).getBlockupdate().isconnectorclicked = false;
                        BlockVar.movingwires = null;


                    }


                    if ((isconnectorclicked && Gdx.input.isKeyPressed(Input.Keys.ESCAPE))) { //Um vorzeitige wire wieder aufzulösen und ggf zu richtigen umzuwandeln

                        isconnectorclicked = false;
                        BlockVar.showleftdocker = false;

                        try {
                            tempwire.getLeft_connection().setWire_right(null);
                            BlockVar.visiblewires.remove(tempwire);
                            BlockVar.wires.remove(tempwire);

                            try {

                                tempwire.getRight_connectionObject().getwirenode().setWire_left(null);
                                tempwire.setRight_connection(null);

                            } catch (NullPointerException e) {
                                //Falls hier keine Wire ist
                            }
                            tempwire = null;
                        } catch (Exception e) {

                        }

                        BlockVar.movingwires = null;


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


                    if (CheckKollision.checkmousewithobject((int) block.getwireconnector_right().x, (int) block.getwireconnector_right().y, 20, 20, (int) BlockVar.mousepressedold.x, (int) BlockVar.mousepressedold.y) && Gdx.input.isButtonPressed(0) && BlockVar.movingwires == null && !IsMousealreadypressed && block.getWire_right() == null) {
                        if (!(BlockVar.markedblock == block)) {
                            if (!isconnectorclicked && BlockVar.wirezulassung) {


                                  tempwire = Var.actProjekt.getWireGenerator().generate(block);

                                tempwire.setMovebymouse(true);

                                tempwire.setSpace_between_blocks(true);

                                block.setWire_right(tempwire);
                                BlockVar.wires.add(tempwire);
                                BlockVar.movingwires = tempwire;
                                BlockVar.wire_beginn = block;


                                BlockVar.showleftdocker = true;

                                isconnectorclicked = true;
                                if (block.isMarked()) {


                                    block.setMoving(false);
                                    BlockVar.ismoving = false;
                                    BlockVar.marked = false;
                                }

                                IsMousealreadypressed = true;

                            }
                        }

                    }

                    if (IsMousealreadypressed && !Gdx.input.isButtonPressed(0)) {
                        IsMousealreadypressed = false;
                    }

                    if (de.ft.interitus.utils.CheckKollision.checkmousewithblock(block, BlockVar.mousepressedold) && Gdx.input.isButtonPressed(0) && BlockVar.ismoving == false && !block.isMarked() && !BlockVar.marked && BlockVar.markedblock == null) {

                        if (!CheckKollision.checkmousewithobject((int) block.getwireconnector_right().x, (int) block.getwireconnector_right().y, 20, 20, (int) BlockVar.mousepressedold.x, (int) BlockVar.mousepressedold.y)) {


                            // MainGame.logger.debug("Marked Block" + block.getIndex());
                            BlockVar.marked = true;
                            block.setMarked(true);
                            BlockVar.markedblock = block;
                            BlockVar.unterschiedsave.set(ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - block.getX(), ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - block.getY());

                        }
                    }


                    if (BlockVar.ismoving == false && !block.isMoving() && block.isMarked() && Gdx.input.isButtonPressed(0) && CheckKollision.checkmousewithblock(block)) {

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

                        if (block.getWire_left() != null) {
                            if (!block.getWire_left().isSpace_between_blocks()) {
                                if (block.getLeft() != -1) {
                                    BlockVar.blocks.get(block.getLeft()).setRight(-1);

                                    block.getWire_left().getLeft_connection().setWire_right(null); //löschen der Wire die zwischen den Blöcken war
                                    BlockVar.wires.remove(block.getWire_left());
                                    block.setWire_left(null);


                                }
                                block.setLeft(-1);

                            }
                        }

                        if (block.getWire_right() != null) {
                            if (!block.getWire_right().isSpace_between_blocks()) {
                                if (block.getRight() != -1) {
                                    BlockVar.blocks.get(block.getRight()).setLeft(-1);

                                    block.getWire_right().getRight_connection().setWire_left(null);//löschen der Wire die zwischen den Blöcken war
                                    BlockVar.wires.remove(block.getWire_right());
                                    block.setWire_right(null);

                                }
                                block.setRight(-1);
                            }

                        }


                    } else if (block.isMoving()) {
                        BlockVar.ismoving = false;
                        block.setMoving(false);


                    }

                    if (BlockVar.markedblock == null && block.isMarked()) {
                        block.setMarked(false);
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

                        try {
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
                            try {
                                if (BlockVar.uberlapptmitmarkedblock.get(i).getBlockMarkedblockuberlappungsflache() > biggestvalue2) {
                                    biggestvalue2 = BlockVar.uberlapptmitmarkedblock.get(i).getBlockMarkedblockuberlappungsflache(); //TODO h
                                    biggestindex2 = i;
                                }
                            } catch (NullPointerException e) {
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


                    try {

                        if (block == BlockVar.blockmitdergrostenuberlappungmitmarkiertemblock && block.moved == false && !geschoben && !block.getWire_left().isSpace_between_blocks() && !block.getWire_right().isSpace_between_blocks()) {
                            block.moved = true;
                            geschoben = true;

                            int a = block.getIndex();
                            //System.out.println(a);
                            block.setX(block.getX() + block.getW());

                            block.seted = false;

                                while (BlockVar.blocks.get(a).getRight() != -1) {

                                    //block.getRight().setX(block.getRight().getX() + block.getW());

                                    BlockVar.blocks.get(BlockVar.blocks.get(a).getRight()).setX(BlockVar.blocks.get(a).getX() + BlockVar.blocks.get(a).getW());
                                    a = BlockVar.blocks.get(a).getRight();
                                }


                        }


                    } catch (NullPointerException e) { //If there are no wires for example if you delete a block with two wires
                        if (block == BlockVar.blockmitdergrostenuberlappungmitmarkiertemblock && block.moved == false && !geschoben) {
                            block.moved = true;
                            geschoben = true;

                            int a = block.getIndex();
                            block.setX(block.getX() + block.getW());

                            block.seted = false;


                                while (BlockVar.blocks.get(a).getRight() != -1) {

                                    //block.getRight().setX(block.getRight().getX() + block.getW());

                                    BlockVar.blocks.get(BlockVar.blocks.get(a).getRight()).setX(BlockVar.blocks.get(a).getX() + BlockVar.blocks.get(a).getW());
                                    a = BlockVar.blocks.get(a).getRight();

                                }



                        }

                    }

                    if (block.seted == false && BlockVar.biggestblock == block && !Gdx.input.isButtonPressed(0)) { //TODO flackern wegmachen!

                        block.seted = true;
                        geschoben = false;
                    }

                    if (block.seted == false && BlockVar.biggestblock != block) {
                        geschoben = false;


                        int b = block.getIndex();

                        block.setX(block.getX() - block.getW());



                            while (BlockVar.blocks.get(b).getRight() != -1) {

                                //block.getRight().setX(block.getRight().getX() + block.getW());

                                BlockVar.blocks.get(BlockVar.blocks.get(b).getRight()).setX(BlockVar.blocks.get(BlockVar.blocks.get(b).getRight()).getX() - BlockVar.blocks.get(b).getW());//TODO tim ist sich nicht sicher
                                b = BlockVar.blocks.get(b).getRight();
                            }




                        block.seted = true;
                    }


                    if (!CheckKollision.checkmousewithblock(block) && Gdx.input.isButtonPressed(0) && !block.isMoving() && block.isMarked()) {
                        block.setMarked(false);
                        BlockVar.marked = false;
                        BlockVar.markedblock = null;
                    }


                    if (BlockVar.marked && !block.isMarked()) {

                        if (CheckKollision.checkblockwithduplicate(BlockVar.markedblock, block, 0) && block.getRight() == -1 && BlockVar.markedblock.getWire_left() == null) {
                            if (BlockVar.markedblock.isMoving()) {
                                //System.out.println("Kollision!");


                                block.setShowdupulicate_rechts(true);


                            } else {


                                if (block.getRight() != BlockVar.markedblock.getIndex() && BlockVar.markedblock.getLeft() != block.getIndex() && block.getRight() == -1 && BlockVar.biggestblock == block) {

                                    //System.out.println("test");
                                    block.setShowdupulicate_rechts(false);


                                    block.setWire_right(Var.actProjekt.getWireGenerator().generate(block,BlockVar.markedblock));
                                    BlockVar.markedblock.setWire_left(block.getWire_right());
                                    BlockVar.wires.add(block.getWire_right());

                                    block.setRight(BlockVar.markedblock.getIndex());
                                    BlockVar.markedblock.setY(block.getY());
                                    BlockVar.markedblock.setX(block.getX_dup_rechts());
                                }


                            }
                        } else {
                            block.setShowdupulicate_rechts(false);
                        }

                        try {
                            if (CheckKollision.checkblockwithduplicate(BlockVar.markedblock, block, 1) && block.getLeft() == -1 && BlockVar.markedblock.getWire_right() == null) {

                                if (BlockVar.markedblock.isMoving()) {


                                    block.setShowdupulicate_links(true);


                                } else {


                                    if (block.getRight() != BlockVar.markedblock.getIndex() && BlockVar.markedblock.getLeft() != block.getIndex() && block.getLeft() == -1 && BlockVar.biggestblock == block) {


                                        block.setShowdupulicate_links(false);



                                        block.setWire_left(Var.actProjekt.getWireGenerator().generate(BlockVar.markedblock,block));
                                        BlockVar.markedblock.setWire_right(block.getWire_left());
                                        BlockVar.wires.add(block.getWire_left());


                                        block.setLeft(BlockVar.markedblock.getIndex());
                                        BlockVar.markedblock.setY(block.getY());
                                        BlockVar.markedblock.setX(block.getX_dup_links());
                                    }


                                }
                            } else {
                                block.setShowdupulicate_links(false);
                            }

                        } catch (NullPointerException e) {

                        }
                    }
                } catch (Exception e) {
                    DisplayErrors.customErrorstring = "Fehler in einem "+block.getBlocktype().getName()+" Block";
                    DisplayErrors.error = e;
                }

            }

            }
        }, 0, 20);

    }

    public boolean isIsconnectorclicked() {
        return isconnectorclicked;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
}
