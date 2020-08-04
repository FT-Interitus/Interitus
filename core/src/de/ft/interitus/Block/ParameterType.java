/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

public class ParameterType {

    String typ;
    boolean isinput;
    boolean dropdown;
    String[] selectables;

    public ParameterType(String typ, boolean isinput, boolean dropdown) {
        this.typ = typ;
        this.isinput = isinput;
        this.dropdown = dropdown;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public boolean isIsinput() {
        return isinput;
    }

    public void setIsinput(boolean isinput) {
        this.isinput = isinput;
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

    public void setSelectables(String[] selectables) {
        this.selectables = selectables;
    }
}
