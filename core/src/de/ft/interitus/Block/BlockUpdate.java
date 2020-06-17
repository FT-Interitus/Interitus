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


                    if (!isIsconnectorclicked() && Var.openprojects.get(Var.openprojectindex).showleftdocker && CheckKollision.object(block.getX_entrance(), block.getY_entrance(), block.getH_entrance(), block.getW_entrance(), (int) ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y, 1, 1) && Gdx.input.isButtonJustPressed(0) && block.getLeft() == -1) { //TODO Durch das Just pressed kann es sein das es manchmal verpasst wird dieses Event auszuführen
                        Var.openprojects.get(Var.openprojectindex).showleftdocker = false;
                        Var.openprojects.get(Var.openprojectindex).movingwires.setMovebymouse(false);
                        Var.openprojects.get(Var.openprojectindex).movingwires.setRight_connection(block);
                        Var.openprojects.get(Var.openprojectindex).movingwires.setSpace_between_blocks(true);
                        block.setWire_left(Var.openprojects.get(Var.openprojectindex).movingwires);
                        try {
                            Var.openprojects.get(Var.openprojectindex).wire_beginn.setRight(block.getIndex());
                        } catch (NullPointerException e) {

                            //Falls die eine Node dazwischen ist und der Nachbar über die Node gesetzt werden muss

                            System.out.println("Konnte Nachbar nicht setzten");


                        }

                        Var.openprojects.get(Var.openprojectindex).blocks.get(Var.openprojects.get(Var.openprojectindex).movingwires.getRight_connection().getLeft()).getBlockupdate().isconnectorclicked = false;
                        Var.openprojects.get(Var.openprojectindex).movingwires = null;


                    }


                    if ((isconnectorclicked && Gdx.input.isKeyPressed(Input.Keys.ESCAPE))) { //Um vorzeitige wire wieder aufzulösen und ggf zu richtigen umzuwandeln

                        isconnectorclicked = false;
                        Var.openprojects.get(Var.openprojectindex).showleftdocker = false;

                        try {
                            tempwire.getLeft_connection().setWire_right(null);
                            Var.openprojects.get(Var.openprojectindex).visiblewires.remove(tempwire);
                            Var.openprojects.get(Var.openprojectindex).wires.remove(tempwire);

                            try {

                                tempwire.getRight_connectionObject().getwirenode().setWire_left(null);
                                tempwire.setRight_connection(null);

                            } catch (NullPointerException e) {
                                //Falls hier keine Wire ist
                            }
                            tempwire = null;
                        } catch (Exception e) {

                        }

                        Var.openprojects.get(Var.openprojectindex).movingwires = null;


                    }

                    if (!isconnectorclicked && Var.openprojects.get(Var.openprojectindex).showleftdocker) { //Wenn der eigene Wire connector nicht ausgelöst ist aber ein anderer
                        if (CheckKollision.checkmousewithobject((int) block.getWireconnector_left().x, (int) block.getWireconnector_left().y, 20, 20, (int) ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y)) { //Wenn die Maus über meinen connection offerer fährt...
                            if (Var.openprojects.get(Var.openprojectindex).connetor_offerd_hoverd_block != block) { //Und der zugehörige Block noch nicht in der Variable steht
                                Var.openprojects.get(Var.openprojectindex).connetor_offerd_hoverd_block = block; //Schreibt sich der Block in die Variable welcher Block beim loslassen als wire nachbar verbunden werden würde
                            }
                        } else {
                            if (Var.openprojects.get(Var.openprojectindex).connetor_offerd_hoverd_block == block) { //Wenn die Maus nicht mehr über dem Wire connection offerer ist
                                Var.openprojects.get(Var.openprojectindex).connetor_offerd_hoverd_block = null; //Wird der Block wieder aus der Variable entfernt
                            }
                        }
                    }


                    if (CheckKollision.checkmousewithobject((int) block.getwireconnector_right().x, (int) block.getwireconnector_right().y, 20, 20, (int) Var.mousepressedold.x, (int) Var.mousepressedold.y) && Gdx.input.isButtonPressed(0) && Var.openprojects.get(Var.openprojectindex).movingwires == null && !IsMousealreadypressed && block.getWire_right() == null) {
                        if (!(Var.openprojects.get(Var.openprojectindex).markedblock == block)) {
                            if (!isconnectorclicked && Var.openprojects.get(Var.openprojectindex).wirezulassung) {


                                  tempwire = Var.openprojects.get(Var.openprojectindex).projectType.getWireGenerator().generate(block);

                                tempwire.setMovebymouse(true);

                                tempwire.setSpace_between_blocks(true);

                                block.setWire_right(tempwire);
                                Var.openprojects.get(Var.openprojectindex).wires.add(tempwire);
                                Var.openprojects.get(Var.openprojectindex).movingwires = tempwire;
                                Var.openprojects.get(Var.openprojectindex).wire_beginn = block;


                                Var.openprojects.get(Var.openprojectindex).showleftdocker = true;

                                isconnectorclicked = true;
                                if (block.isMarked()) {


                                    block.setMoving(false);
                                    Var.openprojects.get(Var.openprojectindex).ismoving = false;
                                    Var.openprojects.get(Var.openprojectindex).marked = false;
                                }

                                IsMousealreadypressed = true;

                            }
                        }

                    }

                    if (IsMousealreadypressed && !Gdx.input.isButtonPressed(0)) {
                        IsMousealreadypressed = false;
                    }

                    if (de.ft.interitus.utils.CheckKollision.checkmousewithblock(block, Var.mousepressedold) && Gdx.input.isButtonPressed(0) && Var.openprojects.get(Var.openprojectindex).ismoving == false && !block.isMarked() && !Var.openprojects.get(Var.openprojectindex).marked && Var.openprojects.get(Var.openprojectindex).markedblock == null) {

                        if (!CheckKollision.checkmousewithobject((int) block.getwireconnector_right().x, (int) block.getwireconnector_right().y, 20, 20, (int) Var.mousepressedold.x, (int) Var.mousepressedold.y)) {


                            // MainGame.logger.debug("Marked Block" + block.getIndex());
                            Var.openprojects.get(Var.openprojectindex).marked = true;
                            block.setMarked(true);
                            Var.openprojects.get(Var.openprojectindex).markedblock = block;
                            Var.openprojects.get(Var.openprojectindex).unterschiedsave.set(ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - block.getX(), ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - block.getY());

                        }
                    }


                    if (Var.openprojects.get(Var.openprojectindex).ismoving == false && !block.isMoving() && block.isMarked() && Gdx.input.isButtonPressed(0) && CheckKollision.checkmousewithblock(block)) {

                        if (!CheckKollision.checkmousewithobject((int) block.getwireconnector_right().x, (int) block.getwireconnector_right().y, 20, 20, (int) Var.mousepressedold.x, (int) Var.mousepressedold.y)) {


                            int feld = 2;
                            if (Math.abs(Var.mousepressedold.x - ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x) > feld || Math.abs(Var.mousepressedold.y - ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y) > feld) {
                                if (block.isMoving() == false && Var.openprojects.get(Var.openprojectindex).ismoving == false) {
                                    Var.openprojects.get(Var.openprojectindex).unterschiedsave.set(ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - block.getX(), ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - block.getY());


                                    DataManager.change(block, false, false);
                                    block.setMoving(true);
                                    Var.openprojects.get(Var.openprojectindex).ismoving = true;

                                }


                            }
                        }

                    }


                    if (block.isMoving() && Gdx.input.isButtonPressed(0)) {

                        block.setX((int) (ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - Var.openprojects.get(Var.openprojectindex).unterschiedsave.x));
                        block.setY((int) (ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - Var.openprojects.get(Var.openprojectindex).unterschiedsave.y));

                        //Wenn der Mauszeiger die Ablagefläche berührt
                        // if(CheckKollision.checkmousewithobject(,Gdx.input.getY())) {
                        // gotodelete = true;
                        // }else{
                        //gotodelete = false;
                        //}

                        if (block.getWire_left() != null) {
                            if (!block.getWire_left().isSpace_between_blocks()) {
                                if (block.getLeft() != -1) {
                                    Var.openprojects.get(Var.openprojectindex).blocks.get(block.getLeft()).setRight(-1);
                                    try {
                                        block.getWire_left().getLeft_connection().setWire_right(null); //löschen der Wire die zwischen den Blöcken war
                                    }catch (Exception e) {
                                        //Falls die Wire schon ein anderer Block gelöscht hat
                                    }
                                    Var.openprojects.get(Var.openprojectindex).wires.remove(block.getWire_left());
                                    block.setWire_left(null);


                                }
                                block.setLeft(-1);

                            }
                        }

                        if (block.getWire_right() != null) {
                            if (!block.getWire_right().isSpace_between_blocks()) {
                                if (block.getRight() != -1) {
                                    Var.openprojects.get(Var.openprojectindex).blocks.get(block.getRight()).setLeft(-1);

                                    block.getWire_right().getRight_connection().setWire_left(null);//löschen der Wire die zwischen den Blöcken war
                                    Var.openprojects.get(Var.openprojectindex).wires.remove(block.getWire_right());
                                    block.setWire_right(null);

                                }
                                block.setRight(-1);
                            }

                        }


                    } else if (block.isMoving()) {
                        Var.openprojects.get(Var.openprojectindex).ismoving = false;
                        block.setMoving(false);


                    }

                    if (Var.openprojects.get(Var.openprojectindex).markedblock == null && block.isMarked()) {
                        block.setMarked(false);
                    }


                    if (block.isShowdupulicate_links() || block.isShowdupulicate_rechts()) {
                        if (Var.openprojects.get(Var.openprojectindex).showduplicat.indexOf(block) == -1) {
                            Var.openprojects.get(Var.openprojectindex).showduplicat.add(block);
                        }
                    } else {
                        Var.openprojects.get(Var.openprojectindex).showduplicat.remove(block);
                        Var.openprojects.get(Var.openprojectindex).biggestblock = null;
                    }

                    //    System.out.println(Var.openprojects.get(Var.openprojectindex).showduplicat.size());
                    int biggestvalue = 0;
                    int biggestindex = -1;
                    for (int i = 0; i < Var.openprojects.get(Var.openprojectindex).showduplicat.size(); i++) {

                        try {
                            if (Var.openprojects.get(Var.openprojectindex).showduplicat.get(i).getDublicatmarkedblockuberlappungsflache() > biggestvalue) {
                                biggestvalue = Var.openprojects.get(Var.openprojectindex).showduplicat.get(i).getDublicatmarkedblockuberlappungsflache();
                                biggestindex = i;
                            }
                        } catch (Exception e) {
                            // e.printStackTrace();
                        }
                    }
                    try {
                        Var.openprojects.get(Var.openprojectindex).biggestblock = Var.openprojects.get(Var.openprojectindex).showduplicat.get(biggestindex);
                    } catch (IndexOutOfBoundsException e) {

                    }


                    try {


                        if (CheckKollision.object(Var.openprojects.get(Var.openprojectindex).markedblock.getX(), Var.openprojects.get(Var.openprojectindex).markedblock.getY(), Var.openprojects.get(Var.openprojectindex).markedblock.getH(), Var.openprojects.get(Var.openprojectindex).markedblock.getW(), block.getX(), block.getY(), block.getH(), block.getW()) && CheckKollision.flache(Var.openprojects.get(Var.openprojectindex).markedblock.getX(), Var.openprojects.get(Var.openprojectindex).markedblock.getY(), Var.openprojects.get(Var.openprojectindex).markedblock.getW(), Var.openprojects.get(Var.openprojectindex).markedblock.getH(), block.getX(), block.getY()) > 7000 && block != Var.openprojects.get(Var.openprojectindex).markedblock) {
                            // System.out.println("überschneidung von markedblock und einem block");

                            if (Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.indexOf(block) == -1) {
                                block.moved = false;

                                Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.add(block);
                            }
                        } else {
                            Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.remove(block);


                        }


                        int biggestvalue2 = 0;
                        int biggestindex2 = -1;

                        for (int i = 0; i < Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.size(); i++) {
                            //if(Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.get(i).)

                            //System.out.println("flaeche   " + Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.get(i).getBlockMarkedblockuberlappungsflache() + "i:   " + i);
                            try {
                                if (Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.get(i).getBlockMarkedblockuberlappungsflache() > biggestvalue2) {
                                    biggestvalue2 = Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.get(i).getBlockMarkedblockuberlappungsflache(); //TODO h
                                    biggestindex2 = i;
                                }
                            } catch (NullPointerException e) {
                            }
                        }

                        try {
                            if (Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.get(biggestindex2) != Var.openprojects.get(Var.openprojectindex).blockmitdergrostenuberlappungmitmarkiertemblock) {
                                Var.openprojects.get(Var.openprojectindex).blockmitdergrostenuberlappungmitmarkiertemblock = Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.get(biggestindex2);
                            }


                        } catch (IndexOutOfBoundsException e) {
                        }
                    } catch (NullPointerException e) {
                    }


                    try {

                        if (block == Var.openprojects.get(Var.openprojectindex).blockmitdergrostenuberlappungmitmarkiertemblock && block.moved == false && !geschoben && !block.getWire_left().isSpace_between_blocks() && !block.getWire_right().isSpace_between_blocks()) {
                            block.moved = true;
                            geschoben = true;

                            int a = block.getIndex();
                            //System.out.println(a);
                            block.setX(block.getX() + block.getW());

                            block.seted = false;

                                while (Var.openprojects.get(Var.openprojectindex).blocks.get(a).getRight() != -1) {

                                    //block.getRight().setX(block.getRight().getX() + block.getW());

                                    Var.openprojects.get(Var.openprojectindex).blocks.get(Var.openprojects.get(Var.openprojectindex).blocks.get(a).getRight()).setX(Var.openprojects.get(Var.openprojectindex).blocks.get(a).getX() + Var.openprojects.get(Var.openprojectindex).blocks.get(a).getW());
                                    a = Var.openprojects.get(Var.openprojectindex).blocks.get(a).getRight();
                                }


                        }


                    } catch (NullPointerException e) { //If there are no wires for example if you delete a block with two wires
                        if (block == Var.openprojects.get(Var.openprojectindex).blockmitdergrostenuberlappungmitmarkiertemblock && block.moved == false && !geschoben) {
                            block.moved = true;
                            geschoben = true;

                            int a = block.getIndex();
                            block.setX(block.getX() + block.getW());

                            block.seted = false;


                                while (Var.openprojects.get(Var.openprojectindex).blocks.get(a).getRight() != -1) {

                                    //block.getRight().setX(block.getRight().getX() + block.getW());

                                    Var.openprojects.get(Var.openprojectindex).blocks.get(Var.openprojects.get(Var.openprojectindex).blocks.get(a).getRight()).setX(Var.openprojects.get(Var.openprojectindex).blocks.get(a).getX() + Var.openprojects.get(Var.openprojectindex).blocks.get(a).getW());
                                    a = Var.openprojects.get(Var.openprojectindex).blocks.get(a).getRight();

                                }



                        }

                    }

                    if (block.seted == false && Var.openprojects.get(Var.openprojectindex).biggestblock == block && !Gdx.input.isButtonPressed(0)) { //TODO flackern wegmachen!

                        block.seted = true;
                        geschoben = false;
                    }

                    if (block.seted == false && Var.openprojects.get(Var.openprojectindex).biggestblock != block) {
                        geschoben = false;


                        int b = block.getIndex();

                        block.setX(block.getX() - block.getW());



                            while (Var.openprojects.get(Var.openprojectindex).blocks.get(b).getRight() != -1) {

                                //block.getRight().setX(block.getRight().getX() + block.getW());

                                Var.openprojects.get(Var.openprojectindex).blocks.get(Var.openprojects.get(Var.openprojectindex).blocks.get(b).getRight()).setX(Var.openprojects.get(Var.openprojectindex).blocks.get(Var.openprojects.get(Var.openprojectindex).blocks.get(b).getRight()).getX() - Var.openprojects.get(Var.openprojectindex).blocks.get(b).getW());//TODO tim ist sich nicht sicher
                                b = Var.openprojects.get(Var.openprojectindex).blocks.get(b).getRight();
                            }




                        block.seted = true;
                    }


                    if (!CheckKollision.checkmousewithblock(block) && Gdx.input.isButtonPressed(0) && !block.isMoving() && block.isMarked()) {
                        block.setMarked(false);
                        Var.openprojects.get(Var.openprojectindex).marked = false;
                        Var.openprojects.get(Var.openprojectindex).markedblock = null;
                    }


                    if (Var.openprojects.get(Var.openprojectindex).marked && !block.isMarked()) {
                        try {
                            if (CheckKollision.checkblockwithduplicate(Var.openprojects.get(Var.openprojectindex).markedblock, block, 0) && block.getRight() == -1 && Var.openprojects.get(Var.openprojectindex).markedblock.getWire_left() == null) {
                                if (Var.openprojects.get(Var.openprojectindex).markedblock.isMoving()) {
                                    //System.out.println("Kollision!");


                                    block.setShowdupulicate_rechts(true);


                                } else {


                                    if (block.getRight() != Var.openprojects.get(Var.openprojectindex).markedblock.getIndex() && Var.openprojects.get(Var.openprojectindex).markedblock.getLeft() != block.getIndex() && block.getRight() == -1 && Var.openprojects.get(Var.openprojectindex).biggestblock == block) {

                                        //System.out.println("test");
                                        block.setShowdupulicate_rechts(false);


                                        block.setWire_right( Var.openprojects.get(Var.openprojectindex).projectType.getWireGenerator().generate(block, Var.openprojects.get(Var.openprojectindex).markedblock));
                                        Var.openprojects.get(Var.openprojectindex).markedblock.setWire_left(block.getWire_right());
                                        Var.openprojects.get(Var.openprojectindex).wires.add(block.getWire_right());

                                        block.setRight(Var.openprojects.get(Var.openprojectindex).markedblock.getIndex());
                                        Var.openprojects.get(Var.openprojectindex).markedblock.setY(block.getY());
                                        Var.openprojects.get(Var.openprojectindex).markedblock.setX(block.getX_dup_rechts());
                                    }


                                }
                            } else {
                                block.setShowdupulicate_rechts(false);
                            }
                        }catch (Exception e) {

                        }


                        try {
                            if (CheckKollision.checkblockwithduplicate(Var.openprojects.get(Var.openprojectindex).markedblock, block, 1) && block.getLeft() == -1 && Var.openprojects.get(Var.openprojectindex).markedblock.getWire_right() == null) {

                                if (Var.openprojects.get(Var.openprojectindex).markedblock.isMoving()) {


                                    block.setShowdupulicate_links(true);


                                } else {


                                    if (block.getRight() != Var.openprojects.get(Var.openprojectindex).markedblock.getIndex() && Var.openprojects.get(Var.openprojectindex).markedblock.getLeft() != block.getIndex() && block.getLeft() == -1 && Var.openprojects.get(Var.openprojectindex).biggestblock == block) {


                                        block.setShowdupulicate_links(false);



                                        block.setWire_left( Var.openprojects.get(Var.openprojectindex).projectType.getWireGenerator().generate(Var.openprojects.get(Var.openprojectindex).markedblock,block));
                                        Var.openprojects.get(Var.openprojectindex).markedblock.setWire_right(block.getWire_left());
                                        Var.openprojects.get(Var.openprojectindex).wires.add(block.getWire_left());


                                        block.setLeft(Var.openprojects.get(Var.openprojectindex).markedblock.getIndex());
                                        Var.openprojects.get(Var.openprojectindex).markedblock.setY(block.getY());
                                        Var.openprojects.get(Var.openprojectindex).markedblock.setX(block.getX_dup_links());
                                    }


                                }
                            } else {
                                block.setShowdupulicate_links(false);
                            }

                        } catch (NullPointerException e) {

                        }
                    }
                } catch (Exception e) {
                    DisplayErrors.customErrorstring = "Fehler in einem "+block.getBlocktype().getName()+" Block mit der ID "+block.getIndex();
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
