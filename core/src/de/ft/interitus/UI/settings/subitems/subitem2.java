package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.Theme.RegisteredThemes;
import de.ft.interitus.UI.settings.SettingsUI;

import java.util.ArrayList;

public class subitem2 {
    public static VisSelectBox<String> themes;
    static String[] items;
    public static void add(VisTable builder) {
        themes = new VisSelectBox<>();

        items = new String[RegisteredThemes.themes.size()];


        for(int i=0;i< RegisteredThemes.themes.size();i++) {

            items[i] = RegisteredThemes.themes.get(i).getName();
        }
        themes.setItems(items);

        themes.setSelected(Settings.theme.getName());

        themes.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                for(int i=0;i<RegisteredThemes.themes.size();i++) {
                    if(RegisteredThemes.themes.get(i).getName().contains(themes.getSelected())) {
                        Settings.theme = RegisteredThemes.themes.get(i);
                        break;
                    }
                }
            }
        });

        builder.add(new VisLabel("Wähle ein Theme aus")).expandX().fillY().padBottom(40);
        builder.row();
        builder.add(themes).expandX().fillY();

    }
}
