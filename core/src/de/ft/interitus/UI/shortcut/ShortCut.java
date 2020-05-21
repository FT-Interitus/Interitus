package de.ft.interitus.UI.shortcut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.ft.interitus.ProgrammingSpace;

import java.util.ArrayList;

public class ShortCut {
    String name=null;
    boolean disable=false;
    ArrayList<Integer>kombination=new ArrayList<>();
    public ShortCut(int... kombination) {
        for(int i=0;i<kombination.length;i++){
             this.kombination.add(kombination[i]);
        }
    }
    public ShortCut(String name,int... kombination) {
        this.name=name;
        for(int i=0;i<kombination.length;i++){
            this.kombination.add(kombination[i]);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortCut(int... kombination) {
        for(int i=0;i<kombination.length;i++){
            this.kombination.add(kombination[i]);
        }
    }
    public void addTaste(int keycode){
        this.kombination.add(keycode);
    }
    public void delLast(){
        this.kombination.remove(this.kombination.size()-1);
    }

    public ArrayList<Integer> getKombination() {
        return kombination;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isDisable() {
        return disable;
    }

    public boolean isPressed(){
        boolean pressed=true;
if(!disable) {

for(int i=0;i<kombination.size();i++) {
    if(kombination.get(i)<600) {
        if (ProgrammingSpace.pressedKeys.getPressedkeys().indexOf(kombination.get(i)) == -1) {
            pressed = false;
        }
    }else{
        switch(kombination.get(i)){
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
if(kombination.size()!=ProgrammingSpace.pressedKeys.getPressedkeys().size()){
    pressed=false;
}

}else{
    pressed=false;
}

        return pressed;
    }

}
