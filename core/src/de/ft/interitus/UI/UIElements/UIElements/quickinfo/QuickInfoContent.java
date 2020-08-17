package de.ft.interitus.UI.UIElements.UIElements.quickinfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import de.ft.interitus.UI.UIElements.check.CheckMouse;

public class QuickInfoContent {
    private Rectangle mouseoverRect=new Rectangle();
    private String text;
    private String Key;
    protected boolean doonce=false;
    protected long zeitstempel;
    private boolean disabled=true;
    public QuickInfoContent(int x,int y,int w,int h,String text, String Key){
        this.mouseoverRect.set(x,y,w,h);
        this.text=text;
        this.Key=Key;
    }

    public void setMouseoverRect(int x,int y,int w,int h) {
        this.mouseoverRect.set(x,y,w,h);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getKey() {
        return Key;
    }

    protected boolean check(){
        boolean blub=false;
        if (CheckMouse.isMouseover(mouseoverRect)) {

            if (doonce) {
                zeitstempel = System.currentTimeMillis() + 2000;
                doonce = false;
            }
            if (zeitstempel < System.currentTimeMillis()) {
                blub=true;
            }
        } else {
            if (!doonce) {
                doonce = true;
                blub=false;

            }

        }
        return blub;

    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    protected boolean isDisabled() {
        return disabled;
    }
}


