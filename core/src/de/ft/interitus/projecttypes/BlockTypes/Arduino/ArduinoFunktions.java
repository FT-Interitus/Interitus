package de.ft.interitus.projecttypes.BlockTypes.Arduino;

import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.dropdownmenue.DropDownElement;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.compiler.Arduino.ArduinoCompiler;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectFunktions;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;


public class ArduinoFunktions implements ProjectFunktions {
int counter = 0;
String savedidentifier = "";

    @Override
    public void create() {

        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0,UIVar.abstandvonRand+20, UIVar.programmflaeche_h+UIVar.untenhohe-70- UIVar.buttonbarzeile_h- 20,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(0).getWidth(),UIVar.BlockHeight,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(0),ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(),ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));
        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0,UIVar.abstandvonRand+20, UIVar.programmflaeche_h+UIVar.untenhohe-70- UIVar.buttonbarzeile_h- 250,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(1).getWidth(),UIVar.BlockHeight,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(1),ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(),ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));

    }

    @Override
    public void update() {




       JSONArray array = ArduinoCompiler.getBoards();
       if(array==null) {
           return;//TODO there was an error
       }
counter=0;
        for(int i=0;i<array.length();i++) {
            if(array.getJSONObject(i).has("boards")) {
counter++;


            }
        }




        if(counter!=UI.runselection.getElements().size()) {
            if(UI.runselection.getSelectedElement()!=null) {
                savedidentifier = ((String) UI.runselection.getSelectedElement().getIdentifier());
            }

            UI.runselection.clear();

            for(int i=0;i<array.length();i++) {
                if(array.getJSONObject(i).has("boards")) {

                    UI.runselection.addelement(new DropDownElement(AssetLoader.DigitalWrite_description_image,array.getJSONObject(i).getJSONArray("boards").getJSONObject(0).getString("name"),array.getJSONObject(i).getString("address")));
                    if(array.getJSONObject(i).getString("address").contains(savedidentifier)) {
                        UI.runselection.setSelectedElement(UI.runselection.getElements().get(UI.runselection.getElements().size()-1));
                    }
                }
            }
            if(UI.runselection.getSelectedElement()==null&&UI.runselection.getElements().size()>0) {
                UI.runselection.setSelectedElement(UI.runselection.getElements().get(0));
            }

            if(UI.runselection.getSelectedElement()==null) {
                UI.runselection.setDefaultText("Bitte Gerät verbinden");
            }

        }



    }

    @Override
    public void runconfigsettings(VisTable builder, DeviceConfiguration configuration) {


        configuration.setName("test");

        configuration.updateEntry();

    }

}
