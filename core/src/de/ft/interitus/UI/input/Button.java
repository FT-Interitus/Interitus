package de.ft.interitus.UI.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.UI.input.check.Check;
import de.ft.interitus.Var;
import de.ft.interitus.utils.RoundRectangle;

public class Button {
    BitmapFont font = new BitmapFont();
    private int x;
    private int y;
    private int w;
    private int h;
    private String text;
    private Texture image = null;
    private boolean visible = true;
    private boolean disable = false;
    private final SpriteBatch batch = new SpriteBatch();
    private final ShapeRenderer s = new ShapeRenderer();
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private final Check check = new Check();
    private boolean flipX=false;
    private boolean flipY=false;
    public float hovertransparancy = 0.8f;
    public static boolean disablepresscolorchange = false;


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
        if (!disable&&! Var.isdialogeopend) {

            pressed = check.isjustPressed(x, y, w, h);

        } else {
            return false;
        }

        return pressed;

    }

    public boolean isjustPressednormal(){
        boolean pressed = false;
        if (!disable&&!Var.isdialogeopend) {

            pressed = check.isJustPressedNormal(x, y, w, h);

        } else {
            return false;
        }

        return pressed;
    }


    public boolean isPresseded() {
        if (!disable) {

            return check.isPressed(x, y, w, h);
        } else {
            return false;
        }
    }


    public boolean isMouseover() {
        if (!disable) {

            return check.isMouseover(x, y, w, h);
        } else {
            return false;
        }
    }

    public void draw() {
        if (isVisible()) {
            if (image == null) {

                s.begin(ShapeRenderer.ShapeType.Filled);
                RoundRectangle.abgerundetesRechteck(s, this.x, this.y, this.w, this.h, 5);
                s.end();
            } else {
                batch.begin();
                if (isMouseover()) {
                    batch.setColor(1, 1, 1, hovertransparancy);
                } else {
                    batch.setColor(1, 1, 1, 1);
                }
                if (isMouseover() && Gdx.input.isButtonPressed(0)&&!disablepresscolorchange) {
                    batch.setColor(1, 0.5f, 0.5f, 1);
                }
                if (isDisable()) {
                    batch.setColor(1, 1, 1, 0.2f);
                }
                //batch.draw(image, this.x, this.y, this.w, this.h);
                batch.draw(image,this.x,this.y,this.w,this.h,0,0,image.getWidth(),image.getHeight(),this.flipX,this.flipY);
                batch.end();
            }
            if (text != null) {
                batch.begin();

                glyphLayout.setText(font, this.text);
                font.draw(batch, glyphLayout, x + 5, y + glyphLayout.height + h / 2 - glyphLayout.height / 2);

                batch.end();
            }
        }
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

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
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

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }
}


