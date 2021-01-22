/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.check;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.UI.WindowAPI;
import de.ft.interitus.Var;
import de.ft.interitus.utils.Unproject;

public class CheckMouse {
    private final Vector2 mousesave = new Vector2();
    private boolean touched;

    public static boolean isJustPressedNormal(int x, int y, int w, int h, boolean unproject) {

        if (!unproject) {
            return WindowAPI.getX() > x && WindowAPI.getX() < x + w && WindowAPI.getY() > WindowAPI.getHeight() - y - h && WindowAPI.getY() < WindowAPI.getHeight() - y && WindowAPI.isButtonJustPressed(0);
        } else {
            return CheckCollision.checkpointwithobject(x, y, w, h, Unproject.unproject().x, Unproject.unproject().y) && WindowAPI.isButtonJustPressed(0);
        }
    }

    public static boolean isMouseover(int x, int y, int w, int h, boolean unproject) {
        if (!unproject) {
            return WindowAPI.getX() > x && WindowAPI.getX() < x + w && WindowAPI.getY() > WindowAPI.getHeight() - y - h && WindowAPI.getY() < WindowAPI.getHeight() - y;
        } else {
            return CheckCollision.checkpointwithobject(x, y, w, h, Unproject.unproject().x, Unproject.unproject().y);

        }
    }

    public static boolean isMouseover(Rectangle r, boolean unproject) {
        if (!unproject) {
            return WindowAPI.getX() > r.x && WindowAPI.getX() < r.x + r.width && WindowAPI.getY() > WindowAPI.getHeight() - r.y - r.height && WindowAPI.getY() < WindowAPI.getHeight() - r.y;
        } else {
            return Unproject.unproject().x > r.x && Unproject.unproject().x < r.x + r.width && Unproject.unproject().y > WindowAPI.getHeight() - r.y - r.height && Unproject.unproject().y < WindowAPI.getHeight() - r.y;

        }
    }

    public static boolean isPressed(int x, int y, int w, int h, boolean unproject) {
        if (!unproject) {
            return WindowAPI.getX() > x && WindowAPI.getX() < x + w && WindowAPI.getY() > WindowAPI.getHeight() - y - h && WindowAPI.getY() < WindowAPI.getHeight() - y && WindowAPI.isButtonPressed(0);
        } else {
            return CheckCollision.checkpointwithobject(x, y, w, h, Unproject.unproject().x, Unproject.unproject().y) && WindowAPI.isButtonPressed(0);

        }
    }

    public static boolean wasMousePressed(int x, int y, int w, int h) {
        return Var.mouseDownPosWithoutUnproject.x > x && Var.mouseDownPosWithoutUnproject.x < x + w && Var.mouseDownPosWithoutUnproject.y > WindowAPI.getHeight() - y - h && Var.mouseDownPosWithoutUnproject.y < WindowAPI.getHeight() - y && WindowAPI.isButtonPressed(0);
    }

    public boolean isJustPressed(int x, int y, int w, int h, boolean unproject) {
        if (!unproject) {


            if (!touched && WindowAPI.isButtonPressed(0) && CheckCollision.checkpointwithobject(x, y, w, h, Unproject.projected().x, WindowAPI.getHeight() - Unproject.projected().y)) {


                touched = true;
                System.out.println("first step done");
            }
            if (touched && !WindowAPI.isButtonPressed(0) && CheckCollision.checkpointwithobject(x, y, w, h, Var.mouseReleasePosWithoutUnproject.x, WindowAPI.getHeight() - Var.mouseReleasePosWithoutUnproject.y)) {
                touched = false;
                return true;
            }
        } else {
            if (!touched && WindowAPI.isButtonPressed(0) && CheckCollision.checkpointwithobject(x, y, w, h, Var.mouseDownPos)) {
                touched = true;
            }
            if (touched && !WindowAPI.isButtonPressed(0) && CheckCollision.checkpointwithobject(x, y, w, h, Var.mouseReleasePos)) {
                touched = false;
                return true;
            }

        }


        return false;


    }


}
