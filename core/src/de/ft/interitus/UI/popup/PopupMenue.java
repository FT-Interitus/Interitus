/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.utils.ArrayList;

public class PopupMenue {
    private static final int DISTANCE = 10;
    private static final int WIDTH = 200;
    private final int buttonheight = 20;
    private final ArrayList<Button> buttons = new ArrayList<>();
    private final Texture popupButtonimage = new Texture("popupbuttonimage.png");
    int ispressed;
    private int priority;
    private int x;
    private boolean mouseunderpopup = false;
    private boolean mouserightpopup = false;
    private int y;
    private boolean show = false;
    private int ausgleichX;
    private int ausgleichY;

    public PopupMenue(int priority, String... it) {
        buttons.clear();
        for (int i = 0; i < it.length; i++) {
            Button b = new Button();
            b.setText(it[i]);

            buttons.add(b);
        }
        this.priority = priority;
    }


    public void setBounds(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void rightKlickControlle() {


        if (Gdx.input.isButtonJustPressed(0) && !CheckMouse.isMouseover(this.x + ausgleichX, this.y - (buttonheight * buttons.size()), WIDTH, buttonheight * buttons.size(), false)) {
            show = false;
        }
    }


    public void draw() {
        if (show) {
            rightKlickControlle();

            this.ausgleichX = 0;
            this.ausgleichY =0;
            mouseunderpopup = false;
            mouserightpopup = false;


            if (this.y - (buttons.size() * buttonheight) - DISTANCE < 0) {
                mouseunderpopup = true;

            }


            if((this.x +WIDTH)+DISTANCE>Gdx.graphics.getWidth()) {

                mouserightpopup = true;
            }


            if(mouserightpopup) {

                this.ausgleichX= (-WIDTH);
            }

            if(mouseunderpopup) {

                this.ausgleichY = buttons.size()*buttonheight;
            }

            for (int i = buttons.size() - 1; i >= 0; i--) {
                buttons.get(i).setBounds(this.x + ausgleichX, this.y - (buttonheight * buttons.size())+(buttonheight*i) + ausgleichY, WIDTH, buttonheight);
                buttons.get(i).setImage(popupButtonimage);
                buttons.get(i).draw();
            }


        }

        ispressed = getPressed();
        if (ispressed != -1) {

            this.show = false;

        }


    }

    public void addItem(String text) {
        Button b = new Button();
        b.setText(text);
        buttons.add(b);

    }

    public void setItems(String... it) {
        buttons.clear();
        for (int i = 0; i < it.length; i++) {
            Button b = new Button();
            b.setText(it[i]);
            buttons.add(b);
        }
    }

    private int getPressed() {
        int p = -1;
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isjustPressednormal()) {
                p = i;
            }
        }
        return p;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }


}
