package de.ft.interitus.UI.shortcut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Var;

import java.util.ArrayList;

public class ShortCut {
    String name=null;
    boolean disable=false;
    ArrayList<Integer> combination =new ArrayList<>();
    public ShortCut(int... combination) {
        for(int i = 0; i< combination.length; i++){
             this.combination.add(combination[i]);
        }
    }
    public ShortCut(String name,int... combination) {
        this.name=name;
        for(int i = 0; i< combination.length; i++){
            this.combination.add(combination[i]);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortCut(int... combination) {
        for(int i=0;i<combination.length;i++){
            this.combination.add(combination[i]);
        }
    }
    public void addTaste(int keycode){
        this.combination.add(keycode);
    }
    public void delLast(){

        if(this.combination.size()>0) {
            this.combination.remove(this.combination.size() - 1);
        }
    }

    public ArrayList<Integer> getCombination() {
        return combination;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isDisable() {
        return disable;
    }

    public boolean isPressed() {
        boolean pressed = true;


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
