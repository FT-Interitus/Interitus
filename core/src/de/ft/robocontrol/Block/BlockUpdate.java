package de.ft.robocontrol.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.data.programm.DataManager;
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



            if(de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block, GlobalBlockVar.mousepressedold)&&Gdx.input.isButtonPressed(0) && GlobalBlockVar.ismoving==false&&!block.isMarked()&&!GlobalBlockVar.marked&&GlobalBlockVar.markedblock==null){
                MainGame.logger.debug("Marked Block" + block.getIndex());
                GlobalBlockVar.marked=true;
                block.setMarked(true);
               GlobalBlockVar.markedblock = block;
                GlobalBlockVar.unterschiedsave = new Vector2(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x  -  block.getX(),MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y  -  block.getY());

            }



            if( GlobalBlockVar.ismoving==false&&!block.isMoving()&&block.isMarked() && Gdx.input.isButtonPressed(0)){
                int feld=2;
                if(Math.abs(GlobalBlockVar.mousepressedold.x-MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x)>feld  ||  Math.abs(GlobalBlockVar.mousepressedold.y-MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y)>feld){
                    if(block.isMoving()==false  && GlobalBlockVar.ismoving==false) {
                        GlobalBlockVar.unterschiedsave = new Vector2(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x  -  block.getX(),MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y  -  block.getY());

                        //block.setX(    (int)MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x );
                        //System.out.println((int)MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-block.getX());
                        block.setMoving(true);
                        GlobalBlockVar.ismoving=true;
                        DataManager.change();
                    }


                }

            }

                if(block.isMoving() && Gdx.input.isButtonPressed(0)){
                    DataManager.change();
                     block.setX((int)(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-GlobalBlockVar.unterschiedsave.x));
                        block.setY((int)(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y-GlobalBlockVar.unterschiedsave.y));


                      if(block.getLeft()!=null) {
                          block.getLeft().setRight(null);
                      }
                      if(block.getRight()!=null) {
                          block.getRight().setLeft(null);
                      }
                    block.setRight(null);
                    block.setLeft(null);
                }else if(block.isMoving()){
                    GlobalBlockVar.ismoving=false;
                    block.setMoving(false);


                }



                if(block.isShowdupulicate_links() || block.isShowdupulicate_rechts()){
                    if(GlobalBlockVar.showduplicat.indexOf(block)==-1){
                        GlobalBlockVar.showduplicat.add(block);
                    }
                }else{
                    GlobalBlockVar.showduplicat.remove(block);
                    GlobalBlockVar.biggestblock=null;
                }

            //    System.out.println(GlobalBlockVar.showduplicat.size());
                int biggestvalue=0;
                int biggestindex=-1;
                for(int i=0;i<GlobalBlockVar.showduplicat.size();i++){

                    if(GlobalBlockVar.showduplicat.get(i).getFlaeche()>biggestvalue){
                        biggestvalue=GlobalBlockVar.showduplicat.get(i).getFlaeche();
                        biggestindex=i;
                    }
                }
                try {
                    GlobalBlockVar.biggestblock = GlobalBlockVar.showduplicat.get(biggestindex);
                }catch(IndexOutOfBoundsException e){

                }





            if(de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block)==false&& Gdx.input.isButtonPressed(0) && !block.isMoving()&&block.isMarked()){
                block.setMarked(false);
                GlobalBlockVar.marked=false;
                GlobalBlockVar.markedblock = null;
                }






    if(GlobalBlockVar.marked&&!block.isMarked()) {

        if(CheckKollision.checkblockwithduplicate(GlobalBlockVar.markedblock, block,0)&&block.getRight()==null) {
            if (GlobalBlockVar.markedblock.isMoving()) {
                System.out.println("Kollision!");

                block.setShowdupulicate_rechts(true);


            } else {


            if(block.getRight()!=GlobalBlockVar.markedblock&&GlobalBlockVar.markedblock.getLeft()!=block&&block.getRight()==null && GlobalBlockVar.biggestblock==block) {

                System.out.println("test");
                block.setShowdupulicate_rechts(false);
                block.setRight(GlobalBlockVar.markedblock);
                GlobalBlockVar.markedblock.setY(block.getY());
                GlobalBlockVar.markedblock.setX(block.getX_dup_rechts());
            }


            }
        }else{
            block.setShowdupulicate_rechts(false);
        }



        if(CheckKollision.checkblockwithduplicate(GlobalBlockVar.markedblock, block,1)&&block.getLeft()==null) {
            if (GlobalBlockVar.markedblock.isMoving()) {


                block.setShowdupulicate_links(true);


            } else {


                if(block.getRight()!=GlobalBlockVar.markedblock&&GlobalBlockVar.markedblock.getLeft()!=block&&block.getLeft()==null && GlobalBlockVar.biggestblock==block) {


                    block.setShowdupulicate_links(false);
                    block.setLeft(GlobalBlockVar.markedblock);
                    GlobalBlockVar.markedblock.setY(block.getY());
                    GlobalBlockVar.markedblock.setX(block.getX_dup_links());
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
                GlobalBlockVar.markedblock = null;
            }
            */


                    //System.out.println(block.getIndex()+ " " +de.ft.robocontrol.utils.CheckKollision.checkmousewithcar(block));
                    //System.out.println(block.getIndex());


                            }
        }, 0,20);

    }

}
