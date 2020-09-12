/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;

public class ParameterVariableType {
    private final String type;
    private final String[] compatibleTypes;
    private final Texture textureconnector;
    private final Color wirecolor;

    public ParameterVariableType(String type,Texture textureconnector,Color wirecolor, String... compatibleTo) {
        this.compatibleTypes = compatibleTo;
        this.type = type;
        this.textureconnector = textureconnector;
        this.wirecolor = wirecolor;
    }


    public String getType() {
        return type;
    }

    public String[] getCompatibleTypes() {
        return compatibleTypes;
    }

    public Color getWirecolor() {
        return wirecolor;
    }

    public Texture getTextureconnector() {
        return textureconnector;
    }

    public boolean iscompatible(ParameterVariableType type) {

        if(type.getType().contentEquals(this.type)) {
            return true;
        }

        if(Arrays.asList(compatibleTypes).contains(type.getType())) {
            return true;
        }

        for(String string1:type.getCompatibleTypes()) {

            for(String string:this.getCompatibleTypes()) {
                if(string.contentEquals(string1)) {
                    return true;
                }
            }

        }
        return false;

    }

    @Override
    public String toString() {
        return type;
    }
}
