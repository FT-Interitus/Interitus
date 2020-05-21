package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.shortcut.ShortCut;

import java.util.ArrayList;

public class subitem6 {
    public static ArrayList<ShortCutEinstellung>shortCutEinstellungen=new ArrayList<>();
    public static void add(VisTable builder) {
        // ---

        VisTable table = new VisTable();

        for(int i=0;i< CheckShortcuts.shortCuts.size();i++) {
            if(CheckShortcuts.shortCuts.get(i).getName()!=null) {
                shortCutEinstellungen.add(new ShortCutEinstellung(i,table,CheckShortcuts.shortCuts.get(i)));

            }else{
                shortCutEinstellungen.add(new ShortCutEinstellung(i,table,CheckShortcuts.shortCuts.get(i)));

            }
        }

        VisScrollPane scrollPane = new VisScrollPane(table);
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);

        // ---

       // add(textArea.createCompatibleScrollPane()).growX().height(100).row();
       builder.add(scrollPane).width(400).row();


    }
    public static class ShortCutEinstellung{
        VisLabel name;
        VisTextField tastenkombeauswahl;
        VisRadioButton disablebutton;
        boolean disable=false;
        public ShortCutEinstellung(int i, VisTable table, final ShortCut shortCut){
            name=new VisLabel(shortCut.getName());
            tastenkombeauswahl=new VisTextField();
            disablebutton=new VisRadioButton("disable");

            tastenkombeauswahl.setReadOnly(true);
            disablebutton.setChecked(shortCut.isDisable());

            disablebutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    shortCut.setDisable(disablebutton.isChecked());
                }
            });

            table.add(name).expand().fill();
            table.add(tastenkombeauswahl);
            table.add(disablebutton).padLeft(10).row();
        }
    }
}


