package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.inputfields.check.InputManager;

import static com.badlogic.gdx.Gdx.input;

public class Viewport {
private static boolean run_left = false;
private static boolean run_right = false;
private static boolean run_up = false;
private static boolean run_down = false;
private static double time_pressed_left = 0;
private static double time_pressed_right = 0;
private static double time_pressed_up = 0;
private static double time_pressed_down = 0;


public static long movedelay = 580;
    public static long firstmovedelay = 600;
    public static void init()   {
        Gdx.graphics.setVSync(Settings.Vsync);

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

    public static void update(float delta) {

        if (input.isKeyPressed(Input.Keys.LEFT)) {
            if(!run_left) {
                time_pressed_left = System.currentTimeMillis();
                run_left = true;
                ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x -= 20, ProgrammingSpace.cam.position.y, 0);
            }else{
                if(System.currentTimeMillis()-time_pressed_left>firstmovedelay) {
                    time_pressed_left = System.currentTimeMillis()-movedelay;
                    ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x -= 20, ProgrammingSpace.cam.position.y, 0);
                }
            }
        }else{
            run_left = false;
        }


        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            if(!run_right) {
                time_pressed_right = System.currentTimeMillis();
                run_right = true;
                ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x += 20, ProgrammingSpace.cam.position.y, 0);
            }else{
                if(System.currentTimeMillis()-time_pressed_right>firstmovedelay) {
                    time_pressed_right = System.currentTimeMillis()-movedelay;
                    ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x += 20, ProgrammingSpace.cam.position.y, 0);
                }
            }
        }else{
            run_right = false;
        }


        if (input.isKeyPressed(Input.Keys.UP)) {
            if(!run_up) {
                time_pressed_up = System.currentTimeMillis();
                run_up = true;
                ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y += 20, 0);
            }else{
                if(System.currentTimeMillis()-time_pressed_up>firstmovedelay) {
                    time_pressed_up = System.currentTimeMillis()-movedelay;
                    ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y += 20, 0);
                }
            }
        }else{
            run_up = false;
        }


        if (input.isKeyPressed(Input.Keys.DOWN)) {
            if(!run_down) {
                time_pressed_down = System.currentTimeMillis();
                run_down = true;
                ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y -= 20, 0);
            }else{
                if(System.currentTimeMillis()-time_pressed_down>firstmovedelay) {
                    time_pressed_down = System.currentTimeMillis()-movedelay;
                    ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y -= 20, 0);
                }
            }
        }else{
            run_down = false;
        }


        if(input.isKeyJustPressed(Input.Keys.PLUS)) {
            ProgrammingSpace.cam.zoom= ProgrammingSpace.cam.zoom-0.2f;
        }
        if(input.isKeyJustPressed(Input.Keys.MINUS)) {
            ProgrammingSpace.cam.zoom= ProgrammingSpace.cam.zoom+0.2f;
        }






    }

    public static void limitfps() {
        if(Settings.limitfps!=0) {
            ProgrammingSpace.rendersleeptime = (int) (1000 / Settings.limitfps - ProgrammingSpace.rendertimediff);
        }else {
            Settings.limitfps =0;
        }
        if(ProgrammingSpace.rendersleeptime > 0&&!Settings.Vsync){
            try {
                Thread.sleep(ProgrammingSpace.rendersleeptime);
            } catch (InterruptedException e) {}
        }
    }
}
