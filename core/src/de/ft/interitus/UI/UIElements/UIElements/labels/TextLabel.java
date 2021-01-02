/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements.labels;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.WindowManager;

public class TextLabel extends UIElement {
    String text="";
    GlyphLayout glyphLayout=new GlyphLayout();

    public TextLabel(){

    }
    public TextLabel(String text){
        this.text=text;
    }

    public void setText(String text){
        this.text=text;
    }



    public void draw() {
        glyphLayout.setText(WindowManager.font, this.text);
        UI.UIbatch.begin();
        w=(int)(glyphLayout.width+ margin *2);
        h=(int)(glyphLayout.height+ margin *2);

        WindowManager.font.draw(UI.UIbatch, glyphLayout, this.x + this.w / 2f - glyphLayout.width / 2 + margin, y + this.h / 2f + glyphLayout.height/2);
        UI.UIbatch.end();
    }


}
