/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.Notification;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.UI.UIElements.UIElements.Button;


public class Notification {

    private  Texture icon;
    private  String message;
    private boolean stayalive = false;
    private boolean closeable = true;
    private int alivetime = 5000;
    private long starttime =0;
    private float fadeout = 1.0f;
    private boolean fadingout = false;
    private  String title;
    private static final Button closeButton = new Button();
    private  int progressbarvalue = -1;
    private  int progressbarvalueis = -1;
    private boolean displayed = false;

    /***
     * Creates a new Notification which can send to the NotificationManager
     * A Notification can only be send once.
     * @param icon A Texture which will be displayed in the top right
     * @param title A Title
     * @param message The Message of the Notification
     */

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
        this.progressbarvalue = progressbarvalue;

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

    /**
     * Get the current ProgressBar Value if ProgressBar is hidden -1 will be returned
     * @return
     */
    public int getProgressbarvalue() {
        return progressbarvalue;
    }


    public void setIcon(Texture icon) {
        this.icon = icon;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Will fade out Message and than delete it
     *it only needs to be used if the Message isn't Closeable or the Message stays alive
     *
     */

    public void close() {

        setFadingout(true);

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




    protected boolean isDisplayed() {
        return displayed;
    }

    protected void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }


    protected  int getProgressbarvalueis() {
        return progressbarvalueis;
    }

    protected  void setProgressbarvalueis(int progressbarvalueis) {
        this.progressbarvalueis = progressbarvalueis;
    }
}


