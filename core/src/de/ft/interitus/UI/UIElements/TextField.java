/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.WindowAPI;
import de.ft.interitus.WindowManager;

public class TextField {

    private static final GlyphLayout glyphLayout = new GlyphLayout();
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private final int begrenzung = -1;
    BitmapFont font = new BitmapFont();
    Texture background;
    Texture Backgroundactive;
    private final CheckMouse checkMouse = new CheckMouse();
    Texture curser;
    private boolean active = false;
    private String input = "default";
    private boolean cursorstate = true;
    private long cursersave;
    private int TextAnordnung = 0;

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     * @deprecated Use VisUI
     */


    public TextField(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        background = new Texture("TextFieldBackground.png");
        Backgroundactive = new Texture("TextFieldBackgroundActive.png");
        curser = new Texture("curser.png");


        WindowManager.inputManager.addProcessor(new InputAdapter() {
            @Override
            public boolean keyTyped(char key) {
                if (active) {

                    if (input.length() <= begrenzung || begrenzung == -1) {
                        input = input + key;
                    }
                }
                return false;
            }

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                return false;
            }
        });
        WindowManager.inputManager.updateMultiplexer();
    }


    private void active() {
        if (checkMouse.isJustPressed(x, y, w, h, false)) {
            active = !active;
        }
        if (!CheckMouse.isMouseover(x, y, w, h, false) && WindowAPI.isButtonPressed(0)) {
            active = false;
        }
    }

    public void draw() {
        active();


        if (active) {

            if (WindowAPI.isKeyPressed(Input.Keys.DEL) && input.length() > 0) {

                input = input.substring(0, input.length() - 1);
            }
        }


        UI.UIbatch.begin();
        if (active) {
            UI.UIbatch.draw(Backgroundactive, x, y, w, h);

        } else {
            UI.UIbatch.draw(background, x, y, w, h);

        }
        //  font.draw(b, input, x+5, y + font.getLineHeight());


        switch (TextAnordnung) {
            case 0:
                glyphLayout.setText(font, input);
                font.draw(UI.UIbatch, glyphLayout, x + 5, y + glyphLayout.height + h / 2 - glyphLayout.height / 2);


                if (active && cursorstate) {
                    UI.UIbatch.draw(curser, x + glyphLayout.width + 7, y + 4, 2, 17);
                }

                break;
            case 1:
                glyphLayout.setText(font, input);
                font.draw(UI.UIbatch, glyphLayout, x + w / 2 - glyphLayout.width / 2, y + glyphLayout.height + h / 2 - glyphLayout.height / 2);


                if (active && cursorstate) {
                    UI.UIbatch.draw(curser, x + w / 2 + glyphLayout.width / 2 + 2, y + 4, 2, 17);
                }
                break;
        }

        UI.UIbatch.end();


        if (System.currentTimeMillis() > cursersave) {
            cursorstate = !cursorstate;
            cursersave = System.currentTimeMillis() + 500;
        }


    }

    public int getTextAnordnung() {
        return TextAnordnung;
    }

    public void setTextAnordnung(int textAnordnung) {
        TextAnordnung = textAnordnung;
    }
}
