/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;


import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Settings;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.block.BlockKillMovingWiresEvent;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalFocusLostEvent;
import de.ft.interitus.projecttypes.ProjectManager;


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




    public static void init() {




    }

    public static void update(float delta) {
        /*
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



*/





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
