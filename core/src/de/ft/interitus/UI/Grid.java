/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.ProgrammingSpace;

public class Grid {

    public static int margin = 20;
    private static int radius = 2;
    public static int origin_radius = 2;
private static int move_x =0;
private static int move_y =0;
    public static void draw() {



        ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        if(ProgrammingSpace.cam.position.x/(float)margin%2!=0) {

            move_x = -(int) (ProgrammingSpace.cam.position.x%(float)margin);

        }else {
            move_x = 0;
        }

        if(ProgrammingSpace.cam.position.y/(float)margin%2!=0) {

            move_y = -(int) (ProgrammingSpace.cam.position.y%(float)margin);

        }else{
            move_y =0;
        }


      //  move_y = (int) (move_y*ProgrammingSpace.cam.zoom*ProgrammingSpace.cam.viewportHeight/2);
       // move_x = (int) (move_x*ProgrammingSpace.cam.zoom);
        //radius = origin_radius;
     //   radius = (int) (radius*ProgrammingSpace.cam.zoom);



       for(int x=move_x;x< Gdx.graphics.getWidth()-(UIVar.abstandvonRand*2);) {

           for(int y=move_y;y<UIVar.programmflaeche_h;) {

               ProgrammingSpace.shapeRenderer.circle(x+UIVar.abstandvonRand,y+UIVar.programmflaeche_y,radius);


               y+=margin;
           }

           x+=margin;

       }

       ProgrammingSpace.shapeRenderer.end();


    }


}
