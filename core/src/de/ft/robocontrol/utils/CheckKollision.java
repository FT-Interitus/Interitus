package de.ft.robocontrol.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.ProgrammingSpace;

public class CheckKollision {

    Vector2 temp1 = new Vector2();
    Vector2 temp2 = new Vector2();
    static Vector3 temp3 = new Vector3();
    static Vector3 temp4 = new Vector3();
    static Rectangle rec1 = new Rectangle();
    static Rectangle rec2 = new Rectangle();
    private static int mousesize = 0;

    public static boolean object(float obj1_x, float obj1_y, float obj1_h, float obj1_w, float obj2_x, float obj2_y, float obj2_h, float obj2_w) {


        rec1.set(obj1_x, obj1_y, obj1_w, obj1_h);
        rec2.set(obj2_x, obj2_y, obj2_h, obj2_w);

        return rec1.overlaps(rec2);

    }

    public static boolean checkmousewithblock(Block block) {


        return object(block.getX(), block.getY(), block.getH(), block.getW(), (int) ProgrammingSpace.viewport.unproject(temp3.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(temp4.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y, mousesize, mousesize);

    }


    public static boolean checkmousewithobject(int obj1_x, int obj1_y, int obj1_h, int obj1_w, Vector2 mousepos) {


        return object(obj1_x, obj1_y, obj1_w, obj1_h, (int) mousepos.x, (int) mousepos.y, mousesize, mousesize);

    }

    public static boolean checkmousewithobject(int obj1_x, int obj1_y, int obj1_h, int obj1_w, int x, int y) {


        return object(obj1_x, obj1_y, obj1_w, obj1_h, x, y, mousesize, mousesize);

    }


    public static boolean checkmousewithblock(Block block, Vector2 mousepos) {


        return object(block.getX(), block.getY(), block.getH(), block.getW(), (int) mousepos.x, (int) mousepos.y, mousesize, mousesize);

    }

    public static boolean checkblockwithduplicate(Block normal, Block duplicate, int rl) {
        if (rl == 0) {
            return object(normal.getX(), normal.getY(), normal.getH(), normal.getW(), duplicate.getX_dup_rechts(), duplicate.getY(), duplicate.getH(), 60);
        } else {
            return object(normal.getX(), normal.getY(), normal.getH(), normal.getW(), duplicate.getX_dup_links() + duplicate.getW() - 60, duplicate.getY(), duplicate.getH(), 60);
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

        //System.out.println("y: " + yy + "x: " + xx + "flache: "+ yy*xx);
        flache = xx * yy;
        return flache;
    }


}

