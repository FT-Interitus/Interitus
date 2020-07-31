package de.ft.interitus.utils;

import com.badlogic.gdx.files.FileHandle;

public class ShapeRenderer extends com.badlogic.gdx.graphics.glutils.ShapeRenderer {
    public void roundendrect( int x, int y, int w, int h, int r) {
        w = w - r * 2;
        h = h - r * 2;
       circle(x + r, y + r, r);
        circle(x + r + w, y + r, r);
        circle(x + r, y + r + h, r);
       circle(x + r + w, y + r + h, r);

       rect(x + r, y, w, r*2);
       rect(x + r, y + h, w, r * 2);
       rect(x, y + r, r * 2, h);
     rect(x + w, y + r, r * 2, h);

     rect(x + r, y + r, w, h);
    }
}
