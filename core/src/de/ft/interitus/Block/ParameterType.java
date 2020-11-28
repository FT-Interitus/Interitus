/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import de.ft.interitus.projecttypes.ParameterVariableType;

import java.util.ArrayList;

public class ParameterType {

    ParameterVariableType variableType;
    private boolean output;
    private boolean dropdown = false;
    private Selectable selected = null;
    ArrayList<Selectable> selectables = new ArrayList<>();

    public ParameterType(ParameterVariableType variableType, boolean output) {
        this.variableType = variableType;
        this.output = output;
    }

    public ParameterVariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(ParameterVariableType variableType) {
        this.variableType = variableType;
    }

    public boolean isOutput() {
        return output;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }

    public boolean isDropdown() {
        return dropdown;
    }

    public void setDropdown(boolean dropdown) {
        this.dropdown = dropdown;
    }

    public ArrayList<Selectable> getSelectables() {
        return selectables;
    }

    public ParameterType setSelectables(Selectable... selectables) {
        for (int i = 0; i < selectables.length; i++) {
            this.dropdown = true;
            this.selectables.add(selectables[i]);
        }
        return this;
    }

    public String[] getDropDownList() {
        String export[] = new String[selectables.size()];
        for (int i = 0; i < selectables.size(); i++) {
            export[i] = selectables.get(i).getDropDownText();
        }
        return export;
    }

    public Selectable getSelected() {
        if (selected == null)
            selected = selectables.get(0);
        return selected;
    }

    public void setSelected(Selectable selected) {
        this.selected = selected;
    }
}
