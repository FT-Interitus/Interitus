package de.ft.interitus.UI.inputfields;

import com.badlogic.gdx.InputAdapter;
import de.ft.interitus.UI.inputfields.check.InputManager;

import java.util.ArrayList;

public class PressedKeys {
    ArrayList<Integer>pressedkeys=new ArrayList<>();
    public PressedKeys(){


        InputManager.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                pressedkeys.add(keycode);
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                pressedkeys.remove(pressedkeys.indexOf(keycode));
                return false;
            }

            @Override
            public boolean keyTyped(char key) {
                return super.keyTyped(key);
            }

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                return false;
            }
        });
        InputManager.updateMultiplexer();

    }

    public ArrayList<Integer> getPressedkeys() {
        return pressedkeys;
    }
}
