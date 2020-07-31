package de.ft.interitus.UI.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.check.Check;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.rightclick.RightClickButtonSelectEvent;

import de.ft.interitus.utils.ArrayList;

public class PopupMenue {
    private final int buttonheight = 20;
    private final SpriteBatch batch = new SpriteBatch();
    private final ArrayList<Button> buttons = new ArrayList<>(); //TODO Buttons kontrollieren die Maus kollision nur auf dem Text
    private final Texture popupButtonimage = new Texture("popupbuttonimage.png");
    private final Check check = new Check();
    int ispressed;
    private int x;
    private int y;
    private int ausgleichX = 0;
    private int ausgleichY = 0;
    private boolean show = false;

    public PopupMenue(String... it) {
        buttons.clear();
        for (int i = 0; i < it.length; i++) {
            Button b = new Button();
            b.setText(it[i]);

            buttons.add(b);
        }
    }

    public PopupMenue(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setBounds(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void rechtsKlickControlle() {

        if (Gdx.input.isButtonJustPressed(1) && (!check.isMouseover(this.x + ausgleichX, this.y + ausgleichY, 200, buttonheight * buttons.size()) || !show)) {
            this.x = Gdx.input.getX();
            this.y = Gdx.graphics.getHeight() - Gdx.input.getY();
            show = true;

        }

        if (Gdx.input.isButtonJustPressed(0) && !check.isMouseover(this.x + ausgleichX, this.y + ausgleichY, 200, buttonheight * buttons.size())) {
            show = false;
        }
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void draw() {
        if (show) {
            batch.begin();
            if (y + buttonheight * buttons.size() < Gdx.graphics.getHeight() && x + 200 < Gdx.graphics.getWidth()) {
                ausgleichX = 0;
                ausgleichY = 0;

            } else {
                if (x + 200 > Gdx.graphics.getWidth()) {
                    if (y + buttonheight * buttons.size() > Gdx.graphics.getHeight()) {
                        ausgleichX = -((x + 200) - Gdx.graphics.getWidth());
                        ausgleichY = -buttonheight * buttons.size();

                    } else {
                        ausgleichX = -((x + 200) - Gdx.graphics.getWidth());
                        ausgleichY = 0;
                    }
                } else {
                    ausgleichX = 0;
                    ausgleichY = -buttonheight * buttons.size();
                }
            }

            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setBounds(this.x + ausgleichX, this.y + (buttonheight * i) + ausgleichY, 200, buttonheight);
                buttons.get(i).setImage(popupButtonimage);
                buttons.get(i).draw();
            }


            batch.end();
        }

        ispressed = getPressed();
        if (ispressed != -1) {
            EventVar.rightClickEventManager.buttonclickedinwindow(new RightClickButtonSelectEvent(this, buttons.get(ispressed)));
            ispressed = -1;
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
            if (buttons.get(i).isjustPressed()) {
                p = i;
            }
        }
        return p;
    }
}
