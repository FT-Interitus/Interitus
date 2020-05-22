package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.UI.shortcut.SpecialKeys;
import de.ft.interitus.Var;

import java.util.ArrayList;

public class subitem6 {
    public static int shortcut_margin = 25;
    public static ArrayList<ShortCutEinstellung>shortCutEinstellungen=new ArrayList<>();
    public static void add(VisTable builder) {



        Var.disableshortcuts = true;
        VisTable table = new VisTable();
        for(int i=0;i< CheckShortcuts.shortCuts.size();i++) {

            if(CheckShortcuts.shortCuts.get(i).getMenuItem()!=null){
                shortCutEinstellungen.add(new ShortCutEinstellung(i, table, CheckShortcuts.shortCuts.get(i),CheckShortcuts.shortCuts.get(i).getMenuItem()));
            }else {
                shortCutEinstellungen.add(new ShortCutEinstellung(i, table, CheckShortcuts.shortCuts.get(i)));
            }


        }

        VisScrollPane scrollPane = new VisScrollPane(table);
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);

        // ---

       // add(textArea.createCompatibleScrollPane()).growX().height(100).row();
       builder.add(scrollPane).width(440).row();


    }
    public static class ShortCutEinstellung{
        VisLabel name;
        VisTextField tastenkombeauswahl;
        VisRadioButton disablebutton;
        MenuItem menuItem;
        public ShortCutEinstellung(int i, VisTable table, final ShortCut shortCut){
            name=new VisLabel(shortCut.getName());
            tastenkombeauswahl=new VisTextField();
            disablebutton=new VisRadioButton("Disable");

            tastenkombeauswahl.setReadOnly(true);
            disablebutton.setChecked(shortCut.isDisable());
            tastenkombeauswahl.setText(shortCut.getShortcutasString());

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
                        if(keycode==Input.Keys.CONTROL_LEFT || keycode==Input.Keys.CONTROL_RIGHT){
                            shortCut.addTaste(SpecialKeys.dualStrg);
                        }else if(keycode==Input.Keys.SHIFT_LEFT || keycode==Input.Keys.SHIFT_RIGHT){
                            shortCut.addTaste(SpecialKeys.dualShift);

                        }else {
                            shortCut.addTaste(keycode);
                        }
                    }
                    if(keycode==Input.Keys.DEL){
                        shortCut.delLast();
                    }

                    tastenkombeauswahl.setText(shortCut.getShortcutasString());

                    return super.keyDown(event, keycode);
                }
            });



            if(i==0) {
                table.add(name).expand().fill().padTop(10);
                table.add(tastenkombeauswahl).width(200).padTop(10);
                table.add(disablebutton).padLeft(10).padTop(10).row();
            }else {
                table.add(name).expand().fill().padTop(shortcut_margin);
                table.add(tastenkombeauswahl).width(200).padTop(shortcut_margin);
                table.add(disablebutton).padLeft(10).padTop(shortcut_margin).row();
            }


        }
        public ShortCutEinstellung(int i, VisTable table, final ShortCut shortCut, final MenuItem menuItem){
            this.menuItem=menuItem;
            name=new VisLabel(shortCut.getName());
            tastenkombeauswahl=new VisTextField();
            disablebutton=new VisRadioButton("Disable");

            tastenkombeauswahl.setReadOnly(true);
            disablebutton.setChecked(shortCut.isDisable());
            tastenkombeauswahl.setText(shortCut.getShortcutasString());

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
               if(keycode==Input.Keys.CONTROL_LEFT || keycode==Input.Keys.CONTROL_RIGHT){
                    shortCut.addTaste(SpecialKeys.dualStrg);
               }else if(keycode==Input.Keys.SHIFT_LEFT || keycode==Input.Keys.SHIFT_RIGHT){
                   shortCut.addTaste(SpecialKeys.dualShift);

               }else {
                   shortCut.addTaste(keycode);
               }
           }
           if(keycode==Input.Keys.DEL){
                shortCut.delLast();
           }

           tastenkombeauswahl.setText(shortCut.getShortcutasString());
           menuItem.setShortcut(shortCut.getShortcutasString().replace(" ",""));

           return super.keyDown(event, keycode);
       }
   });



if(i==0) {
    table.add(name).expand().fill().padTop(10);
    table.add(tastenkombeauswahl).width(200).padTop(10);
    table.add(disablebutton).padLeft(10).padTop(10).row();
}else {
    table.add(name).expand().fill().padTop(shortcut_margin);
    table.add(tastenkombeauswahl).width(200).padTop(shortcut_margin);
    table.add(disablebutton).padLeft(10).padTop(shortcut_margin).row();
}


        }



        ////////




    }
}


