/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.check;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Var;
import de.ft.interitus.utils.Unproject;

public class CheckMouse {
    private  final Vector2 mousesave = new Vector2();
    private  boolean touched;

    public boolean isJustPressed(int x, int y, int w, int h, boolean unproject) {
        if(!unproject) {


            if (!touched && Gdx.input.isButtonPressed(0) && CheckCollision.checkpointwithobject(x, y, w, h, Unproject.projected().x,Gdx.graphics.getHeight()-Unproject.projected().y)) {


                touched = true;
                System.out.println("first step done");
            }
            if(touched&&!Gdx.input.isButtonPressed(0)&&CheckCollision.checkpointwithobject(x,y,w,h,Var.mouseReleasePosWithoutUnproject.x,Gdx.graphics.getHeight()-Var.mouseReleasePosWithoutUnproject.y)) {
                touched = false;
                return true;
            }
        }else{
            if (!touched && Gdx.input.isButtonPressed(0) && CheckCollision.checkpointwithobject(x, y, w, h, Var.mouseDownPos)) {
                touched = true;
            }
            if(touched&&!Gdx.input.isButtonPressed(0)&&CheckCollision.checkpointwithobject(x,y,w,h,Var.mouseReleasePos)) {
                touched = false;
                return true;
            }

        }


        return false;


    }

    public static boolean isJustPressedNormal(int x, int y, int w, int h, boolean unproject) {

      if(!unproject) {
          return Gdx.input.getX() > x && Gdx.input.getX() < x + w && Gdx.input.getY() > Gdx.graphics.getHeight() - y - h && Gdx.input.getY() < Gdx.graphics.getHeight() - y && Gdx.input.isButtonJustPressed(0);
      }else{
          return CheckCollision.checkpointwithobject(x,y,w,h,Unproject.unproject().x,Unproject.unproject().y) && Gdx.input.isButtonJustPressed(0);
      }
    }

    public static boolean isMouseover(int x, int y, int w, int h, boolean unproject) {
        if(!unproject) {
            return Gdx.input.getX() > x && Gdx.input.getX() < x + w && Gdx.input.getY() > Gdx.graphics.getHeight() - y - h && Gdx.input.getY() < Gdx.graphics.getHeight() - y;
        }else{
                return CheckCollision.checkpointwithobject(x,y,w,h,Unproject.unproject().x,Unproject.unproject().y);

            }
        }
    public static boolean isMouseover(Rectangle r, boolean unproject) {
        if(!unproject) {
            return Gdx.input.getX() > r.x && Gdx.input.getX() < r.x + r.width && Gdx.input.getY() > Gdx.graphics.getHeight() - r.y - r.height && Gdx.input.getY() < Gdx.graphics.getHeight() - r.y;
        }else{
            return Unproject.unproject().x > r.x && Unproject.unproject().x < r.x + r.width && Unproject.unproject().y > Gdx.graphics.getHeight() - r.y - r.height && Unproject.unproject().y < Gdx.graphics.getHeight() - r.y;

        }
    }


    public static boolean isPressed(int x, int y, int w, int h, boolean unproject) {
        if(!unproject) {
            return Gdx.input.getX() > x && Gdx.input.getX() < x + w && Gdx.input.getY() > Gdx.graphics.getHeight() - y - h && Gdx.input.getY() < Gdx.graphics.getHeight() - y && Gdx.input.isButtonPressed(0);
        }else{
            return  CheckCollision.checkpointwithobject(x,y,w,h,Unproject.unproject().x,Unproject.unproject().y) && Gdx.input.isButtonPressed(0);

        }
    }

    public static boolean wasMousePressed(int x, int y, int w, int h) {
        return Var.mouseDownPosWithoutUnproject.x > x && Var.mouseDownPosWithoutUnproject.x < x + w && Var.mouseDownPosWithoutUnproject.y > Gdx.graphics.getHeight() - y - h && Var.mouseDownPosWithoutUnproject.y < Gdx.graphics.getHeight() - y && Gdx.input.isButtonPressed(0);
    }


}
