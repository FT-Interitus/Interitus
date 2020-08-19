/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.BlockDropDownMenue;

import com.badlogic.gdx.graphics.Texture;

public class BlockDropDownItem {
    private Texture image;
    private String text;

    public BlockDropDownItem(Texture image, String text){
        this.image=image;
        this.text = text;
    }

    public Texture getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public void setText(String text) {
        this.text = text;
    }
}
