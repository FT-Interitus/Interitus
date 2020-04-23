package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.input.check.Check;

public class RechtsKlick {
    public static Check check=new Check();

    public static int mouseoverblockindex;
public static void Rechtsklickupdate(){
    boolean mob=false;


    if(Gdx.input.isButtonPressed(1)){
        for(int i=0;i< BlockVar.visibleblocks.size();i++){

            if(check.isMouseover(BlockVar.visibleblocks.get(i).getX(),BlockVar.visibleblocks.get(i).getY(),BlockVar.visibleblocks.get(i).getW(),BlockVar.visibleblocks.get(i).getH())){
                mouseoverblockindex=i;
                mob=true;

            }
        }


        if(mob==false) {
            mouseoverblockindex = -1;
            ProgrammingSpace.popupmanager.getPopup(1).setShow(false);
            ProgrammingSpace.popupmanager.setPossiblepopup(0);
        }else{
            ProgrammingSpace.popupmanager.getPopup(0).setShow(false);
            ProgrammingSpace.popupmanager.setPossiblepopup(1);
        }

    }




}
}


