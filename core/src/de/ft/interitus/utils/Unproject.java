/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.UI.WindowAPI;

public class Unproject {

    static Vector2 temp1 = new Vector2();
    static Vector3 temp3 = new Vector3();
    static Vector3 temp4 = new Vector3();
    static Vector2 temp5 = new Vector2();

    public static Vector2 unproject() {
        return temp1.set(ProgramingSpace.cam.unproject(temp3.set(WindowAPI.getX(), WindowAPI.getY(), 0)).x, ProgramingSpace.cam.unproject(temp4.set(WindowAPI.getX(), WindowAPI.getY(), 0)).y);

    }
    public static Vector2 unproject(float X,float Y) {
        return temp1.set(ProgramingSpace.viewport.unproject(temp1.set(X,Y)));
    }

    public static Vector2 projected() {
        return temp5.set(WindowAPI.getX(), WindowAPI.getY());
    }

}
