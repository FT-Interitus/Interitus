/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.ProgrammingSpace;

public class ProgramGrid {
    public static int radius = 2;
    public static float linewidth = 0.75f;
    public static float margin = 20;
    public static boolean points = false;
    public static boolean enable = true;
    public static boolean block_snapping = true;

    public static int origin_radius = 2;
    private static int move_x = 0;
    private static int move_y = 0;
    private static final float min = 0.39999992f;
    private static final float max = 2.0000002f;
    private static final float neuzoom = 0;
    private static final float ma = 0;

    public static void draw() {


        if(!enable) {
            return;
        }

        ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //   margin=20;
        //   ma=max-min;
        //    neuzoom=(ProgrammingSpace.cam.zoom-min)/ma;
        //    System.out.println("margin: ");
        //    margin=margin*(1-neuzoom);

        if (ProgrammingSpace.cam.position.x / margin % 2 != 0) {

            move_x = -(int) (ProgrammingSpace.cam.position.x % margin);

        } else {
            move_x = 0;
        }

        if (ProgrammingSpace.cam.position.y / margin % 2 != 0) {

            move_y = -(int) (ProgrammingSpace.cam.position.y % margin);

        } else {
            move_y = 0;
        }


        //  move_y = (int) (move_y*ProgrammingSpace.cam.zoom*ProgrammingSpace.cam.viewportHeight/2);
        // move_x = (int) (move_x*ProgrammingSpace.cam.zoom);
        //radius = origin_radius;
        //   radius = (int) (radius*ProgrammingSpace.cam.zoom);


        if (points) {

            for (int x = move_x; x < Gdx.graphics.getWidth() - (UIVar.abstandvonRand * 2); ) {

                for (int y = move_y; y < UIVar.programmflaeche_h; ) {

                    ProgrammingSpace.shapeRenderer.circle(x + UIVar.abstandvonRand, y + UIVar.programmflaeche_y, radius);


                    y += margin;
                }

                x += margin;

            }
        } else {


            for (int x = move_x; x < Gdx.graphics.getWidth() - (UIVar.abstandvonRand * 2); ) {


                    ProgrammingSpace.shapeRenderer.rectLine(x+UIVar.abstandvonRand,UIVar.programmflaeche_y,x+UIVar.abstandvonRand,UIVar.programmflaeche_y+UIVar.programmflaeche_h,linewidth);




                x += margin;

            }

            for (int y = move_y; y < UIVar.programmflaeche_h; ) {

                ProgrammingSpace.shapeRenderer.rectLine((float)UIVar.abstandvonRand,(float)y+UIVar.programmflaeche_y,(float)Gdx.graphics.getWidth()-UIVar.abstandvonRand,(float)y+UIVar.programmflaeche_y,linewidth);



                y += margin;
            }



        }

        ProgrammingSpace.shapeRenderer.end();


    }


}
