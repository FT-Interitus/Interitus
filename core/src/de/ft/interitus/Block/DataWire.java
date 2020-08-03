/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.utils.ShapeRenderer;

public class DataWire {

    int input_x = 0;
    int input_y = 0;
    int output_x = 0;
    int output_y = 0;
    private Parameter param_input;
    private Parameter param_output;


    public DataWire(Parameter param_input, Parameter param_output) {
        this.param_input = param_input;
        this.param_output = param_output;
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {

    }

    public Parameter getParam_input() {
        return param_input;
    }

    public void setParam_input(Parameter param_input) {
        this.param_input = param_input;
    }

    public Parameter getParam_output() {
        return param_output;
    }

    public void setParam_output(Parameter param_output) {
        this.param_output = param_output;
    }

}
