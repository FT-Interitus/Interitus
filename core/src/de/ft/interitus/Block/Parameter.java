/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;


import com.badlogic.gdx.graphics.Texture;

public class Parameter {

    private final String Unit;
    private Object Parameter;
    private Texture ParameterTexture;
    private String ParameterName;
    private String ParameterDescription;
    private Block block;
    private ParameterType parameterType;

    public Parameter(Object parameter, Texture ParameterTexture, String ParameterName, String ParameterDescription, String Unit, ParameterType parameterType) {
        this.ParameterTexture = ParameterTexture;
        this.Parameter = parameter;
        this.ParameterName = ParameterName;
        this.ParameterDescription = ParameterDescription;
        this.Unit = Unit;

        this.parameterType = parameterType;


    }

    public Object getParameter() {
        return Parameter;
    }

    public void setParameter(Object parameter) {
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
}
