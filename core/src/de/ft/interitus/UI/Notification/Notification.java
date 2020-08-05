/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.Notification;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.UI.UIElements.UIElements.Button;


public class Notification {

    private final Texture icon;
    private final String message;
    private boolean stayalive = false;
    private boolean closeable = true;
    private int alivetime = 5000;
    private long starttime =0;
    private float fadeout = 1.0f;
    private boolean fadingout = false;
    private final String title;
    private static final Button closeButton = new Button();
    private static int progressbarvalue = -1;



    public Notification(Texture icon,String title, String message) {
        this.icon = icon;
        this.message = message;
        this.title = title;
    }

    /**
     * Set the value of the ProgressBar in the Notification
     * If the value is -1 (default) the ProgressBar will be hidden
     * Note that the Notification will automatically expire if StayAlive is false (default)
     * @param progressbarvalue
     * @throws IllegalArgumentException
     */
    public Notification setProgressbarvalue(int progressbarvalue) {
        if(progressbarvalue>100||progressbarvalue<-1) {
            throw new IllegalArgumentException("Expected Value between -1 and 100");
        }
        Notification.progressbarvalue = progressbarvalue;

        return this;
    }



    /**
     * Tells the NotificationManager if the Notification will removed after the Notification expired
     * If the Notification doesn't fit on the screen it will disappear though
     * @param stayalive if true the Notification can expired
     * @return
     */

    public Notification setStayalive(boolean stayalive) {
        this.stayalive = stayalive;
        return this;
    }




    /**
     * If false the Notification can't be closed by the user and after expiring.
     * it will be ignored if the notification doesnt fit on the screen
     * @param closeable says if the Notification can disappear
     * @return
     */
    public Notification setCloseable(boolean closeable) {
        this.closeable = closeable;
        return this;
    }

    /**
     *
     * @param alivetime the time after the Notification will expire and disappear
     * @return
     */

    public Notification setAlivetime(int alivetime) {
        this.alivetime = alivetime;
        return this;
    }



    protected Texture getIcon() {
        return icon;
    }

    protected String getMessage() {
        return message;
    }

    protected boolean isCloseable() {
        return closeable;
    }

    protected int getAlivetime() {
        return alivetime;
    }

    protected void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    protected long getStarttime() {
        return starttime;
    }


    protected String getTitle() {
        return title;
    }


    protected float getFadeout() {
        return fadeout;
    }

    protected void setFadeout(float fadeout) {
        this.fadeout = fadeout;
    }

    protected boolean isFadingout() {
        return fadingout;
    }

    protected void setFadingout(boolean fadingout) {
        this.fadingout = fadingout;
    }

    protected static Button getCloseButton() {
        return closeButton;
    }


    protected boolean isStayalive() {
        return stayalive;
    }

    public static int getProgressbarvalue() {
        return progressbarvalue;
    }
}
