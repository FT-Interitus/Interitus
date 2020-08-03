/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.check;

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

    public static void remove(InputProcessor p) {
        multiplexer.removeProcessor(p);
    }

    public static boolean contains(InputProcessor p) {
        return multiplexer.getProcessors().contains(p, true);
    }
}
