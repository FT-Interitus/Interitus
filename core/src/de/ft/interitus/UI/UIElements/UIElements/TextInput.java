/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.FormattingFrame;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.WindowManager;
import de.ft.interitus.loading.AssetLoader;

import static com.badlogic.gdx.Gdx.input;

public class TextInput extends UIElement{
    private StringBuilder text;
    private String drawingText;
    private String defaultText = "";
    private boolean active=false;
    private final FormattingFrame formattingFrame = new FormattingFrame(this);
    private final GlyphLayout glyphLayout=new GlyphLayout();
    private BitmapFont bitmapFont ;
    private int CurserPosition=0;
    private boolean CursorVisible=true;
    private long timer=0;
    private boolean isPassword = false;
    private final CheckMouse checkMouse = new CheckMouse();
    static protected final char ENTER= '\r';
    static private final char TAB = '\t';
    static private final char DELETE = 127;
    static private final char BULLET = 8226;

    private boolean run_left = false;
    private boolean run_right = false;
    private double time_pressed_left = 0;
    private double time_pressed_right = 0;

    private float cursorPercentHeight = 0.8f;

    public static final long movedelay = 580;
    public static final long firstmovedelay = 600;

    public TextInput(String text) {
        this();
        this.text = new StringBuilder(text);
    }
    public TextInput(String text, BitmapFont bitmapFont){
        this(text);
        this.bitmapFont=bitmapFont;

    }

    @Override
    public UIElement setPosition(int x, int y) {
         this.formattingFrame.setPosition(x,y);
         return this;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        formattingFrame.setPosition(x,y);
        super.w=w;
        super.h=h;
    }

    @Override
    public UIElement setX(int x) {
        formattingFrame.setX(x);
        return this;
    }

    @Override
    public UIElement setY(int y) {
        formattingFrame.setY(y);
        return this;
    }

    public TextInput() {
        formattingFrame.setDrawInnerUIElement(false);
        formattingFrame.setFillColor(new Color(0.2f,0.2f,0.2f,0.2f));
        formattingFrame.setBorderColor(new Color(1f,0f,0f,1f));
        formattingFrame.setBorderThickness(2);
        super.h=20;
        super.w=200;
        this.bitmapFont = new BitmapFont();



        WindowManager.inputManager.addProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char key) {

                if(key==BULLET||key==ENTER||key==TAB)  return false;
                if(active) {
                    if (key != 8 && key != 127) {
                        text.insert(CurserPosition, key);
                        CurserPosition++;
                        resetBlink();

                    } else if (key == 8 && text.length() > 0&&text.length()>=CurserPosition) {
                        text.deleteCharAt(CurserPosition - 1);
                        CurserPosition--;
                        resetBlink();

                    } else if (key == 127 && text.length() > CurserPosition) {
                        text.deleteCharAt(CurserPosition);
                        resetBlink();

                    }
                }
                return false;
            }

        });

    }


    @Override
    public void draw() {
        if(checkMouse.isJustPressed(super.x,super.y,super.w,super.h, false))active=true;
        if(!CheckMouse.isMouseover(super.x,super.y,super.w,super.h, false) && Gdx.input.isButtonPressed(0))active=false;

        if(active){
            formattingFrame.setFillColor(new Color(0.4f,0.4f,0.4f,1f));
        }else{
            formattingFrame.setFillColor(new Color(0.2f,0.2f,0.2f,1f));
        }
        formattingFrame.draw();


        cursorMovement();

        this.drawingText = text.toString();

        if(this.isPassword) {
           this.drawingText = "*".repeat(this.drawingText.length());
        }

        this.glyphLayout.setText(this.bitmapFont, this.drawingText);
        UI.UIbatch.begin();
        this.bitmapFont.draw(UI.UIbatch, glyphLayout, this.x+2, this.y+this.h/2+glyphLayout.height/2);
        UI.UIbatch.end();
        if(active){
            WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            WindowManager.shapeRenderer.setColor(1f,1f,1f,1f);
            try {
                if (CursorVisible) {
                    glyphLayout.setText(this.bitmapFont, this.drawingText.substring(0, this.CurserPosition));
                    int pos = (int) (this.x + this.glyphLayout.width + formattingFrame.getBorderThickness() + 2);
                    WindowManager.shapeRenderer.rectLine(pos, this.y + this.formattingFrame.getBorderThickness() , pos, this.y - this.formattingFrame.getBorderThickness() + this.h - 1, 2);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            WindowManager.shapeRenderer.end();
        }
        if(System.currentTimeMillis() > this.timer){
            this.CursorVisible=!this.CursorVisible;
            this.timer=System.currentTimeMillis()+500;
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setBitmapFont(BitmapFont bitmapFont) {
        this.bitmapFont = bitmapFont;
    }

    public BitmapFont getBitmapFont() {
        return bitmapFont;
    }

    public String getText() {
        return text.toString();
    }

    public String getDefaultText() {
        return defaultText;
    }

    public GlyphLayout getGlyphLayout() {
        return glyphLayout;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public void setText(String text) {
        this.text = new StringBuilder(text);
    }

    public FormattingFrame getFormattingFrame() {
        return formattingFrame;
    }


    public void cursorMovement() {

        if (input.isKeyPressed(Input.Keys.LEFT)) {
            if (!run_left) {
                time_pressed_left = System.currentTimeMillis();
                run_left = true;
                if(CurserPosition>0) {
                    CurserPosition--;
                    resetBlink();
                }
            } else {
                if (System.currentTimeMillis() - time_pressed_left > firstmovedelay) {
                    time_pressed_left = System.currentTimeMillis() - movedelay;
                    if(CurserPosition>0) {
                        CurserPosition--;
                        resetBlink();

                    }
                }
            }
        } else {
            run_left = false;
        }


        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            if (!run_right) {
                time_pressed_right = System.currentTimeMillis();
                run_right = true;
                if(CurserPosition<this.text.length()) {
                    CurserPosition++;
                    resetBlink();

                }
            } else {
                if (System.currentTimeMillis() - time_pressed_right > firstmovedelay) {
                    time_pressed_right = System.currentTimeMillis() - movedelay;
                    if(CurserPosition<this.text.length()) {
                        CurserPosition++;

                        resetBlink();
                    }
                }
            }
        } else {
            run_right = false;
        }
    }
    private void resetBlink() {
        this.timer=System.currentTimeMillis()+500;
        this.CursorVisible = true;
    }

   /* protected void drawCursor (Drawable cursorPatch, Batch batch, BitmapFont font, float x, float y) {
        float cursorHeight = this.h * cursorPercentHeight;
        float cursorYPadding = (textHeight - cursorHeight) / 2;
        cursorPatch.draw(batch,
                x + textOffset + glyphPositions.get(cursor) - glyphPositions.get(visibleTextStart) + fontOffset + font.getData().cursorX,
                y - textHeight - font.getDescent() + cursorYPadding, cursorPatch.getMinWidth(), cursorHeight);
    }

    */


}
