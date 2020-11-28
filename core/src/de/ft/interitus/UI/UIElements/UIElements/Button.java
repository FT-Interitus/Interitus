/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ft.interitus.WindowManager;
import de.ft.interitus.UI.Animation;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.utils.ShapeRenderer;

public class Button implements UIElement {
    public static boolean disablepresscolorchange = false;
    public final int widthoverTextlinksrandabstand = 5;
    private final GlyphLayout glyphLayout = new GlyphLayout();
    public float hovertransparancy = 0.8f;
    public boolean widthoverText = false;
    private boolean isworking = false;
    private boolean ignore_uilock = false;
    private int x;
    private int y;
    private int w;
    private int h;
    private String text;
    private Texture image = null;
    private Texture image_mouseover = null;
    private Texture image_pressed = null;
    private Animation working_animation = null;
    private boolean visible = true;
    private boolean disable = false;
    private boolean flipX = false;
    private boolean flipY = false;
    private float transparency = 1.0f;
    private int text_pos_change_x = 0;
    private int text_pos_change_y = 0;


    public Button(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }

    public Button() {


    }


    public void setBounds(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }


    public boolean isjustPressed() {
        boolean pressed = false;

        if (!disable && !UIVar.isdialogeopend) {

            pressed = CheckMouse.isjustPressed(x, y, w, h, false);
            return pressed;

        } else {

            return false;

        }


    }

    public boolean isjustPressednormal() {
        if (!UIVar.uilocked || this.isIgnore_uilock()) {
            boolean pressed = false;
            if (!disable && !UIVar.isdialogeopend) {

                pressed = CheckMouse.isJustPressedNormal(x, y, w, h, false);
                return pressed;
            } else {
                return false;
            }


        } else {
            return false;
        }
    }


