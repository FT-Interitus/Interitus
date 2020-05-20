package de.ft.interitus.UI.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.ft.interitus.UI.input.check.InputManager;

import java.util.ArrayList;

public class PressedKeys {
    ArrayList<Integer>pressedkeys=new ArrayList<>();
    public PressedKeys(){


        InputManager.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                pressedkeys.add(keycode);
                return super.keyDown(keycode);
            }

            @Override
            public boolean keyUp(int keycode) {
                pressedkeys.remove(pressedkeys.indexOf(keycode));
                return super.keyUp(keycode);
            }

            @Override
            public boolean keyTyped(char key) {
                return super.keyTyped(key);
            }

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                return super.touchDown(x,y,pointer,button);
            }
        });
        InputManager.updateMultiplexer();

    }

    public ArrayList<Integer> getPressedkeys() {
        return pressedkeys;
    }
}
