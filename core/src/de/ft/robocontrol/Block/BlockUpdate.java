package de.ft.robocontrol.Block;

import com.badlogic.gdx.Gdx;
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
    Vector2 temp2;
    Vector3 temp3;
    Vector3 temp4;

    BlockUpdate(Block block) {
        this.block = block; //Der Block wird zu gewiesen

        temp1 = new Vector2(0,0);//Temp Vectoren init
        temp2 = new Vector2(0,0);
        temp3 = new Vector3(0,0,0);
        temp4 = new Vector3(0,0,0);
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

                    toggle = de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block); //Wird der Block von der Mouse gehovert

                    //TODO
                    //TODO      Kommentierung fortsetzen (Abgebrochen da hier noch sehr viele Änderungen passieren werden)
                    //TODO


                    if (de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block, BlockVar.mousepressedold) && Gdx.input.isButtonPressed(0) && BlockVar.ismoving == false && !block.isMarked() && !BlockVar.marked && BlockVar.markedblock == null) {
                        // MainGame.logger.debug("Marked Block" + block.getIndex());
                        BlockVar.marked = true;
                        block.setMarked(true);
                        BlockVar.markedblock = block;
                        BlockVar.unterschiedsave.set(ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - block.getX(), ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - block.getY());

                    }


                    if (BlockVar.ismoving == false && !block.isMoving() && block.isMarked() && Gdx.input.isButtonPressed(0)) {
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


                    if (block.isMoving() && Gdx.input.isButtonPressed(0)) {

                        block.setX((int) (ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - BlockVar.unterschiedsave.x));
                        block.setY((int) (ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - BlockVar.unterschiedsave.y));

                        //Wenn der Mauszeiger die Ablagefläche berührt
                        // if(CheckKollision.checkmousewithobject(,Gdx.input.getY())) {
                        // gotodelete = true;
                        // }else{
                        //gotodelete = false;
                        //}

                        if (block.getLeft() != null) {
                            block.getLeft().setRight(null);
                        }
                        if (block.getRight() != null) {
                            block.getRight().setLeft(null);
                        }
                        block.setRight(null);
                        block.setLeft(null);
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

                        if (CheckKollision.checkblockwithduplicate(BlockVar.markedblock, block, 0) && block.getRight() == null) {
                            if (BlockVar.markedblock.isMoving()) {
                                //System.out.println("Kollision!");

                                block.setShowdupulicate_rechts(true);


                            } else {


                                if (block.getRight() != BlockVar.markedblock && BlockVar.markedblock.getLeft() != block && block.getRight() == null && BlockVar.biggestblock == block) {

                                    //System.out.println("test");
                                    block.setShowdupulicate_rechts(false);
                                    block.setRight(BlockVar.markedblock);
                                    BlockVar.markedblock.setY(block.getY());
                                    BlockVar.markedblock.setX(block.getX_dup_rechts());
                                }


                            }
                        } else {
                            block.setShowdupulicate_rechts(false);
                        }


                        if (CheckKollision.checkblockwithduplicate(BlockVar.markedblock, block, 1) && block.getLeft() == null) {  //TODO Fehler beheben
                            if (BlockVar.markedblock.isMoving()) {


                                block.setShowdupulicate_links(true);


                            } else {


                                if (block.getRight() != BlockVar.markedblock && BlockVar.markedblock.getLeft() != block && block.getLeft() == null && BlockVar.biggestblock == block) {


                                    block.setShowdupulicate_links(false);
                                    block.setLeft(BlockVar.markedblock);
                                    BlockVar.markedblock.setY(block.getY());
                                    BlockVar.markedblock.setX(block.getX_dup_links());
                                }


                            }
                        } else {
                            block.setShowdupulicate_links(false);
                        }

                    }
                }catch (Exception e){
                    e.printStackTrace(); //FOR Debug to find errors
                }

            }
        }, 0, 20);

    }

}
