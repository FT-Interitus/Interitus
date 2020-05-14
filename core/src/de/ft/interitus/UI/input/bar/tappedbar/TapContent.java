package de.ft.interitus.UI.input.bar.tappedbar;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.TapBarBlockItem;

import de.ft.interitus.UI.input.Button;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.Wait;

import java.util.ArrayList;

public class TapContent {

    Button tab_button=new Button();
    ArrayList<TapItem>items=new ArrayList<>();

    public TapContent(Texture img){

        items.add(new TapBarBlockItem(new Wait(), AssetLoader.aktion_mittlerermotor));

        tab_button.setImage(img);
    }

    public Button getTab_button() {
        return tab_button;
    }

    public void setTab_button(Button tab_button) {
        this.tab_button = tab_button;
    }
}
