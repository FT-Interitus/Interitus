package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.inputfields.check.InputManager;
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

        public ShortCutEinstellung(int i, VisTable table, final ShortCut shortCut){
            name=new VisLabel(shortCut.getName());
            tastenkombeauswahl=new VisTextField();
            disablebutton=new VisRadioButton("disable");

            tastenkombeauswahl.setReadOnly(true);
            disablebutton.setChecked(shortCut.isDisable());
            loadkombination(shortCut);

            disablebutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    shortCut.setDisable(disablebutton.isChecked());
                    tastenkombeauswahl.setDisabled(disablebutton.isChecked());
                }
            });

   tastenkombeauswahl.addListener(new InputListener() {
       @Override
       public boolean keyDown(InputEvent event, int keycode) {
           System.out.println(keycode);
           if(keycode != Input.Keys.DEL) {
               shortCut.addTaste(keycode);
           }
           if(keycode==Input.Keys.DEL){
                shortCut.delLast();
           }
           loadkombination(shortCut);

           return super.keyDown(event, keycode);
       }
   });




            table.add(name).expand().fill();
            table.add(tastenkombeauswahl).width(200);
            table.add(disablebutton).padLeft(10).row();
        }
        public void loadkombination(ShortCut shortCut){
            tastenkombeauswahl.clearText();
            for(int i=0;i<shortCut.getKombination().size();i++) {

                if(shortCut.getKombination().get(i)<600) {
                    tastenkombeauswahl.setText(tastenkombeauswahl.getText() + Input.Keys.toString(shortCut.getKombination().get(i)));
                }else{
                    switch (shortCut.getKombination().get(i)){
                        case 600:
                            tastenkombeauswahl.setText(tastenkombeauswahl.getText() + "dualStrg");
                            break;
                        case 601:
                            tastenkombeauswahl.setText(tastenkombeauswahl.getText() + "dualShift");
                            break;
                    }
                }

                if(i!=shortCut.getKombination().size()-1){
                    tastenkombeauswahl.setText(tastenkombeauswahl.getText()+" + ");
                }


            }
        }
    }
}


