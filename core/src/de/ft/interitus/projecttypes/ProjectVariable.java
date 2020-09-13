/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

public interface ProjectVariable {

    String getName();
    ParameterVariableType getType();
    void setType(ParameterVariableType type);
    void setName(String name);

}
