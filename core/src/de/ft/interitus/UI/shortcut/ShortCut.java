package de.ft.interitus.UI.shortcut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.kotcrab.vis.ui.widget.MenuItem;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Var;

import java.util.ArrayList;

public class ShortCut {
    String name=null;
    boolean disable=false;
    MenuItem menuItem;
    ArrayList<Integer> combination =new ArrayList<>();

    public ShortCut(String name,int... combination) {
        this.name=name;
        for(int i = 0; i< combination.length; i++){
            this.combination.add(combination[i]);
        }
    }

    public ShortCut(String name,MenuItem menuItem,int... combination) {
        this.menuItem=menuItem;
        this.name=name;
        for(int i = 0; i< combination.length; i++){
            this.combination.add(combination[i]);
        }
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String getShortcutasString(){
        String ausgabe="";

        for(int i = 0; i<this.getCombination().size(); i++) {

            if(this.getCombination().get(i)<600) {
                ausgabe=ausgabe + Input.Keys.toString(this.getCombination().get(i)).replace("L-Alt","Alt").replace("R-Alt","Alt Gr").replace("Forward Delete","Entf");
            }else{
                switch (this.getCombination().get(i)){
                    case 600:
                        ausgabe=ausgabe + "Strg";
                        break;
                    case 601:
                        ausgabe=ausgabe + "Shift";
                        break;
                }
            }

            if(i!=this.getCombination().size()-1){
                ausgabe=ausgabe+" + ";
            }


        }
        return ausgabe;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void menubaraktualisieren(){
        if(menuItem!=null && !disable){
            menuItem.setShortcut(this.getShortcutasString().replace(" ",""));
        }
    }
    public void setShortCut(int... combination) {
        this.combination.clear();
        for(int i=0;i<combination.length;i++){
            this.combination.add(combination[i]);
        }

        menubaraktualisieren();
    }
    public void addTaste(int keycode){
        if(this.combination.indexOf(keycode)==-1) {
            this.combination.add(keycode);
        }
        menubaraktualisieren();
    }
    public void delLast(){

        if(this.combination.size()>0) {
            this.combination.remove(this.combination.size() - 1);
        }
        menubaraktualisieren();

    }

    public ArrayList<Integer> getCombination() {
        return combination;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
        System.out.println(menuItem);

        if(menuItem!=null){
            System.out.println("shortcut text fweg");

            if(disable) {
                menuItem.setShortcut("");
            }else{
                menuItem.setShortcut(getShortcutasString());

            }
        }

    }

    public boolean isDisable() {
        return disable;
    }

    public boolean isPressed() {
        boolean pressed = true;

if(combination.size()==0) {
    return false;
}

        if(!Var.disableshortcuts) {

        if (!disable) {

            for (int i = 0; i < combination.size(); i++) {
                if (combination.get(i) < 600) {
                    if (ProgrammingSpace.pressedKeys.getPressedkeys().indexOf(combination.get(i)) == -1) {
                        pressed = false;
                    }
                } else {
                    switch (combination.get(i)) {
                        case 600:
                            if (!Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
                                pressed = false;
                            }
                            break;
                        case 601:
                            if (!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                                pressed = false;
                            }
                            break;
                    }
                }
            }
            if (combination.size() != ProgrammingSpace.pressedKeys.getPressedkeys().size()) {
                pressed = false;
            }

        } else {
            pressed = false;
        }

    }else {

        pressed = false;
    }

        return pressed;
    }

}
