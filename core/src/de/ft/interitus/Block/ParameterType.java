/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

public class ParameterType {

    String typ;
    boolean output;
    boolean dropdown;
    String[] selectables;

    public ParameterType(String typ, boolean output, boolean dropdown) {
        this.typ = typ;
        this.output = output;
        this.dropdown = dropdown;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
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

    public String[] getSelectables() {
        return selectables;
    }

    public ParameterType setSelectables(String[] selectables) {
        this.selectables = selectables;
        return this;
    }
}
