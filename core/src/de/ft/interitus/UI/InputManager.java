/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputManager {
    public InputMultiplexer multiplexer = new InputMultiplexer();

    public void updateMultiplexer() {
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void addProcessor(InputProcessor p) {
        multiplexer.addProcessor(p);
    }

    public void remove(InputProcessor p) {
        multiplexer.removeProcessor(p);
    }

    public boolean contains(InputProcessor p) {
        return multiplexer.getProcessors().contains(p, true);
    }
}
