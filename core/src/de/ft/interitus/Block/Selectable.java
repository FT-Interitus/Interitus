/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

public class Selectable {
    private String DropDownText ="";
    private String CodeReplacemetText="";
    private String BlockParamText="";
    public Selectable(String text){
        this.DropDownText = text;
        this.CodeReplacemetText = text;
        this.BlockParamText = text;
    }
    public Selectable(String DropDownText,String BlockParamText){
        this.DropDownText=DropDownText;
        this.BlockParamText=BlockParamText;
    }
    public Selectable setCodeReplacement(String replacement){
        this.CodeReplacemetText=replacement;
        return this;
    }

    @Override
    public String toString() {
        return getDropDownText();
    }

    public String getDropDownText() {
        return DropDownText;
    }

    public String getBlockText() {
        return this.BlockParamText;
    }
}
