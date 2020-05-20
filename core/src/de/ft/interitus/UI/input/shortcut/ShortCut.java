package de.ft.interitus.UI.input.shortcut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.ft.interitus.UI.input.check.InputManager;

import java.util.ArrayList;

public class ShortCut {
    boolean disable=false;
    ArrayList<Integer>kombination=new ArrayList<>();
    public ShortCut(int... kombination) {
        for(int i=0;i<kombination.length;i++){
             this.kombination.add(kombination[i]);
        }



        InputManager.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                System.out.println("keyDown  "+Input.Keys.toString(keycode));
                return super.keyDown(keycode);
            }

            @Override
            public boolean keyUp(int keycode) {
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


    public void setShortCut(int... kombination) {
        for(int i=0;i<kombination.length;i++){
            this.kombination.add(kombination[i]);
        }
    }


    public boolean isPressed(){
        boolean pressed=true;
if(!disable) {
    for (int i = 0; i < kombination.size(); i++) {

        if(kombination.get(i)<600) {
            if (!Gdx.input.isKeyPressed(kombination.get(i))) {
                pressed = false;
            }
        }else{
            switch (kombination.get(i)){
                case 600:
                    if (!Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
                        pressed = false;
                    }
                    break;
                case 601:
                    if (!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                        pressed = false;
                    }
                    break;
            }
        }
    }
}else{
    pressed=false;
}

        return pressed;
    }

}
