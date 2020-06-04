package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.inputfields.check.InputManager;

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
                return false;





            }
        });

       InputManager.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
           @Override
           public boolean pan(float x, float y, float deltaX, float deltaY) {

               if(input.isButtonPressed(Input.Buttons.MIDDLE)) {
                   ProgrammingSpace.cam.position.x -= deltaX;
                   ProgrammingSpace.cam.position.y += deltaY;
                   ProgrammingSpace.cam.update();
               }


               return false;
           }
       }));


        InputManager.updateMultiplexer();
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
