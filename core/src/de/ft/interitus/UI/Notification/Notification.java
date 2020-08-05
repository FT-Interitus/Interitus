/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.Notification;

import com.badlogic.gdx.graphics.Texture;

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


    public Notification(Texture icon,String title, String message) {
        this.icon = icon;
        this.message = message;
        this.title = title;
    }

    public Texture getIcon() {
        return icon;
    }

    public String getMessage() {
        return message;
    }


    public boolean isStayalive() {
        return stayalive;
    }

    public Notification setStayalive(boolean stayalive) {
        this.stayalive = stayalive;
        return this;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public Notification setCloseable(boolean closeable) {
        this.closeable = closeable;
        return this;
    }

    public void setAlivetime(int alivetime) {
        this.alivetime = alivetime;
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


    public String getTitle() {
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
}
