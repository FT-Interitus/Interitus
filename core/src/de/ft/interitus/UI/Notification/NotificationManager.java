/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.Notification;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ft.interitus.UI.WindowAPI;
import de.ft.interitus.WindowManager;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.ShapeRenderer;
import org.lwjgl.opengl.GL11;

import de.ft.interitus.utils.ArrayList;

public class NotificationManager {

    private static final ArrayList<Notification> notifications = new ArrayList<>();
    private static final int NOTIFICATION_MARGIN = 10;
    private static final int DISTANCE_RIGHT = 300;
    private static final int HEIGHT = 75;
    private static final int MARGIN_RIGHT = 20;
    private static final int IMAGE_MARGIN_RIGHT = 17;
    private static final int IMAGE_MARGIN_TOP = 17;
    private static final int PROGRESSBAR_MARGIN_BOTTOM = 10;
    private static final int MOVE_IN_SPEED  = 15;
    private static final GlyphLayout glyphLayout = new GlyphLayout();

    private static int MAX_NOTIFICATIONS = 10;

    /***
     * Send a Notification to the user
     * It can fail if there are more Notification than Space on the Screen
     * Nevertheless it can be displayed if the Notification isn't closeable
     *
     * @param notification that one which should be send to the user
     * @return true if the sending was successful
     */

    public static boolean sendNotification(Notification notification) {



        if ((notifications.size() < MAX_NOTIFICATIONS||!notification.isCloseable())&&!notification.isDisplayed()) {

            notifications.add(notification);
            notification.setDisplayed(true);
            if(notification.isInrollin()) {
                notification.setRollin(DISTANCE_RIGHT);
            }else{
                notification.setRollin(0);
            }
            notification.setStarttime(System.currentTimeMillis());
            notification.getCloseButton().setImage(AssetLoader.close_notification);



            return true;
        }else{
            return false;
        }

    }


    /***
     * Draw should be called in the Main Renderer with no active Batch or ShapeRenderer
     * At first it will be checked how much notifications can fit on the screen
     * Than Notifications which doesn't fit will be deleted without fading if they are closeable
     * The Notification will be Rendered by default only the Title, the Icon, the Message, the CloseButton and the Background
     * If the ProgressBar Value isn't -1 it will also be rendered
     * Than Notifications which are expired will be faded out
     * That happens after the Render loop to prevent flickering
     */

