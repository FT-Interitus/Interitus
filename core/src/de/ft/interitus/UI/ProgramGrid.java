/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.Settings;


import com.badlogic.gdx.graphics.OrthographicCamera;


public class ProgramGrid {
    public static int radius = 2;
    public static float line_width = 0.75f;
    public static float margin = 17.5f;
    public static boolean points = false;
    public static boolean enable = true;
    public static int marginBottom = 0;



    public static void draw(ShapeRenderer shapeRenderer, OrthographicCamera cam) {


        margin =20f;

       margin = margin*(0.86f/ cam.zoom);

        if(!enable) {
            return;
        }



       shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Settings.theme.ClearColor());


        int move_x;

        if ((cam.position.x*(0.86f/ cam.zoom)) / margin % 2 != 0) {

            move_x = -(int) ((cam.position.x*(0.86f/ cam.zoom)) % margin);

        } else {
            move_x = 0;
        }

        int move_y;

        if ((cam.position.y*(0.86f/ cam.zoom)) / margin % 2 != 0) {

            move_y = -(int) ((cam.position.y*(0.86f/ cam.zoom)) % margin);

        } else {
            move_y = 0;
        }



        if (points) {

            for (int x = move_x; x < WindowAPI.getWidth() - (UIVar.abstandvonRand * 2); ) {

                for (int y = move_y; y < UIVar.programmflaeche_h+(UIVar.programmflaeche_y-marginBottom); ) {

                    shapeRenderer.circle(x + UIVar.abstandvonRand, y + marginBottom, radius);


                    y += margin;
                }

                x += margin;

            }
        } else {


            for (int x = move_x; x < WindowAPI.getWidth() - (UIVar.abstandvonRand * 2); ) {


                shapeRenderer.rectLine(x+UIVar.abstandvonRand, marginBottom,x+UIVar.abstandvonRand, marginBottom +UIVar.programmflaeche_h+(UIVar.programmflaeche_y-marginBottom), line_width);

                x += margin;

            }

            for (int y = move_y; y < UIVar.programmflaeche_h+(UIVar.programmflaeche_y-marginBottom); ) {

                shapeRenderer.rectLine((float)UIVar.abstandvonRand,(float)y+ marginBottom,(float)WindowAPI.getWidth()-UIVar.abstandvonRand,(float)y+ marginBottom, line_width);
                
                y += margin;
            }



        }

        shapeRenderer.end();


    }


}
