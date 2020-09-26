/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;



import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.Arrays;

public class ParameterVariableType {
    private final String type;
    private final String[] compatibleTypes;
    private final BufferedImage textureconnector;
    private final Color wirecolor;

    public ParameterVariableType(String type,BufferedImage textureconnector,Color wirecolor, String... compatibleTo) {
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

    public BufferedImage getTextureconnector() {
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
