/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino;

import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.ParameterVariableType;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.projecttypes.ProjectVariable;

public class ArduinoVariable implements ProjectVariable {


    private String name;
    private ParameterVariableType type;

    public ArduinoVariable(String name, ParameterVariableType type) {
        this.name = name;
        this.type = type;

    }

    public String getCode() {

        return this.type.getType()+" "+this.name+";";


    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ParameterVariableType getType() {
        return type;
    }

    @Override
    public void setType(ParameterVariableType type) {
        this.type = type;

    }

    @Override
    public void setName(String name) {

        this.name = name;

    }
}
