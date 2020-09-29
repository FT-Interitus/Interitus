/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Settings;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.block.BlockKillMovingWiresEvent;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalFocusLostEvent;
import de.ft.interitus.projecttypes.ProjectManager;

import static com.badlogic.gdx.Gdx.input;

public class Viewport {
    public static long movedelay = 580;
    public static long firstmovedelay = 600;
    private static boolean run_left = false;
    private static boolean run_right = false;
    private static boolean run_up = false;
    private static boolean run_down = false;
    private static double time_pressed_left = 0;
    private static double time_pressed_right = 0;
    private static double time_pressed_up = 0;
    private static double time_pressed_down = 0;
    private static Matrix4 projection = new Matrix4();
    public static final float ADDITIONAL_FRUSTRUM_SIZE = 0.7f;
    private static Matrix4 view = new Matrix4();
    private static Vector3 tmp = new Vector3();
    private static Matrix4 combined = new Matrix4();
    private static Matrix4 invProjectionView = new Matrix4();
    public static Frustum extendedfrustum = new Frustum();


    public static void init() {
        Gdx.graphics.setVSync(Settings.Vsync);

        InputManager.addProcessor(new InputAdapter() {


            @Override
            public boolean scrolled(int amount) {

                if (!UIVar.isdialogeopend) {

                    if (input.isKeyPressed(Input.Keys.CONTROL_LEFT) || input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
                        if (amount == -1) {
                            if (ProgrammingSpace.cam.zoom > 0.4f) {
                                ProgrammingSpace.cam.zoom = ProgrammingSpace.cam.zoom - 0.1f;
                            }

                        } else {
                            if (ProgrammingSpace.cam.zoom <= 2.0f) {
                                ProgrammingSpace.cam.zoom = ProgrammingSpace.cam.zoom + 0.1f;
                            }
                        }

                        ProjectManager.getActProjectVar().zoom = ProgrammingSpace.cam.zoom;

                    }

                }
                return false;


            }
        });

        InputManager.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                if (!UIVar.isdialogeopend) {

                    if (input.isButtonPressed(Input.Buttons.MIDDLE)) {
                        ProgrammingSpace.cam.position.x -= deltaX*ProgrammingSpace.cam.zoom;
                        ProgrammingSpace.cam.position.y += deltaY*ProgrammingSpace.cam.zoom;
                        ProgrammingSpace.cam.update();
                    }
                }


                return false;
            }
        }));


        InputManager.updateMultiplexer();

        EventVar.globalEventManager.addListener(new GlobalEventAdapter() {
            @Override
            public void focuslost(GlobalFocusLostEvent e) {
                EventVar.blockEventManager.killmovingwires(new BlockKillMovingWiresEvent(this));
            }
        });


    }

    public static void update(float delta) {
        if (!UIVar.isdialogeopend && !UIVar.moveprogrammlock) {

            if (input.isKeyPressed(Input.Keys.LEFT)) {
                if (!run_left) {
                    time_pressed_left = System.currentTimeMillis();
                    run_left = true;
                    ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x -= 20, ProgrammingSpace.cam.position.y, 0);
                } else {
                    if (System.currentTimeMillis() - time_pressed_left > firstmovedelay) {
                        time_pressed_left = System.currentTimeMillis() - movedelay;
                        ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x -= 20, ProgrammingSpace.cam.position.y, 0);
                    }
                }
            } else {
                run_left = false;
            }


            if (input.isKeyPressed(Input.Keys.RIGHT)) {
                if (!run_right) {
                    time_pressed_right = System.currentTimeMillis();
                    run_right = true;
                    ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x += 20, ProgrammingSpace.cam.position.y, 0);
                } else {
                    if (System.currentTimeMillis() - time_pressed_right > firstmovedelay) {
                        time_pressed_right = System.currentTimeMillis() - movedelay;
                        ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x += 20, ProgrammingSpace.cam.position.y, 0);
                    }
                }
            } else {
                run_right = false;
            }


            if (input.isKeyPressed(Input.Keys.UP)) {
                if (!run_up) {
                    time_pressed_up = System.currentTimeMillis();
                    run_up = true;
                    ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y += 20, 0);
                } else {
                    if (System.currentTimeMillis() - time_pressed_up > firstmovedelay) {
                        time_pressed_up = System.currentTimeMillis() - movedelay;
                        ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y += 20, 0);
                    }
                }
            } else {
                run_up = false;
            }


            if (input.isKeyPressed(Input.Keys.DOWN)) {
                if (!run_down) {
                    time_pressed_down = System.currentTimeMillis();
                    run_down = true;
                    ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y -= 20, 0);
                } else {
                    if (System.currentTimeMillis() - time_pressed_down > firstmovedelay) {
                        time_pressed_down = System.currentTimeMillis() - movedelay;
                        ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y -= 20, 0);
                    }
                }
            } else {
                run_down = false;
            }


            if (input.isKeyJustPressed(Input.Keys.PLUS)) {
                ProgrammingSpace.cam.zoom = ProgrammingSpace.cam.zoom - 0.2f;
            }
            if (input.isKeyJustPressed(Input.Keys.MINUS)) {
                ProgrammingSpace.cam.zoom = ProgrammingSpace.cam.zoom + 0.2f;
            }


            ProjectManager.getActProjectVar().cam_pos.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y);

        }



        projection.setToOrtho((ProgrammingSpace.cam.zoom+ADDITIONAL_FRUSTRUM_SIZE) * -ProgrammingSpace.cam.viewportWidth / 2, (ProgrammingSpace.cam.zoom+ADDITIONAL_FRUSTRUM_SIZE) * (ProgrammingSpace.cam.viewportWidth / 2), (ProgrammingSpace.cam.zoom+ADDITIONAL_FRUSTRUM_SIZE) * -(ProgrammingSpace.cam.viewportHeight / 2), (ProgrammingSpace.cam.zoom+ADDITIONAL_FRUSTRUM_SIZE)
                * ProgrammingSpace.cam.viewportHeight / 2, ProgrammingSpace.cam.near, ProgrammingSpace.cam.far);

        view.setToLookAt(ProgrammingSpace.cam.position, tmp.set(ProgrammingSpace.cam.position).add(ProgrammingSpace.cam.direction), ProgrammingSpace.cam.up);
        combined.set(projection);
        Matrix4.mul(combined.val, view.val);


            invProjectionView.set(combined);
            Matrix4.inv(invProjectionView.val);
            extendedfrustum.update(invProjectionView);





    }

    public static void limitfps() {
        ProgrammingSpace.rendertimediff = System.currentTimeMillis() - ProgrammingSpace.renderstarttime;
        if (Settings.limitfps != 0) {
            ProgrammingSpace.rendersleeptime = (int) (1000 / Settings.limitfps - ProgrammingSpace.rendertimediff);
        } else {
            Settings.limitfps = 0;
        }
        if (ProgrammingSpace.rendersleeptime > 0 && !Settings.Vsync) {
            try {
                Thread.sleep(ProgrammingSpace.rendersleeptime);
            } catch (InterruptedException e) {
            }
        }
    }
}
