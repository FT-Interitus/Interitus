/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import com.badlogic.gdx.Gdx;
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
            if (ProgramingSpace.cam != null)
                Var.mouseDownPos.set(Unproject.unproject());
            Var.mouseDownPosWithoutUnproject.set(Gdx.input.getX(), Gdx.input.getY());
        }

        if (!Gdx.input.isButtonPressed(0) && isPressed) {
            isPressed = false;
            if (ProgramingSpace.cam != null)
                Var.mouseReleasePos.set(Unproject.unproject());
            Var.mouseReleasePosWithoutUnproject.set(Gdx.input.getX(), Gdx.input.getY());
        }
    }
}

