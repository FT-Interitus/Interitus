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



            if(de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block, BlockVar.mousepressedold)&&Gdx.input.isButtonPressed(0) && BlockVar.ismoving==false&&!block.isMarked()&&!BlockVar.marked&&BlockVar.markedblock==null){
                MainGame.logger.debug("Marked Block" + block.getIndex());
                BlockVar.marked=true;
                block.setMarked(true);
               BlockVar.markedblock = block;
                BlockVar.unterschiedsave = new Vector2(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x  -  block.getX(),MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y  -  block.getY());

            }



            if( BlockVar.ismoving==false&&!block.isMoving()&&block.isMarked() && Gdx.input.isButtonPressed(0)){
                int feld=2;
                if(Math.abs(BlockVar.mousepressedold.x-MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x)>feld  ||  Math.abs(BlockVar.mousepressedold.y-MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y)>feld){
                    if(block.isMoving()==false  && BlockVar.ismoving==false) {
                        BlockVar.unterschiedsave = new Vector2(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x  -  block.getX(),MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y  -  block.getY());

                        //block.setX(    (int)MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x );
                        //System.out.println((int)MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-block.getX());
                        block.setMoving(true);
                        BlockVar.ismoving=true;
                        DataManager.change();
                    }


                }

            }

                if(block.isMoving() && Gdx.input.isButtonPressed(0)){
                    DataManager.change();
                     block.setX((int)(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-BlockVar.unterschiedsave.x));
                        block.setY((int)(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y-BlockVar.unterschiedsave.y));


                      if(block.getLeft()!=null) {
                          block.getLeft().setRight(null);
                      }
                      if(block.getRight()!=null) {
                          block.getRight().setLeft(null);
                      }
                    block.setRight(null);
                    block.setLeft(null);
                }else if(block.isMoving()){
                    BlockVar.ismoving=false;
                    block.setMoving(false);


                }



                if(block.isShowdupulicate_links() || block.isShowdupulicate_rechts()){
                    if(BlockVar.showduplicat.indexOf(block)==-1){
                        BlockVar.showduplicat.add(block);
                    }
                }else{
                    BlockVar.showduplicat.remove(block);
                    BlockVar.biggestblock=null;
                }

            //    System.out.println(BlockVar.showduplicat.size());
                int biggestvalue=0;
                int biggestindex=-1;
                for(int i=0;i<BlockVar.showduplicat.size();i++){

                    if(BlockVar.showduplicat.get(i).getFlaeche()>biggestvalue){
                        biggestvalue=BlockVar.showduplicat.get(i).getFlaeche();
                        biggestindex=i;
                    }
                }
                try {
                    BlockVar.biggestblock = BlockVar.showduplicat.get(biggestindex);
                }catch(IndexOutOfBoundsException e){

                }





            if(de.ft.robocontrol.utils.CheckKollision.checkmousewithblock(block)==false&& Gdx.input.isButtonPressed(0) && !block.isMoving()&&block.isMarked()){
                block.setMarked(false);
                BlockVar.marked=false;
                BlockVar.markedblock = null;
                }






    if(BlockVar.marked&&!block.isMarked()) {

        if(CheckKollision.checkblockwithduplicate(BlockVar.markedblock, block,0)&&block.getRight()==null) {
            if (BlockVar.markedblock.isMoving()) {
                System.out.println("Kollision!");

                block.setShowdupulicate_rechts(true);


            } else {


            if(block.getRight()!=BlockVar.markedblock&&BlockVar.markedblock.getLeft()!=block&&block.getRight()==null && BlockVar.biggestblock==block) {

                System.out.println("test");
                block.setShowdupulicate_rechts(false);
                block.setRight(BlockVar.markedblock);
                BlockVar.markedblock.setY(block.getY());
                BlockVar.markedblock.setX(block.getX_dup_rechts());
            }


            }
        }else{
            block.setShowdupulicate_rechts(false);
        }



        if(CheckKollision.checkblockwithduplicate(BlockVar.markedblock, block,1)&&block.getLeft()==null) {
            if (BlockVar.markedblock.isMoving()) {


                block.setShowdupulicate_links(true);


            } else {


                if(block.getRight()!=BlockVar.markedblock&&BlockVar.markedblock.getLeft()!=block&&block.getLeft()==null && BlockVar.biggestblock==block) {


                    block.setShowdupulicate_links(false);
                    block.setLeft(BlockVar.markedblock);
                    BlockVar.markedblock.setY(block.getY());
                    BlockVar.markedblock.setX(block.getX_dup_links());
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
                BlockVar.markedblock = null;
            }
            */


                    //System.out.println(block.getIndex()+ " " +de.ft.robocontrol.utils.CheckKollision.checkmousewithcar(block));
                    //System.out.println(block.getIndex());


                            }
        }, 0,20);

    }

}
