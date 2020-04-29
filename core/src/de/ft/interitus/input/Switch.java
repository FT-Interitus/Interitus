package de.ft.interitus.input;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.input.check.Check;

import java.util.Random;

public class Switch {
    Texture background;
    SpriteBatch b = new SpriteBatch();
    Random random = new Random();
    private int x;
    private int y;
    private int w = 50;
    private int h = 25;
    private final int wecht = 50;
    private final int hecht = 25;
    private int xecht;
    private int yecht;
    private boolean state = false;
    private boolean ismoving = false;
    private int switchpos = 0;
    private int dm = 19;
    private final int dmecht = 19;
    private boolean wackeln = true;
    private boolean visible = true;
    private boolean disable = false;
    private int wackelstärke = 1;
    private long wwwave = 0;
    private float size = 1;
    private int multiplikator = 3;
    private final int multiplikatorecht = 3;
    private final float[] farbehebel = {1, 1, 1, 1};
    private final Vector2 mousesave = new Vector2();
    private final Check check = new Check();
    private boolean touched;
    private Texture Backgroundgreen;
    private Texture inside;

    public Switch(int x, int y) {
        this.x = x;
        this.y = y;
        this.xecht = x;
        this.yecht = y;


    }

    public Switch() {

        this.xecht = x;
        this.yecht = y;
    }

    public void setBounds(int x, int y) {
        this.x = x;
        this.y = y;
        this.xecht = x;
        this.yecht = y;

    }


    public boolean isMouseover() {
        if (!disable) {

            return check.isMouseover(x, y, w, h);
        } else {
            return false;
        }
    }

    public boolean isjustPressed() {
        boolean pressed = false;
        if (!disable) {

            pressed = check.isjustPressed(x, y, w, h);

        } else {
            return false;
        }

        return pressed;

    }

    private void update() {
        if (isjustPressed()) {
            if (disable == false) {
                state = !state;
            } else {
                wwwave = System.currentTimeMillis() + 500;
            }
        }
    }

    public void draw() {

        update();


        if (System.currentTimeMillis() < wwwave) {
            x = xecht + (wackelstärke * -1 + random.nextInt(wackelstärke * 3));
            y = yecht + (wackelstärke * -1 + random.nextInt(wackelstärke * 3));
        } else {
            if (wwwave != 0) {
                x = xecht;
                y = yecht;
                wwwave = 0;
            }

        }


        if (visible) {
            b.begin();


            b.setColor(1, 1, 1, 1 - (1f / 26f) * (float) switchpos);
            b.draw(background, x, y, w, h);
            b.setColor(1, 1, 1, (1f / 26f) * (float) switchpos);
            b.draw(Backgroundgreen, x, y, w, h);

            b.setColor(farbehebel[0], farbehebel[1], farbehebel[2], farbehebel[3]);
            b.draw(inside, x + multiplikator + switchpos, y + multiplikator, dm, dm);
            b.end();
        }

        if (state == false && switchpos >= 0) {
            switchpos = switchpos - 1;
            ismoving = true;
        } else if (state == true && switchpos <= w - dm - multiplikator - multiplikator) {
            switchpos = switchpos + 1;
            ismoving = true;
        } else {
            ismoving = false;
        }


        if (ismoving == true) {

            //x=x+(-1+random.nextInt(3));
            // y=y+(-1+random.nextInt(3));

            if (wackeln == true) {
                x = xecht + (wackelstärke * -1 + random.nextInt(wackelstärke * 3));
                y = yecht + (wackelstärke * -1 + random.nextInt(wackelstärke * 3));
            }

        } else {
            x = xecht;
            y = yecht;

        }

    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getXecht() {
        return xecht;
    }

    public int getYecht() {
        return yecht;
    }

    public boolean isIsmoving() {
        return ismoving;
    }

    public boolean isWackeln() {
        return wackeln;
    }

    public void setWackeln(boolean wackeln) {
        this.wackeln = wackeln;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getWackelstärke() {
        return wackelstärke;
    }

    public void setWackelstärke(int wackelstärke) {
        this.wackelstärke = wackelstärke;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
        w = (int) (wecht * size);
        h = (int) (hecht * size);
        dm = (int) (dmecht * size);
        multiplikator = (int) (multiplikatorecht * size);

    }

    public void setHebelColor(float r, float g, float b, float a) {
        farbehebel[0] = r;
        farbehebel[1] = g;
        farbehebel[2] = b;
        farbehebel[3] = a;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public void setBackgroundgreen(Texture backgroundgreen) {
        this.Backgroundgreen = backgroundgreen;
    }

    public void setInside(Texture inside) {
        this.inside = inside;
    }
}
