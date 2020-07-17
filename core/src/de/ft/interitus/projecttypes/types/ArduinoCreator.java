package de.ft.interitus.projecttypes.types;

import com.badlogic.gdx.Gdx;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.projecttypes.ProjectManager;

public class ArduinoCreator implements ProjectCreator{

    @Override
    public void create() {

        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0,UIVar.abstandvonRand+20, UIVar.programmflaeche_h+UIVar.untenhohe-70- UIVar.buttonbarzeile_h- 20,150,70,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(0),ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(),ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));
        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0,UIVar.abstandvonRand+20, UIVar.programmflaeche_h+UIVar.untenhohe-70- UIVar.buttonbarzeile_h- 250,150,70,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(1),ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(),ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));

    }
}
