package de.ft.robocontrol.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.data.programm.Data;
import de.ft.robocontrol.data.user.DataManager;
import de.ft.robocontrol.utils.CheckKollision;

import java.util.Timer;
import java.util.TimerTask;


public class BlockUpdate extends Thread {
Block block;
boolean toggle;



public Timer time;

    BlockUpdate(Block block) {
        this.block=block;

    }

    @Override
    public void run() {
        time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {


            toggle = de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block);



            if(de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block, Var.mousepressedold)&&Gdx.input.isButtonPressed(0) && Var.ismoving==false&&!block.isMarked()&&!Var.marked&&Var.markedblock==null){
                MainGame.logger.debug("Marked Block" + block.getIndex());
                Var.marked=true;
                block.setMarked(true);
               Var.markedblock = block;
                Var.unterschiedsave = new Vector2(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x  -  block.getX(),MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y  -  block.getY());

            }



            if( Var.ismoving==false&&!block.isMoving()&&block.isMarked() && Gdx.input.isButtonPressed(0)){
                int feld=2;
                if(Math.abs(Var.mousepressedold.x-MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x)>feld  ||  Math.abs(Var.mousepressedold.y-MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y)>feld){
                    if(block.isMoving()==false  && Var.ismoving==false) {
                        Var.unterschiedsave = new Vector2(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x  -  block.getX(),MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y  -  block.getY());

                        //block.setX(    (int)MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x );
                        //System.out.println((int)MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-block.getX());
                        block.setMoving(true);
                        Var.ismoving=true;
                        DataManager.change();
                    }


                }

            }

                if(block.isMoving() && Gdx.input.isButtonPressed(0)){
                    DataManager.change();
                     block.setX((int)(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-Var.unterschiedsave.x));
                        block.setY((int)(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y-Var.unterschiedsave.y));


                      if(block.getLeft()!=null) {
                          block.getLeft().setRight(null);
                      }
                      if(block.getRight()!=null) {
                          block.getRight().setLeft(null);
                      }
                    block.setRight(null);
                    block.setLeft(null);
                }else if(block.isMoving()){
                    Var.ismoving=false;
                    block.setMoving(false);


                }


            if(de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block)==false&& Gdx.input.isButtonPressed(0) && !block.isMoving()&&block.isMarked()){
                block.setMarked(false);
                Var.marked=false;
                Var.markedblock = null;
                }
if(block.isShowdupulicate_rechts()) {

    if (Var.markedblock.getX() > block.getX() + block.getW() && Var.markedblock.getY()<block.getY()) {

        try {

            int xx = block.getW() - (Var.markedblock.getX() - block.getX_dup_rechts());
            int yy = (Var.markedblock.getY() + Var.markedblock.getH()) - block.getY();
            System.out.println("y: " + yy + "x: " + xx + "flache: "+ yy*xx);
        } catch (NullPointerException e) {

        }


    }else{


        try {

            int xx = block.getW()-((block.getW() - (Var.markedblock.getX() - block.getX_dup_rechts()))-block.getW());
            int yy = block.getH()-(((Var.markedblock.getY() + Var.markedblock.getH()) - block.getY())-block.getH());
            System.out.println("y: " + yy + "x: " + xx + "flache: "+ yy*xx);
        } catch (NullPointerException e) {

        }


    }
}

    if(Var.marked&&!block.isMarked()) {

        if(CheckKollision.checkblockwithduplicate(Var.markedblock, block,0)&&block.getRight()==null) {
            if (Var.markedblock.isMoving()) {
                System.out.println("Kollision!");

                block.setShowdupulicate_rechts(true);


            } else {


            if(block.getRight()!=Var.markedblock&&Var.markedblock.getLeft()!=block&&block.getRight()==null) {

                System.out.println("test");
                block.setShowdupulicate_rechts(false);
                block.setRight(Var.markedblock);
                Var.markedblock.setY(block.getY());
                Var.markedblock.setX(block.getX_dup_rechts());
            }


            }
        }else{
            block.setShowdupulicate_rechts(false);
        }



        if(CheckKollision.checkblockwithduplicate(Var.markedblock, block,1)&&block.getLeft()==null) {
            if (Var.markedblock.isMoving()) {


                block.setShowdupulicate_links(true);


            } else {


                if(block.getRight()!=Var.markedblock&&Var.markedblock.getLeft()!=block&&block.getLeft()==null) {


                    block.setShowdupulicate_links(false);
                    block.setLeft(Var.markedblock);
                    Var.markedblock.setY(block.getY());
                    Var.markedblock.setX(block.getX_dup_links());
                }


            }
        }else{
            block.setShowdupulicate_links(false);
        }

    }




          //block.setIndex( MainGame.blocks.indexOf(block));

/*

            if(toggle && Gdx.input.isButtonPressed(0) && !Var.indraganddrop){
                block.setMarked(true);
                Var.markedblock = block;
                Var.indraganddrop = true;

            }


            if(!Gdx.input.isButtonPressed(0)) {
                Var.indraganddrop=false;
            }




            if(toggle&&!block.isMarked()) {
                Var.markedblock.setMarked(false);
                block.setMarked(true);
                Var.markedblock = block;
            }

            if(!toggle && Gdx.input.isButtonPressed(0)&&!Var.indraganddrop){
                block.setMarked(false);
                Var.markedblock = null;
            }
            */


                    //System.out.println(block.getIndex()+ " " +de.ft.robocontrol.utils.CheckKollision.checkmousewithcar(block));
                    //System.out.println(block.getIndex());


                            }
        }, 0,20);

    }

}
