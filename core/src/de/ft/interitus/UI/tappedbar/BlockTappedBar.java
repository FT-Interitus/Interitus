package de.ft.interitus.UI.tappedbar;

import de.ft.interitus.Block.TapBarBlockItem;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.device.BlockTypes.Ev3.Sound;


public class BlockTappedBar {
    public static TappedBar tb= new TappedBar(100,100);

    public static void init() {

        TapBarBlockItem tbbi=new TapBarBlockItem(new Sound(),AssetLoader.img_mappe2);

        TapContent content1=new TapContent(AssetLoader.img_mappe1);
        TapContent content2=new TapContent(AssetLoader.img_mappe2);
        TapContent content3=new TapContent(AssetLoader.img_mappe3);
        TapContent content4=new TapContent(AssetLoader.img_mappe4);
        TapContent content5=new TapContent(AssetLoader.img_mappe5);
        TapContent content6=new TapContent(AssetLoader.img_mappe6);


        content1.setItems(tbbi,tbbi,tbbi,tbbi,tbbi,tbbi,tbbi);
        tb.setContent(content1,content2,content3,content4,content5,content6);
    }
}
