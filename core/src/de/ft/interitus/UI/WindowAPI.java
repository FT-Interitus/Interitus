/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;

public class WindowAPI {

    public static int getX() {
        return Gdx.input.getX();
    }

    public static int getY() {
        return Gdx.input.getY();
    }

    public static int getHeight(){
       return Gdx.graphics.getHeight();
    }

    public static int getWidth() {
        return Gdx.graphics.getWidth();
    }

    public static boolean isButtonPressed(int Button) {
        return Gdx.input.isButtonPressed(Button);
    }

    public static boolean isButtonJustPressed(int Button) {
        return Gdx.input.isButtonJustPressed(Button);
    }


}
