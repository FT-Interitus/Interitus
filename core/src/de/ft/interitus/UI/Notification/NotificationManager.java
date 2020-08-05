/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.Notification;

import com.badlogic.gdx.Gdx;
import de.ft.interitus.Programm;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.ShapeRenderer;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class NotificationManager {

    private static final ArrayList<Notification> notifications = new ArrayList<>();
    private static final int NOTIFICATION_MARGIN = 10;
    private static final int DISTANCE_RIGHT = 300;
    private static final int HEIGHT = 75;
    private static final int MARGIN_RIGHT = 20;
    private static final int MARGIN_BOTTOM = 175;
    private static int MAX_NOTIFICATIONS = 10;

    public static boolean sendNotification(Notification notification) {

        if (notifications.size() < MAX_NOTIFICATIONS) {

            notifications.add(notification);
            notification.setStarttime(System.currentTimeMillis());
            return true;
        }else{
            return false;
        }

    }


    public static void draw() {


        for (int i = notifications.size() - 1; i >= 0; i--) {
            Gdx.gl.glEnable(GL11.GL_BLEND);
            Gdx.gl.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            ProgrammingSpace.shapeRenderer.setColor(Settings.theme.ClearColor().r,Settings.theme.ClearColor().g,Settings.theme.ClearColor().b,notifications.get(i).getFadeout());

            ProgrammingSpace.shapeRenderer.roundendrect(Gdx.graphics.getWidth() - DISTANCE_RIGHT, 175 + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i)), DISTANCE_RIGHT - MARGIN_RIGHT, HEIGHT, 5);
            ProgrammingSpace.shapeRenderer.end();

            ProgrammingSpace.batch.begin();
            AssetLoader.welcomefont.draw(ProgrammingSpace.batch, i + "", Gdx.graphics.getWidth() - DISTANCE_RIGHT + 20, 175 + ((NOTIFICATION_MARGIN + HEIGHT + 10) * (notifications.size() - 1 - i)));
            ProgrammingSpace.batch.end();
            Gdx.gl.glDisable(GL11.GL_BLEND);

        }

        for (int i = 0; i < notifications.size(); i++) {

            if (System.currentTimeMillis() - notifications.get(i).getStarttime() > notifications.get(i).getAlivetime() && !notifications.get(i).isStayalive()&&!notifications.get(i).isFadingout()) {
                notifications.get(i).setFadingout(true);
            }

            if(notifications.get(i).isFadingout()) {
                if(notifications.get(i).getFadeout()<0){
                    notifications.remove(notifications.get(i));
                    continue;
                }else{

                    notifications.get(i).setFadeout(notifications.get(i).getFadeout()-0.02f);


                }
            }




        }


    }

    public static void resize() {
        MAX_NOTIFICATIONS = 1;
        while (((NOTIFICATION_MARGIN + HEIGHT) * MAX_NOTIFICATIONS + 150) < UIVar.programmflaeche_h) {
            MAX_NOTIFICATIONS++;
        }
    }
}
