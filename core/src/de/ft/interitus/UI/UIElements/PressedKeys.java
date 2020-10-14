/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.ft.interitus.UI.InputManager;
import de.ft.interitus.WindowManager;
import de.ft.interitus.utils.ArrayList;

public class PressedKeys {
    ArrayList<Integer> pressedkeys = new ArrayList<>();

    public PressedKeys() {


        WindowManager.inputManager.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {

                pressedkeys.add(keycode);

                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (pressedkeys.indexOf(keycode) != -1) {
                    pressedkeys.remove((Integer) keycode);
                }
                return false;
            }

            @Override
            public boolean keyTyped(char key) {
                return false;
            }

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                return false;
            }
        });
        WindowManager.inputManager.updateMultiplexer();

    }

    public ArrayList<Integer> getPressedkeys() {
        if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            pressedkeys.clear();
        }
        return pressedkeys;
    }
}
