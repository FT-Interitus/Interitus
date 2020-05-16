package de.ft.interitus.UI;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.input.check.InputManager;

import static com.badlogic.gdx.Gdx.input;

public class Viewport {

    public static void init()   {
        InputManager.addProcessor(new InputAdapter(){
            @Override
            public boolean scrolled(int amount) {

                if(input.isKeyPressed(Input.Keys.CONTROL_LEFT)||input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
                    if(amount==-1) {
                        if(ProgrammingSpace.cam.zoom>0.4f) {
                            ProgrammingSpace.cam.zoom=ProgrammingSpace.cam.zoom-0.1f;
                        }

                    }else{
                        if(ProgrammingSpace.cam.zoom<2.0f) {
                            ProgrammingSpace.cam.zoom=ProgrammingSpace.cam.zoom+0.1f;
                        }
                    }
                }
                return super.scrolled(amount);





            }
        });
    }

    public static void update() {
        if (input.isKeyJustPressed(Input.Keys.LEFT)) {
            ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x -= 20, ProgrammingSpace.cam.position.y, 0);
        }

        if (input.isKeyJustPressed(Input.Keys.RIGHT)) {
            ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x += 20, ProgrammingSpace.cam.position.y, 0);
        }

        if (input.isKeyJustPressed(Input.Keys.UP)) {
            ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x,ProgrammingSpace.cam.position.y += 20, 0);
        }

        if (input.isKeyJustPressed(Input.Keys.DOWN)) {
            ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y -= 20, 0);
        }

        if(input.isKeyJustPressed(Input.Keys.PLUS)) {
            ProgrammingSpace.cam.zoom= ProgrammingSpace.cam.zoom-0.2f;
        }
        if(input.isKeyJustPressed(Input.Keys.MINUS)) {
            ProgrammingSpace.cam.zoom= ProgrammingSpace.cam.zoom+0.2f;
        }
    }
}
