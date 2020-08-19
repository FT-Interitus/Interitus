/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import java.util.Arrays;

public class ParameterVariableType {
    private String type;
    private String[] compatibleTypes;
    public ParameterVariableType(String type, String... compatibleTo) {
        this.compatibleTypes = compatibleTo;
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public String[] getCompatibleTypes() {
        return compatibleTypes;
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
}
