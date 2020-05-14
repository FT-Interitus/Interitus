package de.ft.interitus.UI.input.check;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputManager {
    public static InputMultiplexer multiplexer = new InputMultiplexer();

    public static void updateMultiplexer() {
        Gdx.input.setInputProcessor(multiplexer);
    }

    public static void addProcessor(InputProcessor p) {
        multiplexer.addProcessor(p);
    }
}