    public boolean isPresseded() {
        if (!UIVar.uilocked || this.isIgnore_uilock()) {
            if (!disable) {

                return CheckMouse.isPressed(x, y, w, h, false);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public boolean isMouseover() {

        if (!UIVar.uilocked || this.isIgnore_uilock()) {

            if (UIVar.isdialogeopend) {
                return false;
            } else {
                if (!disable) {

                    return CheckMouse.isMouseover(x, y, w, h, false);
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public void draw() { //TODO Konstellationen deutlicher machen
        if (text != null) {
            glyphLayout.setText(WindowManager.font, this.text);
            if (widthoverText) {
                this.w = (int) glyphLayout.width + 2 * widthoverTextlinksrandabstand;
            }
        }

        if (isVisible()) {
            if (image == null) {

                if (text == null) {

                    WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    WindowManager.shapeRenderer.roundendrect(this.x, this.y, this.w, this.h, 5);
                    WindowManager.shapeRenderer.end();
                }
            } else if (image_mouseover == null) {
                UI.UIbatch.begin();
                if (isMouseover()) {
                    UI.UIbatch.setColor(1, 1, 1, hovertransparancy);
                } else {
                    UI.UIbatch.setColor(1, 1, 1, transparency);
                }
                if (isMouseover() && Gdx.input.isButtonPressed(0) && !disablepresscolorchange) {
                    UI.UIbatch.setColor(1, 0.5f, 0.5f, transparency);
                }
                if (isDisable()) {
                    UI.UIbatch.setColor(1, 1, 1, 0.2f);
                }
                //batch.draw(image, this.x, this.y, this.w, this.h);
                UI.UIbatch.draw(image, this.x, this.y, this.w, this.h, 0, 0, image.getWidth(), image.getHeight(), this.flipX, this.flipY);
                UI.UIbatch.end();
            } else if (image_pressed == null) {
                UI.UIbatch.begin();
                UI.UIbatch.setColor(1, 1, 1, transparency);

                if (isMouseover()) {
                    UI.UIbatch.draw(image_mouseover, this.x, this.y, this.w, this.h, 0, 0, image.getWidth(), image.getHeight(), this.flipX, this.flipY);
                } else {
                    UI.UIbatch.draw(image, this.x, this.y, this.w, this.h, 0, 0, image.getWidth(), image.getHeight(), this.flipX, this.flipY);
                }


                UI.UIbatch.end();
            } else {
                UI.UIbatch.begin();

                if (!isworking) {
                    if (isDisable()) {
                        UI.UIbatch.setColor(1, 1, 1, 0.3f);
                    } else {
                        UI.UIbatch.setColor(1, 1, 1, transparency);
                    }


                    if (isMouseover()) {
                        UI.UIbatch.draw(image_mouseover, this.x, this.y, this.w, this.h, 0, 0, image.getWidth(), image.getHeight(), this.flipX, this.flipY);
                    } else {
                        UI.UIbatch.draw(image, this.x, this.y, this.w, this.h, 0, 0, image.getWidth(), image.getHeight(), this.flipX, this.flipY);
                    }
                    if (isMouseover() && Gdx.input.isButtonPressed(0)) {
                        UI.UIbatch.draw(image_pressed, this.x, this.y, this.w, this.h, 0, 0, image.getWidth(), image.getHeight(), this.flipX, this.flipY);
                    }
                } else {
                    UI.UIbatch.setColor(1, 1, 1, transparency);
                    UI.UIbatch.draw(this.working_animation.getAnimation(), x, y, w, h);
                }
                UI.UIbatch.end();
            }
            if (text != null) {
                UI.UIbatch.begin();
                UI.UIbatch.setColor(1, 1, 1, transparency);
                WindowManager.font.setColor(1,1,1,transparency);
                WindowManager.font.draw(UI.UIbatch, glyphLayout, x + widthoverTextlinksrandabstand+text_pos_change_x, y + glyphLayout.height + h / 2 - glyphLayout.height / 2+text_pos_change_y);


                UI.UIbatch.end();
            }
        }

    }

    @Override
    public void setAlpha(float alpha) {
        this.transparency = alpha;
    }

    public int getX() {
        return x;
    }

    public Object setX(int x) {
        this.x = x;
        return null;

    }

    public int getY() {
        return y;
    }

    public Object setY(int y) {
        this.y = y;
        return null;

    }

    public int getH() {
        return h;
    }

    public Object setH(int h) {
        this.h = h;
        return null;

    }

    @Override
    public Object setPosition(int x, int y) {
        this.x=x;
        this.y=y;
        return null;

    }

    public int getW() {
        return w;
    }

    public Button setW(int w) {
        this.w = w;
        return this;
    }

    public String getText() {
        return text;
    }

    public Button setText(String text) {
        this.text = text;
        return this;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public void setImage_mouseover(Texture image_mouseover) {
        this.image_mouseover = image_mouseover;
    }

    public void setImage_pressed(Texture image_pressed) {
        this.image_pressed = image_pressed;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    public boolean isIsworking() {
        return isworking;
    }

    public void setIsworking(boolean isworking) {
        this.isworking = isworking;
    }

    public Animation getWorking_animation() {
        return working_animation;
    }

    public void setWorking_animation(Animation working_animation) {
        this.working_animation = working_animation;
    }

    public boolean isIgnore_uilock() {
        return ignore_uilock;
    }

    public void setIgnore_uilock(boolean ignore_uilock) {
        this.ignore_uilock = ignore_uilock;
    }

    public float getTransparency() {
        return transparency;
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }

    public int getText_pos_change_x() {
        return text_pos_change_x;
    }

    public int getText_pos_change_y() {
        return text_pos_change_y;
    }

    public void setText_pos_change_x(int text_pos_change_x) {
        this.text_pos_change_x = text_pos_change_x;
    }

    public void setText_pos_change_y(int text_pos_change_y) {
        this.text_pos_change_y = text_pos_change_y;
    }
}