    public static void draw() {

        MAX_NOTIFICATIONS = 1;
        while (((NOTIFICATION_MARGIN + HEIGHT) * MAX_NOTIFICATIONS + 115) < UIVar.programmflaeche_h) {
            MAX_NOTIFICATIONS++;
        }



        //Delete Notification if it doesnt fit on the screen but only if it is closeable
        if(notifications.size()>MAX_NOTIFICATIONS) {
          int close_counter =0;
            for(int i=0;i<notifications.size()-MAX_NOTIFICATIONS;i++) {

                if(notifications.size()-1-i-close_counter<0) {
                    break;
                }

                if(! notifications.get(notifications.size()-1-i-close_counter).isCloseable()) {
                    close_counter++;
                    continue;
                }
                notifications.get(notifications.size()-1-i-close_counter).setFadingout(true);
                notifications.get(notifications.size()-1-i-close_counter).setFadeout(-0.1f);

            }
        }


        for (int i = notifications.size() - 1; i >= 0; i--) {

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); //Enable Alpha Rendering
            WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            WindowManager.shapeRenderer.setColor(Settings.theme.ClearColor().r,Settings.theme.ClearColor().g,Settings.theme.ClearColor().b,notifications.get(i).getFadeout());

            if(CheckMouse.isMouseover(WindowAPI.getWidth() - DISTANCE_RIGHT+notifications.get(i).getRollin(), UIVar.programmflaeche_y + MARGIN_RIGHT + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i)), DISTANCE_RIGHT - MARGIN_RIGHT, HEIGHT, false)&&!notifications.get(i).isClosedbyuser()) {


                for(int j=notifications.size()-1;j>=i;j--)  {
                    notifications.get(j).setFadingout(false);
                    notifications.get(j).setFadeout(1);
                }


                notifications.get(i).setFadingout(false);
                notifications.get(i).setFadeout(1);


            }


            WindowManager.shapeRenderer.roundendrect(WindowAPI.getWidth() - DISTANCE_RIGHT+notifications.get(i).getRollin(), UIVar.programmflaeche_y + MARGIN_RIGHT + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i)), DISTANCE_RIGHT - MARGIN_RIGHT, HEIGHT, 5);
            WindowManager.shapeRenderer.end();


            UI.UIbatch.begin();
            UI.UIbatch.setColor(1,1,1,notifications.get(i).getFadeout());
            UI.UIbatch.draw(notifications.get(i).getIcon(),WindowAPI.getWidth() - DISTANCE_RIGHT+IMAGE_MARGIN_RIGHT/2f+notifications.get(i).getRollin(),UIVar.programmflaeche_y + MARGIN_RIGHT + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i))+HEIGHT-IMAGE_MARGIN_TOP,10,10);
           glyphLayout.setText(  AssetLoader.defaultfont,notifications.get(i).getTitle());

            AssetLoader.defaultfont.setColor(0.9f,0.9f,0.9f,notifications.get(i).getFadeout());
          AssetLoader.defaultfont.draw(UI.UIbatch,notifications.get(i).getTitle(),WindowAPI.getWidth() - DISTANCE_RIGHT+IMAGE_MARGIN_RIGHT/2f+20+notifications.get(i).getRollin(),UIVar.programmflaeche_y + MARGIN_RIGHT + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i))+HEIGHT- glyphLayout.height /1.2f);
            AssetLoader.defaultfont.setColor(0.6f,0.6f,0.6f,notifications.get(i).getFadeout());

            glyphLayout.setText(AssetLoader.defaultfont,notifications.get(i).getMessage());
           AssetLoader.defaultfont.draw(UI.UIbatch,notifications.get(i).getMessage(),WindowAPI.getWidth() - DISTANCE_RIGHT+IMAGE_MARGIN_RIGHT/2f+23+notifications.get(i).getRollin(),UIVar.programmflaeche_y + MARGIN_RIGHT + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i))+HEIGHT-HEIGHT/3.2f);
            UI.UIbatch.end();
            if(notifications.get(i).isCloseable()) {
                notifications.get(i).getCloseButton().setAlpha(notifications.get(i).getFadeout());
                notifications.get(i).getCloseButton().setBounds(WindowAPI.getWidth() - IMAGE_MARGIN_RIGHT - MARGIN_RIGHT+notifications.get(i).getRollin(), UIVar.programmflaeche_y + MARGIN_RIGHT + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i))+HEIGHT-IMAGE_MARGIN_TOP,10,10);

                notifications.get(i).getCloseButton().draw();
                if( notifications.get(i).getCloseButton().isjustPressed()) {
                    notifications.get(i).setFadingout(true);
                    notifications.get(i).setClosedbyuser(true);
                    notifications.get(i).setFadeout(0.5f);
                }

            }

            if(notifications.get(i).getProgressbarvalue()>-1) {
                Gdx.gl.glEnable(GL11.GL_BLEND);
                Gdx.gl.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

                WindowManager.shapeRenderer.setColor(Settings.theme.ClearColor().r+0.1f,Settings.theme.ClearColor().g+0.1f,Settings.theme.ClearColor().b+0.1f,notifications.get(i).getFadeout());
                WindowManager.shapeRenderer.roundendrect(WindowAPI.getWidth() - DISTANCE_RIGHT+IMAGE_MARGIN_RIGHT+notifications.get(i).getRollin(), UIVar.programmflaeche_y + MARGIN_RIGHT + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i))+PROGRESSBAR_MARGIN_BOTTOM, DISTANCE_RIGHT - MARGIN_RIGHT-IMAGE_MARGIN_RIGHT*2, 3, 2);

                WindowManager.shapeRenderer.setColor(Settings.theme.ClearColor().r+0.3f,Settings.theme.ClearColor().g+0.3f,Settings.theme.ClearColor().b+0.3f,notifications.get(i).getFadeout());
                WindowManager.shapeRenderer.roundendrect(WindowAPI.getWidth() - DISTANCE_RIGHT+IMAGE_MARGIN_RIGHT+notifications.get(i).getRollin(), UIVar.programmflaeche_y + MARGIN_RIGHT + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i))+PROGRESSBAR_MARGIN_BOTTOM, ((int) ((float) (DISTANCE_RIGHT - MARGIN_RIGHT - IMAGE_MARGIN_RIGHT * 2) / 100f * notifications.get(i).getDisplayedProgressbarValue())), 3, 2);

                WindowManager.shapeRenderer.end();

            }else{

               if(notifications.get(i).getButtonbar()!=null) {

                   notifications.get(i).getButtonbar().setX(WindowAPI.getWidth() - IMAGE_MARGIN_RIGHT - MARGIN_RIGHT+notifications.get(i).getRollin());
                   notifications.get(i).getButtonbar().setY(UIVar.programmflaeche_y + MARGIN_RIGHT + ((NOTIFICATION_MARGIN + HEIGHT) * (notifications.size() - 1 - i))+PROGRESSBAR_MARGIN_BOTTOM);
                   notifications.get(i).getButtonbar().setButton_h(10);
                   notifications.get(i).getButtonbar().draw(notifications.get(i).getFadeout());

               }

            }


            Gdx.gl.glDisable(GL20.GL_BLEND);

        }

        for (int i = 0; i < notifications.size(); i++) {


            if(notifications.get(i).isInrollin()&&notifications.get(i).getRollin()<=0) {
                notifications.get(i).setInrollin(false);
            }

            if(notifications.get(i).isInrollin()) {
                notifications.get(i).setRollin(notifications.get(i).getRollin()-MOVE_IN_SPEED);
            }

            if(notifications.get(i).getDisplayedProgressbarValue()>notifications.get(i).getProgressbarvalue()) {
                notifications.get(i).setProgressbarvalueis(notifications.get(i).getDisplayedProgressbarValue()-1);
            }
            if(notifications.get(i).getDisplayedProgressbarValue()<notifications.get(i).getProgressbarvalue()) {
                notifications.get(i).setProgressbarvalueis(notifications.get(i).getDisplayedProgressbarValue()+1);
            }


            if (System.currentTimeMillis() - notifications.get(i).getStarttime() > notifications.get(i).getAlivetime() && !notifications.get(i).isStayalive()&&!notifications.get(i).isFadingout()&&notifications.get(i).isCloseable()) {
                notifications.get(i).setFadingout(true);
            }

            if(notifications.get(i).isFadingout()) {
                if(notifications.get(i).getFadeout()<0){
                    if(notifications.get(i).getCloseListener()!=null) {
                        notifications.get(i).getCloseListener().change();
                    }
                    notifications.remove(notifications.get(i));
                }else{

                    notifications.get(i).setFadeout(notifications.get(i).getFadeout()-0.02f);


                }



            }



        }


    }





}
