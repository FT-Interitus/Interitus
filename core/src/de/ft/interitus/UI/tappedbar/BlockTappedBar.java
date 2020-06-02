package de.ft.interitus.UI.tappedbar;

import de.ft.interitus.Block.TapBarBlockItem;
import de.ft.interitus.Var;
import de.ft.interitus.data.user.DataLoader;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.Wait;
import org.lwjgl.Sys;

public class BlockTappedBar {
    public static TappedBar tb= new TappedBar(100,100);

    public static void init() {


        TapBarBlockItem tbbi=new TapBarBlockItem(new Wait(),AssetLoader.img_mappe2);

        TapContent ActionBlocks=new TapContent(AssetLoader.img_mappe1);
        TapContent Programm_Sequence=new TapContent(AssetLoader.img_mappe2);
        TapContent Sensors=new TapContent(AssetLoader.img_mappe3);
        TapContent Data_Operation=new TapContent(AssetLoader.img_mappe4);
        TapContent Specials=new TapContent(AssetLoader.img_mappe5);
        TapContent OwnBlocks=new TapContent(AssetLoader.img_mappe6);


for(int i=0;i<Var.actProjekt.getProjectblocks().size();i++) {
    switch (Var.actProjekt.getProjectblocks().get(i).getBlockCategoration()) {
        case ActionBlocks:
            ActionBlocks.addItem(new TapBarBlockItem(Var.actProjekt.getProjectblocks().get(i),Var.actProjekt.getProjectblocks().get(i).getSmallImage()));
            break;
        case Programm_Sequence:
            Programm_Sequence.addItem(new TapBarBlockItem(Var.actProjekt.getProjectblocks().get(i),Var.actProjekt.getProjectblocks().get(i).getSmallImage()));
            break;
        case Sensors:
            Sensors.addItem(new TapBarBlockItem(Var.actProjekt.getProjectblocks().get(i),Var.actProjekt.getProjectblocks().get(i).getSmallImage()));
            break;
        case Data_Operation:
            Data_Operation.addItem(new TapBarBlockItem(Var.actProjekt.getProjectblocks().get(i),Var.actProjekt.getProjectblocks().get(i).getSmallImage()));
            break;
        case Specials:
            Specials.addItem(new TapBarBlockItem(Var.actProjekt.getProjectblocks().get(i),Var.actProjekt.getProjectblocks().get(i).getSmallImage()));
        case OwnBlocks:
            System.out.println("Unallowed Block was registered!");
            break;

    }
}


        tb.setContent(ActionBlocks,Programm_Sequence,Sensors,Data_Operation,Specials,OwnBlocks);
    }
}
