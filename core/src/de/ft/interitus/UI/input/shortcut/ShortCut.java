package de.ft.interitus.UI.input.shortcut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.ft.interitus.Programm;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.input.check.InputManager;

import java.util.ArrayList;

public class ShortCut {
    boolean disable=false;
    ArrayList<Integer>kombination=new ArrayList<>();
    public ShortCut(int... kombination) {
        for(int i=0;i<kombination.length;i++){
             this.kombination.add(kombination[i]);
        }



    }


    public void setShortCut(int... kombination) {
        for(int i=0;i<kombination.length;i++){
            this.kombination.add(kombination[i]);
        }
    }


    public boolean isPressed(){
        boolean pressed=true;
if(!disable) {


for(int i=0;i<kombination.size();i++) {
    if(kombination.get(i)<600) {
        if (ProgrammingSpace.pressedKeys.getPressedkeys().indexOf(kombination.get(i)) == -1) {
            pressed = false;
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
