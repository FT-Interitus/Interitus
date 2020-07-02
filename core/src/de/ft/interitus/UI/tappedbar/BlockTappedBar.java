package de.ft.interitus.UI.tappedbar;

import de.ft.interitus.Block.TapBarBlockItem;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectManager;


public class BlockTappedBar {
    public static TappedBar tb = new TappedBar(100, 100);

    static TapContent ActionBlocks = new TapContent(AssetLoader.img_mappe1);
    static TapContent Programm_Sequence = new TapContent(AssetLoader.img_mappe2);
    static TapContent Sensors = new TapContent(AssetLoader.img_mappe3);
    static TapContent Data_Operation = new TapContent(AssetLoader.img_mappe4);
    static TapContent Specials = new TapContent(AssetLoader.img_mappe5);
    static TapContent OwnBlocks = new TapContent(AssetLoader.img_mappe6);

    public static void init() {


        ActionBlocks.clear();
        Programm_Sequence.clear();
        Sensors.clear();
        Data_Operation.clear();
        Specials.clear();
        OwnBlocks.clear();


        for (int i = 0; i < ProjectManager.getActProjectVar().projectType.getProjectblocks().size(); i++) {
            try {
                switch (ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getBlockCategoration()) {
                    case ActionBlocks:
                        ActionBlocks.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                        break;
                    case Programm_Sequence:
                        Programm_Sequence.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                        break;
                    case Sensors:
                        Sensors.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                        break;
                    case Data_Operation:
                        Data_Operation.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                        break;
                    case Specials:
                        Specials.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                    case OwnBlocks:
                        System.out.println("Unallowed Block was registered!");
                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
                DisplayErrors.customErrorstring = "Fehler beim Laden der Blöcke!";
                DisplayErrors.error = e;
            }
        }


        tb.setContent(ActionBlocks, Programm_Sequence, Sensors, Data_Operation, Specials, OwnBlocks);
    }
}
