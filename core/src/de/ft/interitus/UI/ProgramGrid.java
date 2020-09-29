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
    public static float line_width = 0.75f;
    public static float margin = 17.5f;
    public static boolean points = false;
    public static boolean enable = true;
    public static boolean block_snapping = true;
    public static boolean block_active_snapping = false;


    public static void draw() {


        margin =20f;

       margin = margin*(0.86f/ProgrammingSpace.cam.zoom);
       System.out.println(ProgrammingSpace.cam.zoom);

        if(!enable) {
            return;
        }

        ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //   margin=20;
        //   ma=max-min;
        //    x=(ProgrammingSpace.cam.zoom-min)/ma;
        //    System.out.println("margin: ");
        //    margin=margin*(1-x);

        int move_x;

        if ((ProgrammingSpace.cam.position.x*(0.86f/ProgrammingSpace.cam.zoom)) / margin % 2 != 0) {

            move_x = -(int) ((ProgrammingSpace.cam.position.x*(0.86f/ProgrammingSpace.cam.zoom)) % margin);

        } else {
            move_x = 0;
        }

        int move_y;

        if ((ProgrammingSpace.cam.position.y*(0.86f/ProgrammingSpace.cam.zoom)) / margin % 2 != 0) {

            move_y = -(int) ((ProgrammingSpace.cam.position.y*(0.86f/ProgrammingSpace.cam.zoom)) % margin);

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


                    ProgrammingSpace.shapeRenderer.rectLine(x+UIVar.abstandvonRand,UIVar.programmflaeche_y,x+UIVar.abstandvonRand,UIVar.programmflaeche_y+UIVar.programmflaeche_h, line_width);




                x += margin;

            }

            for (int y = move_y; y < UIVar.programmflaeche_h; ) {

                ProgrammingSpace.shapeRenderer.rectLine((float)UIVar.abstandvonRand,(float)y+UIVar.programmflaeche_y,(float)Gdx.graphics.getWidth()-UIVar.abstandvonRand,(float)y+UIVar.programmflaeche_y, line_width);



                y += margin;
            }



        }

        ProgrammingSpace.shapeRenderer.end();


    }


}
