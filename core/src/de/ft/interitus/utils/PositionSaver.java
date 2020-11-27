/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.Var;

public class PositionSaver {
    static Vector3 pos = new Vector3();
    static Vector3 pos2 = new Vector3();
    private static boolean isPressed = false;

    public static void save() {
        if (Gdx.input.isButtonJustPressed(0)) {
            isPressed = true;
            Var.mouseDownPos.set(ProgramingSpace.viewport.unproject(pos.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, ProgramingSpace.viewport.unproject(pos2.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y);
            Var.mousepressedoldwihoutunproject.set(Gdx.input.getX(), Gdx.input.getY());
        }

        if(!Gdx.input.isButtonPressed(0)&&isPressed) {
            isPressed = false;
            Var.mouseReleasePos.set(Unproject.unproject());
            Var.mouseReleasePosWithoutUnproject.set(Unproject.projected());
        }
    }
}

