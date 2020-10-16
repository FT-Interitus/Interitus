/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.check;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Var;
import de.ft.interitus.utils.Unproject;

public class CheckMouse {
    private static final Vector2 mousesave = new Vector2();
    private static boolean touched;

    public static boolean isjustPressed(int x, int y, int w, int h, boolean unproject) {
        boolean pressed = false;

        if (Gdx.input.isButtonPressed(0)) {
            if(!unproject) {
                if (Gdx.input.getX() > x && Gdx.input.getX() < x + w && Gdx.input.getY() > Gdx.graphics.getHeight() - y - h && Gdx.input.getY() < Gdx.graphics.getHeight() - y) {
                    if (touched == false) {
                        mousesave.set(Gdx.input.getX(), Gdx.input.getY());
                        touched = true;
                    }
                }
            }else{
                if (Unproject.unproject().x > x && Unproject.unproject().x < x + w && Unproject.unproject().y > Gdx.graphics.getHeight() - y - h && Unproject.unproject().y < Gdx.graphics.getHeight() - y) {
                    if (touched == false) {
                        mousesave.set(Unproject.unproject().x, Unproject.unproject().y);
                        touched = true;
                    }
                }
            }

        }
        if(!unproject) {
            if (!Gdx.input.isButtonPressed(0) && Math.abs(Gdx.input.getX() - mousesave.x) < 1 && Math.abs(Gdx.input.getY() - mousesave.y) < 1 && touched) {

                pressed = true;
                touched = false;
            } else {
                pressed = false;
            }
            if (Math.abs(Gdx.input.getX() - mousesave.x) > 1 && Math.abs(Gdx.input.getY() - mousesave.y) > 1 && touched && !Gdx.input.isButtonPressed(0)) {
                pressed = false;
                touched = false;
            }
        }else {
            if (!Gdx.input.isButtonPressed(0) && Math.abs(Unproject.unproject().x - mousesave.x) < 1 && Math.abs(Unproject.unproject().x - mousesave.y) < 1 && touched) {

                pressed = true;
                touched = false;
            } else {
                pressed = false;
            }
            if (Math.abs(Unproject.unproject().x - mousesave.x) > 1 && Math.abs(Unproject.unproject().y - mousesave.y) > 1 && touched && !Gdx.input.isButtonPressed(0)) {
                pressed = false;
                touched = false;
            }
        }


        return pressed;

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
        return Var.mousepressedoldwihoutunproject.x > x && Var.mousepressedoldwihoutunproject.x < x + w && Var.mousepressedoldwihoutunproject.y > Gdx.graphics.getHeight() - y - h && Var.mousepressedoldwihoutunproject.y < Gdx.graphics.getHeight() - y && Gdx.input.isButtonPressed(0);
    }


}
