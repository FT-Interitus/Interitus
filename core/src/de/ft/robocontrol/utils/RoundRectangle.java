package de.ft.robocontrol.utils;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RoundRectangle {


    public static void abgerundetesRechteck(ShapeRenderer renderer, int x, int y, int w, int h, int r) {
        w = w - r * 2;
        h = h - r * 2;
        renderer.circle(x + r, y + r, r);
        renderer.circle(x + r + w, y + r, r);
        renderer.circle(x + r, y + r + h, r);
        renderer.circle(x + r + w, y + r + h, r);

        renderer.rect(x + r, y, w, 40);
        renderer.rect(x + r, y + h, w, r * 2);
        renderer.rect(x, y + r, r * 2, h);
        renderer.rect(x + w, y + r, r * 2, h);

        renderer.rect(x + r, y + r, w, h);
    }
}
