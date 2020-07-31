package de.ft.interitus.projecttypes.BlockTypes.Arduino;

import de.ft.interitus.Programm;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectFunktions;

public class ArduinoFunktions implements ProjectFunktions {

    @Override
    public void create() {

        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0,UIVar.abstandvonRand+20, UIVar.programmflaeche_h+UIVar.untenhohe-70- UIVar.buttonbarzeile_h- 20,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(0).getWidth(),UIVar.BlockHeight,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(0),ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(),ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));
        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0,UIVar.abstandvonRand+20, UIVar.programmflaeche_h+UIVar.untenhohe-70- UIVar.buttonbarzeile_h- 250,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(1).getWidth(),UIVar.BlockHeight,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(1),ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(),ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));

    }

    @Override
    public void update() {

    }
}
