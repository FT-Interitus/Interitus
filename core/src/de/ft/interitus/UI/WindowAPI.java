/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;

public class WindowAPI {

    public static int getX() {
        return Gdx.input.getX();
    }

    public static int getY() {
        return Gdx.input.getY();
    }

    public static int getHeight() {
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

    public enum SystemCursors {
        Arrow,
        Ibeam,
        Crosshair,
        Hand,
        HorizontalResize,
        VerticalResize
    }

    public static void setSystemCursor(SystemCursors systemCursor) {
        switch (systemCursor) {
            case Hand -> Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            case Arrow -> Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            case Ibeam -> Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Ibeam);
            case Crosshair -> Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Crosshair);
            case VerticalResize -> Gdx.graphics.setSystemCursor(Cursor.SystemCursor.VerticalResize);
            case HorizontalResize -> Gdx.graphics.setSystemCursor(Cursor.SystemCursor.HorizontalResize);
        }
    }

    public static boolean isKeyPressed(int key) {
        return Gdx.input.isKeyPressed(key);
    }

    public static boolean isKeyJustPressed(int key) {
        return Gdx.input.isKeyJustPressed(key);
    }

    public static float getDeltaTime() {
       return Gdx.graphics.getDeltaTime();
    }


}
