/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;


import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.utils.ArrayList;

public class Parameter {
    private String varName;
    private final String Unit;
    private String Parameter;
    private Texture ParameterTexture;
    private String ParameterName;
    private String ParameterDescription;
    private Block block;
    private ParameterType parameterType;
    private ArrayList<DataWire> DataWire = new ArrayList<>();
    private int x = 0;
    private int y = 0;
    private final boolean varname;

    public Parameter(String parameter, Texture ParameterTexture, String ParameterName, String ParameterDescription, String Unit, ParameterType parameterType, boolean varname) {
        this.ParameterTexture = ParameterTexture;
        this.Parameter = parameter;
        this.ParameterName = ParameterName;
        this.ParameterDescription = ParameterDescription;
        this.Unit = Unit;

        this.parameterType = parameterType;
        this.varname = varname;


    }

    public Object getParameter() {
        return this.toString();
    }

    public String getBlockParameterContent() {
        if (this.getParameterType().isDropdown()) {

            return this.getParameterType().getSelected().getBlockText();

        }
        return this.toString();
    }


    public void setParameter(String parameter) {
        Parameter = parameter;
    }

    public String getParameterDescription() {
        return ParameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        ParameterDescription = parameterDescription;
    }

    public String getParameterName() {
        return ParameterName;
    }

    public void setParameterName(String parameterName) {
        ParameterName = parameterName;
    }

    public Texture getParameterTexture() {
        return ParameterTexture;
    }

    public void setParameterTexture(Texture parameterTexture) {
        ParameterTexture = parameterTexture;
    }

    public String getUnit() {
        return Unit;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
    }


    public ArrayList<DataWire> getDataWires() {
        return DataWire;
    }

    protected void setDataWire(ArrayList<DataWire> dataWire) {
        DataWire = dataWire;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }


    @Override
    public String toString() {
        if (!varname) {
            return Parameter.toString();
        }

        if (getDataWires().size() > 0) {
            return DataWire.get(0).getParam_input().getVarName();
        } else {
            return Parameter.toString();
        }
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }


}
