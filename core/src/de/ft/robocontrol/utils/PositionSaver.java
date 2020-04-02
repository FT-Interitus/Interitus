package de.ft.robocontrol.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.Block.BlockVar;
import de.ft.robocontrol.MainGame;

public class PositionSaver {
    public static void save() {
        if (Gdx.input.isButtonJustPressed(0)) {
            BlockVar.mousepressedold = new Vector2(MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x, MainGame.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y);
        }
    }
}

