/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.popup.PopupHandler;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.ShapeRenderer;

public class MainRendering {
    public static ShapeRenderer shapeRenderer;
    public static ShapeRenderer BlockshapeRenderer;
    public static BitmapFont font;
    public static SpriteBatch batch;
    private static boolean initalProgramingSpace = true;

    public static void update() {


        UI.UIcam.update();
        UI.UIbatch.setProjectionMatrix(UI.UIcam.combined);
        MainRendering.shapeRenderer.setProjectionMatrix(UI.UIcam.combined);


    }

    public static void drawer() {


        if(!Var.inProgram) {
            UIVar.programmflaeche_y = 0;

        }





        NotificationManager.draw();
        try {

            UI.update();

        } catch (Exception e) {
            //Falls die UI nicht richtig initialisiert werden konnte
            DisplayErrors.customErrorstring = "Fehler in der UI";
            DisplayErrors.error = e;

        }

/*
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            Notification notification = new Notification(AssetLoader.information, "Wichtige Information", "\nEs steht kein Update bereit!");
            //notification.setButtonBar(new UIElementBar().addButton(new Button().setText("Test")));
            NotificationManager.sendNotification(notification);
        }


 */

        PopupHandler.drawPopUp();


        try {
            DisplayErrors.checkerror(); //Check if there are undisplayed Errors
        } catch (IllegalStateException e) {
            //Bei eienem VisUI absturz
        } catch (NullPointerException e) {

            e.printStackTrace();

        }


    }

    public static void init() {
        font = new BitmapFont();
        MainRendering.batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        BlockshapeRenderer = new ShapeRenderer();
        UI.UIcam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);


    }

   public enum Windows {

        programingspace,welcome

    }

    public static void switchto(Windows window) {


     if(window==Windows.programingspace) {
         if(!Var.inProgram) {
             Var.inProgram =true;
             if(initalProgramingSpace) {
                 Var.programingSpace.open();
                 initalProgramingSpace = false;
             }
             Program.INSTANCE.setScreen(Var.programingSpace);

         }

     }else{

         if(Var.inProgram) {
             Var.inProgram =false;
             Program.INSTANCE.setScreen(Var.welcome);

         }

     }


    }
}
