package de.ft.robocontrol.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.Block.BlockVar;
import de.ft.robocontrol.ProgrammingSpace;

public class PositionSaver {
    Vector2 saver = new Vector2();
    static Vector3 pos = new Vector3();
    static Vector3 pos2 = new Vector3();
    public static void save() {
        if (Gdx.input.isButtonJustPressed(0)) {
            BlockVar.mousepressedold.set(ProgrammingSpace.viewport.unproject(pos.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, ProgrammingSpace.viewport.unproject(pos2.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y);
        }
    }
}

