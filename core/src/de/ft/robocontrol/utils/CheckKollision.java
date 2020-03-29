package de.ft.robocontrol.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.MainGame;

public class CheckKollision {

    private static int mousesize=0;

    public static boolean object(int obj1_x, int obj1_y, int obj1_h, int obj1_w, int obj2_x, int obj2_y, int obj2_h, int obj2_w) {

        Rectangle obj1 = new Rectangle(obj1_x, obj1_y, obj1_w, obj1_h);
        Rectangle obj2 = new Rectangle(obj2_x, obj2_y, obj2_w, obj2_h);

        return obj1.overlaps(obj2);

    }

    public static boolean checkmousewithblock(Block block) {


            return object((int) block.getX(), (int) block.getY(), (int) block.getH(), (int) block.getW(), (int)MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x, (int) MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y, (int) mousesize, (int) mousesize);

        }


    public static boolean checkmousewithobject(int obj1_x, int obj1_y, int obj1_h, int obj1_w, Vector2 mousepos) {


        return object(obj1_x, obj1_y, obj1_w, obj1_h, (int) mousepos.x, (int)mousepos.y,  (int) mousesize, (int) mousesize);

    }


    public static boolean checkmousewithblock(Block block, Vector2 mousepos) {


        return object((int) block.getX(), (int) block.getY(), (int) block.getH(), (int) block.getW(), (int) mousepos.x, (int)mousepos.y,  (int) mousesize, (int) mousesize);

    }

    public static boolean checkblockwithduplicate(Block normal, Block duplicate,int rl) {
        if(rl==0) {
            return object(normal.getX(), normal.getY(), normal.getH(), normal.getW(), duplicate.getX_dup_rechts(), duplicate.getY(), duplicate.getH(), 60);
        }else{
            return object(normal.getX(), normal.getY(), normal.getH(), normal.getW(), duplicate.getX_dup_links()+duplicate.getW()-60, duplicate.getY(), duplicate.getH(), 60);
        }
    }


    public static int flache(int x,int y,int w,int h,int xm,int ym){
        int flache=0;

        int xx = w - (xm - x);
        int yy = (ym + h - y);
        if(xx>w){
            xx=w-(xx-w);
        }
        if(yy>h){
            yy=h-(yy-h);
        }

        //System.out.println("y: " + yy + "x: " + xx + "flache: "+ yy*xx);
        flache=xx*yy;
        return flache;
    }


    }

