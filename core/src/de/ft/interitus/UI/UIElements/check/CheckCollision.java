/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.check;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import de.ft.interitus.Block.Block;
import de.ft.interitus.ProgramingSpace;

public class CheckCollision {

    private static final int MOUSESIZE = 1;
    private final static Vector3 temp3 = new Vector3();
    private final static Vector3 temp4 = new Vector3();
    private final static Vector2 tempVec = new Vector2();
    private final static Rectangle rec1 = new Rectangle();
    private final static Rectangle rec2 = new Rectangle();


    public static boolean object(float obj1_x, float obj1_y, float obj1_w, float obj1_h, float obj2_x, float obj2_y, float obj2_w, float obj2_h) {


        rec1.set(obj1_x, obj1_y, obj1_w, obj1_h);
        rec2.set(obj2_x, obj2_y, obj2_w,obj2_h);

        return rec1.overlaps(rec2);

    }

    public static boolean rectCollision(java.awt.Rectangle rect1, java.awt.Rectangle rect2){
        java.awt.Rectangle checkrect=new java.awt.Rectangle(rect1);
        if(rect1.getWidth()>=0 && rect1.getHeight()>=0){
            return checkrect.intersects(rect2);
        }else if(rect1.getWidth()<0 && rect1.getHeight()>=0){
            checkrect.setLocation((int)(rect1.getX()+rect1.getWidth()),(int)(rect1.getY()));
            checkrect.setSize((int)Math.abs(rect1.getWidth()),(int)Math.abs(rect1.getHeight()));
            return checkrect.intersects(rect2);
        }else if(rect1.getWidth()>=0 && rect1.getHeight()<0){
            checkrect.setLocation((int)(rect1.getX()),(int)(rect1.getY()+rect1.getHeight()));
            checkrect.setSize((int)Math.abs(rect1.getWidth()),(int)Math.abs(rect1.getHeight()));
            return checkrect.intersects(rect2);
        }else if(rect1.getWidth()<0 && rect1.getHeight()<0){
            checkrect.setLocation((int)(rect1.getX()+rect1.getWidth()),(int)(rect1.getY()+rect1.getHeight()));
            checkrect.setSize((int)(Math.abs(rect1.getWidth())),(int)(Math.abs(rect1.getHeight())));
            return checkrect.intersects(rect2);
        }else{
            return false;
        }


    }

    public static boolean objectwithrotation(float obj1_x, float obj1_y, float obj1_w, float obj1_h, float obj1_angle, float obj2_x, float obj2_y, int obj2_h, int obj2_w, float obj2_angle) {


        Polygon obj1 = new Polygon(new float[]{0, 0, obj1_w, 0, obj1_w, obj1_h, 0, obj1_h});
        Polygon obj2 = new Polygon(new float[]{0, 0, obj2_w, 0, obj2_w, obj2_h, 0, obj2_h});
        obj1.setOrigin(0, 0);
        obj2.setOrigin(0, 0);
        obj1.setPosition(obj1_x, obj1_y);
        obj2.setPosition(obj2_x, obj2_y);
        obj1.setRotation(obj1_angle);
        obj2.setRotation(obj2_angle);

        return Intersector.overlapConvexPolygons(obj1, obj2);

    }

    public static boolean checkmousewithblock(Block block) {


        return object(block.getX(), block.getY(), block.getW(), block.getH(), (int) ProgramingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgramingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y, MOUSESIZE, MOUSESIZE);

    }


    public static boolean checkpointwithobject(int obj1_x, int obj1_y, int obj1_w, int obj1_h, Vector2 position) {


        return object(obj1_x, obj1_y, obj1_w, obj1_h, (int) position.x, (int) position.y, MOUSESIZE, MOUSESIZE);

    }

    public static boolean checkpointwithobject(int obj1_x, int obj1_y, int obj1_w, int obj1_h, float x, float y) {


        return object(obj1_x, obj1_y, obj1_w, obj1_h, x, y, MOUSESIZE, MOUSESIZE);

    }


    public static boolean checkVectorWithBlock(Block block, Vector2 mousepos) {


        return object(block.getX(), block.getY(), block.getW(), block.getH(), (int) mousepos.x, (int) mousepos.y, MOUSESIZE, MOUSESIZE);

    }

    public static boolean checkVectors(Vector2 vector1,int w,int h,Vector2 vector2, int w2, int h2) {

        return object(vector1.x,vector1.y,w,h,vector2.x,vector2.y,w2,h2);

    }

    public static boolean checkblockwithduplicate(Block normal, Block duplicate, int rl) {
        if (rl == 0) { //0=Right
            return object(normal.getX(), normal.getY(), normal.getW(), normal.getH(), duplicate.getRightDuplicatePosition(), duplicate.getY(), normal.getW() / 1.5f, duplicate.getH());
        } else {
            return object(normal.getX(), normal.getY(), normal.getW(), normal.getH(), duplicate.getX() - normal.getW() / 1.5f, duplicate.getY(), normal.getW() / 1.5f, duplicate.getH());
        }
    }


    public static int flache(int x, int y, int w, int h, int xm, int ym) {
        int flache = 0;

        int xx = w - (xm - x);
        int yy = (ym + h - y);
        if (xx > w) {
            xx = w - (xx - w);
        }
        if (yy > h) {
            yy = h - (yy - h);
        }

        //Program.logger.config("y: " + yy + "x: " + xx + "flache: "+ yy*xx);
        flache = xx * yy;
        return flache;
    }

    public static boolean checkCircleWithVector(int radius, int x, int y, Vector2 vector2) {

        return Vector2.dst(vector2.x,Gdx.graphics.getHeight()-vector2.y,x,y)<radius;

    }


}

