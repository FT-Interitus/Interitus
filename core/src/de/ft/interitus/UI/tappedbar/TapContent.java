package de.ft.interitus.UI.tappedbar;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.UI.UIElements.UIElements.Button;

import de.ft.interitus.utils.ArrayList;

public class TapContent {

    Button tab_button = new Button();
    ArrayList<TapItem> items = new ArrayList<>();

    public TapContent(Texture img, TapItem... ti) {

        //items.add(new TapBarBlockItem( new Wait(), AssetLoader.aktion_mittlerermotor));
        items.clear();
        for (int i = 0; i < ti.length; i++) {
            items.add(ti[i]);
        }
        Button.disablepresscolorchange = true;
        tab_button.hovertransparancy = 0.9f;
        tab_button.setImage(img);
    }

    public TapContent(Texture img) {
        Button.disablepresscolorchange = true;
        tab_button.hovertransparancy = 0.9f;
        //items.add(new TapBarBlockItem( new Wait(), AssetLoader.aktion_mittlerermotor));

        tab_button.setImage(img);
    }

    public void addItem(TapItem ti) {
        items.add(ti);
    }

    public void clear() {
        items.clear();
    }

    public void setItems(TapItem... ti) {
        items.clear();
        for (int i = 0; i < ti.length; i++) {
            items.add(ti[i]);
        }
    }


    public Button getTab_button() {
        return tab_button;
    }

    public void setTab_button(Button tab_button) {
        this.tab_button = tab_button;
    }
}
