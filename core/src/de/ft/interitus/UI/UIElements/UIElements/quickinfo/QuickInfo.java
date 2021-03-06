/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements.quickinfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.WindowAPI;
import de.ft.interitus.WindowManager;
import de.ft.interitus.utils.ArrayList;

public class QuickInfo {
    private static final float alphaMax = 1;
    private static final float alphaMin = 0;
    private String text;
    private int x;
    private int y;
    private BitmapFont font;
    private GlyphLayout glyphLayout;
    private boolean shown = false;
    private Color backgroundColor = new Color(0.5f, 0.5f, 0.5f, 1.0f);
    private Color textColor = new Color(0.0f, 0.0f, 0.0f, 1.0f);
    private int abstandvonrand = 5;
    private int eckenradius = 5;
    private boolean attachedToMouse = false;
    private float animationsAlphaPosition = 0;
    private float fadeInSpeed = 10;
    private float fadeOutSpeed = 15;

    private boolean doonce = false;
    private long zeitstempel;
    private Rectangle selfCheckRectangle = new Rectangle();
    private boolean selfCheck = false;

    private ArrayList<QuickInfoContent> selfCheckList = new ArrayList<>();


    public QuickInfo(int x, int y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        glyphLayout = new GlyphLayout();
        font = new BitmapFont();
    }


    public QuickInfoContent getContentOverKey(String Key) {
        QuickInfoContent qic = null;
        for (int i = 0; i < selfCheckList.size(); i++) {
            if (selfCheckList.get(i).getKey() == Key) {
                qic = selfCheckList.get(i);

            }
        }
        return qic;
    }

    public void disableall() {
        for (int i = 0; i < selfCheckList.size(); i++) {
            selfCheckList.get(i).setDisabled(true);
        }
    }

    public void RectangleSelfCheck() {
        for (int i = 0; i < this.selfCheckList.size(); i++) {
            if (this.selfCheckList.get(i).isDisabled()) {
                continue;
            }
            if (this.selfCheckList.get(i).check()) {
                this.text = this.selfCheckList.get(i).getText();
                fadeIn();
                break;
            } else {
                fadeOut();
            }
        }
/*
        if (CheckMouse.isMouseover(selfCheckRectangle)) {

            if (doonce) {
                zeitstempel = System.currentTimeMillis() + 2000;
                doonce = false;
            }
            if (zeitstempel < System.currentTimeMillis()) {
                setX(WindowAPI.getX());
                setY(WindowAPI.getHeight()-WindowAPI.getY());
                fadeIn();
            }
        } else {
            if (!doonce) {
                doonce = true;
                fadeOut();

            }

        }*/
    }

    /**
     * draws and updates the QuickInfo
     */
    public void update() {
        if (selfCheck) RectangleSelfCheck();

        if (attachedToMouse) {
            this.x = WindowAPI.getX();
            this.y = WindowAPI.getHeight() - WindowAPI.getY();
        }

        if (shown) {
            if (animationsAlphaPosition <= alphaMax - alphaMax / fadeInSpeed) {
                animationsAlphaPosition += alphaMax / fadeInSpeed;
            }
        } else if (!shown) {
            if (animationsAlphaPosition >= alphaMin) {
                animationsAlphaPosition -= alphaMax / fadeOutSpeed;
            }
        }

        if (animationsAlphaPosition < 0) {
            animationsAlphaPosition = 0;
        }

        this.glyphLayout.setText(this.font, this.text);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); //Enalble Alpha Rendering
        WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        backgroundColor.a = animationsAlphaPosition;
        WindowManager.shapeRenderer.setColor(backgroundColor);
        WindowManager.shapeRenderer.roundendrect(this.x, this.y, glyphLayout.width + abstandvonrand * 2, glyphLayout.height + abstandvonrand * 2, eckenradius);
        WindowManager.shapeRenderer.end();

        UI.UIbatch.begin();
        textColor.a = animationsAlphaPosition;
        font.setColor(textColor);
        font.draw(UI.UIbatch, glyphLayout, this.x + abstandvonrand, this.y + glyphLayout.height + abstandvonrand);
        UI.UIbatch.end();
    }

    /**
     * tells the QuickInfo to fade in
     */
    public void fadeIn() {
        if (!shown) {
            shown = true;
            //...
        }
    }

    /**
     * tells the QuickInfo to fade out
     */
    public void fadeOut() {
        if (shown) {
            shown = false;
            //...
        }
    }

    public void addSelfCheckField(QuickInfoContent QIC) {
        this.selfCheckList.add(QIC);
    }

    public ArrayList<QuickInfoContent> getSelfCheckList() {
        return selfCheckList;
    }

    public void setSelfCheckList(ArrayList<QuickInfoContent> selfCheckList) {
        this.selfCheckList = selfCheckList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public GlyphLayout getGlyphLayout() {
        return glyphLayout;
    }

    public void setGlyphLayout(GlyphLayout glyphLayout) {
        this.glyphLayout = glyphLayout;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public int getAbstandvonrand() {
        return abstandvonrand;
    }

    public void setAbstandvonrand(int abstandvonrand) {
        this.abstandvonrand = abstandvonrand;
    }

    public int getEckenradius() {
        return eckenradius;
    }

    public void setEckenradius(int eckenradius) {
        this.eckenradius = eckenradius;
    }

    public float getAnimationsAlphaPosition() {
        return animationsAlphaPosition;
    }

    public float getAlphaMax() {
        return alphaMax;
    }

    public float getAlphaMin() {
        return alphaMin;
    }

    public float getFadeInSpeed() {
        return fadeInSpeed;
    }

    public void setFadeInSpeed(float fadeInSpeed) {
        this.fadeInSpeed = fadeInSpeed;
    }

    public float getFadeOutSpeed() {
        return fadeOutSpeed;
    }

    public void setFadeOutSpeed(float fadeOutSpeed) {
        this.fadeOutSpeed = fadeOutSpeed;
    }

    public boolean isAttachedToMouse() {
        return attachedToMouse;
    }

    public QuickInfo setAttachedToMouse(boolean attachedToMouse) {
        this.attachedToMouse = attachedToMouse;
        return this;
    }

    public Rectangle getSelfCheckRectangle() {
        return selfCheckRectangle;
    }

    public void setSelfCheckRectangle(Rectangle selfCheckRectangle) {
        this.selfCheckRectangle = selfCheckRectangle;
    }

    public boolean isSelfCheck() {
        return selfCheck;
    }

    public QuickInfo setSelfCheck(boolean selfCheck) {
        this.selfCheck = selfCheck;
        return this;
    }

    public boolean isDoonce() {
        return doonce;
    }

    public void setDoonce(boolean doonce) {
        this.doonce = doonce;
    }

    public long getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(long zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

}


