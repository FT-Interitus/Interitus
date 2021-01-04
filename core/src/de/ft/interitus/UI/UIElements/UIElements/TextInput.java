/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.FormattingFrame;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.WindowManager;

public class TextInput extends UIElement{
    private String text="";
    private String defaultText = "";
    private boolean active=false;
    private final FormattingFrame formattingFrame = new FormattingFrame(this);
    private final GlyphLayout glyphLayout=new GlyphLayout();
    private BitmapFont bitmapFont;
    private int CurserPosition=1;
    private boolean CursorVisible=true;
    private long timer=0;

    public TextInput(String text) {
        this();
        this.text=text;
    }
    public TextInput(String text, BitmapFont bitmapFont){
        this();
        this.text=text;
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
        this.bitmapFont=new BitmapFont();
        super.h=20;
        super.w=200;



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
                if(active)if(key!=8) {
                    text += key;
                }else if(text.length()>0){
                    text=text.substring(0,text.length()-1);
                    System.out.println("del");
                }
                return false;
            }

        });

    }


    @Override
    public void draw() {
        if(CheckMouse.isJustPressedNormal(super.x,super.y,super.w,super.h, false))active=true;
        if(!CheckMouse.isMouseover(super.x,super.y,super.w,super.h, false) && Gdx.input.isButtonPressed(0))active=false;

        if(active){
            formattingFrame.setFillColor(new Color(0.4f,0.4f,0.4f,1f));
        }else{
            formattingFrame.setFillColor(new Color(0.2f,0.2f,0.2f,1f));
        }
        formattingFrame.draw();

        this.glyphLayout.setText(this.bitmapFont, this.text);
        UI.UIbatch.begin();
        this.bitmapFont.draw(UI.UIbatch, glyphLayout, this.x+2, this.y+this.h/2+glyphLayout.height/2);
        UI.UIbatch.end();
        if(active){
            WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            WindowManager.shapeRenderer.setColor(1f,1f,1f,1f);
            try {
                if (CursorVisible) if (CurserPosition == -1 || this.CurserPosition==1) {
                    int pos = (int) (this.x + this.glyphLayout.width + formattingFrame.getBorderThickness() + 2);
                    WindowManager.shapeRenderer.rectLine(pos, this.y + this.formattingFrame.getBorderThickness() + 1, pos, this.y - this.formattingFrame.getBorderThickness() + this.h - 1 * 2, 2);
                } else if (this.CurserPosition > 0) {
                    glyphLayout.setText(this.bitmapFont, this.text.substring(0, this.CurserPosition));
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
        return text;
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
        this.text = text;
    }

    public FormattingFrame getFormattingFrame() {
        return formattingFrame;
    }
}
