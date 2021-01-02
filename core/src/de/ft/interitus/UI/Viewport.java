/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.*;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.UI.EventManager;
import de.ft.interitus.events.UI.UIZoomEvent;
import de.ft.interitus.events.block.BlockKillMovingWiresEvent;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalFocusLostEvent;
import de.ft.interitus.projecttypes.ProjectManager;

import static com.badlogic.gdx.Gdx.input;

public class Viewport {
    public static final float ADDITIONAL_FRUSTRUM_SIZE = 0.7f;
    public static long movedelay = 580;
    public static long firstmovedelay = 600;
    public static Frustum extendedfrustum = new Frustum();
    private static boolean run_left = false;
    private static boolean run_right = false;
    private static boolean run_up = false;
    private static boolean run_down = false;
    private static double time_pressed_left = 0;
    private static double time_pressed_right = 0;
    private static double time_pressed_up = 0;
    private static double time_pressed_down = 0;
    private static final Matrix4 projection = new Matrix4();
    private static final Matrix4 view = new Matrix4();
    private static final Vector3 tmp = new Vector3();
    private static final Matrix4 combined = new Matrix4();
    private static final Matrix4 invProjectionView = new Matrix4();

    public static EventManager<UIZoomEvent> zoomEvent = new EventManager<>();


    public static void init(OrthographicCamera cam, InputManager inputManager) {
        Gdx.graphics.setVSync(Settings.Vsync);
        inputManager.addProcessor(new InputAdapter() {

            Vector3 tp = new Vector3();

            @Override
            public boolean scrolled(int amount) {

                if (!UIVar.isdialogeopend) {

                    if(amount==-1) {
                        if (cam.zoom <= 0.4f) return false;
                    }else{
                        if (cam.zoom > 2.0f) return false;
                    }

                    if (input.isKeyPressed(Input.Keys.CONTROL_LEFT) || input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {

                        zoomEvent.fireForEach(n->n.zoomStart());

                        cam.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0 ));
                        float px = tp.x;
                        float py = tp.y;

                        cam.zoom += amount * cam.zoom * 0.1f;
                        cam.update();

                        cam.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0 ));
                        cam.position.add(px - tp.x, py- tp.y, 0);
                        cam.update();

                    }

                }
                return true;


            }
        });

        inputManager.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                if (!UIVar.isdialogeopend) {

                    if (input.isButtonPressed(Input.Buttons.MIDDLE)) {
                        cam.position.x -= deltaX * cam.zoom;
                        cam.position.y += deltaY * cam.zoom;
                        cam.update();
                    }
                }


                return false;
            }
        }));


        inputManager.updateMultiplexer();

        EventVar.globalEventManager.addListener(new GlobalEventAdapter() {
            @Override
            public void focusLost(GlobalFocusLostEvent e) {
                if (ProjectManager.getActProjectVar() != null && Var.inProgram) {
                    EventVar.blockEventManager.killmovingwires(new BlockKillMovingWiresEvent(this));
                }
            }
        });


    }

    public static void update(float delta, OrthographicCamera cam) {
        if (!UIVar.isdialogeopend && !UIVar.moveprogrammlock) {

            if (input.isKeyPressed(Input.Keys.LEFT)) {
                if (!run_left) {
                    time_pressed_left = System.currentTimeMillis();
                    run_left = true;
                    cam.position.set(cam.position.x -= 20, cam.position.y, 0);
                } else {
                    if (System.currentTimeMillis() - time_pressed_left > firstmovedelay) {
                        time_pressed_left = System.currentTimeMillis() - movedelay;
                        cam.position.set(cam.position.x -= 20, cam.position.y, 0);
                    }
                }
            } else {
                run_left = false;
            }


            if (input.isKeyPressed(Input.Keys.RIGHT)) {
                if (!run_right) {
                    time_pressed_right = System.currentTimeMillis();
                    run_right = true;
                    cam.position.set(cam.position.x += 20, cam.position.y, 0);
                } else {
                    if (System.currentTimeMillis() - time_pressed_right > firstmovedelay) {
                        time_pressed_right = System.currentTimeMillis() - movedelay;
                        cam.position.set(cam.position.x += 20, cam.position.y, 0);
                    }
                }
            } else {
                run_right = false;
            }


            if (input.isKeyPressed(Input.Keys.UP)) {
                if (!run_up) {
                    time_pressed_up = System.currentTimeMillis();
                    run_up = true;
                    cam.position.set(cam.position.x, cam.position.y += 20, 0);
                } else {
                    if (System.currentTimeMillis() - time_pressed_up > firstmovedelay) {
                        time_pressed_up = System.currentTimeMillis() - movedelay;
                        cam.position.set(cam.position.x, cam.position.y += 20, 0);
                    }
                }
            } else {
                run_up = false;
            }


            if (input.isKeyPressed(Input.Keys.DOWN)) {
                if (!run_down) {
                    time_pressed_down = System.currentTimeMillis();
                    run_down = true;
                    cam.position.set(cam.position.x, cam.position.y -= 20, 0);
                } else {
                    if (System.currentTimeMillis() - time_pressed_down > firstmovedelay) {
                        time_pressed_down = System.currentTimeMillis() - movedelay;
                        cam.position.set(cam.position.x, cam.position.y -= 20, 0);
                    }
                }
            } else {
                run_down = false;
            }


            if (input.isKeyJustPressed(Input.Keys.PLUS)) {
                cam.zoom = cam.zoom - 0.2f;
            }
            if (input.isKeyJustPressed(Input.Keys.MINUS)) {
                cam.zoom = cam.zoom + 0.2f;
            }


            ProjectManager.getActProjectVar().cam_pos.set(cam.position.x, cam.position.y);

        }


        projection.setToOrtho((cam.zoom + ADDITIONAL_FRUSTRUM_SIZE) * -cam.viewportWidth / 2, (cam.zoom + ADDITIONAL_FRUSTRUM_SIZE) * (cam.viewportWidth / 2), (cam.zoom + ADDITIONAL_FRUSTRUM_SIZE) * -(cam.viewportHeight / 2), (cam.zoom + ADDITIONAL_FRUSTRUM_SIZE)
                * cam.viewportHeight / 2, cam.near, cam.far);

        view.setToLookAt(cam.position, tmp.set(cam.position).add(cam.direction), cam.up);
        combined.set(projection);
        Matrix4.mul(combined.val, view.val);


        invProjectionView.set(combined);
        Matrix4.inv(invProjectionView.val);
        extendedfrustum.update(invProjectionView);


    }

    public static void limitfps() {
        ProgramingSpace.rendertimediff = System.currentTimeMillis() - ProgramingSpace.renderstarttime;
        if (Settings.limitfps != 0) {
            ProgramingSpace.rendersleeptime = (int) (1000 / Settings.limitfps - ProgramingSpace.rendertimediff);
        } else {
            Settings.limitfps = 0;
        }
        if (ProgramingSpace.rendersleeptime > 0 && !Settings.Vsync) {
            try {
                Thread.sleep(ProgramingSpace.rendersleeptime);
            } catch (InterruptedException e) {
            }
        }
    }
}
