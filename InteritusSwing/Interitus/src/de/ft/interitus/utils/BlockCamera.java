package de.ft.interitus.utils;

import javax.swing.*;

public class BlockCamera {
    private final int move_x,move_y;
    private final JPanel panel;


    public BlockCamera(JPanel panel) {
        this.move_x = 0;
        this.move_y = 0;
        this.panel = panel;


    }


    public float unproject_x(float x) {

        return move_x+x;

    }


    public float unproject_y(float y) {

        return move_y+y;
    }


}
