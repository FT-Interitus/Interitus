package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.UI.ManualConfig.ManualConfigUI;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.dropdownmenue.DropDownElement;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.compiler.Arduino.ArduinoCompiler;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectFunktions;
import org.json.JSONArray;


public class ArduinoFunktions implements ProjectFunktions {
  private int counter = 0; //Counts the connected Boards
    private String savedidentifier = ""; //To know which is the Selected Board


    private VisTextField configurationname = new VisTextField();
    private DeviceConfiguration activeConfiguration;

    public ArduinoFunktions() {

        configurationname.setMaxLength(ManualConfigUI.MAX_NAME_LENGTH);

        configurationname.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(activeConfiguration!=null) {
                    activeConfiguration.setName(((VisTextField) actor).getText());
                    if(configurationname.getText().length()==0) {
                        configurationname.setColor(1, 0, 0, 1);
                    }else{
                        configurationname.setColor(1,1,1,1);
                    }
                    activeConfiguration.updateEntry();
                }
            }
        });
    }


    @Override
    public void create() {

        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0, UIVar.abstandvonRand + 20, UIVar.programmflaeche_h + UIVar.untenhohe - 70 - UIVar.buttonbarzeile_h - 20, ProjectManager.getActProjectVar().projectType.getProjectblocks().get(0).getWidth(), UIVar.BlockHeight, ProjectManager.getActProjectVar().projectType.getProjectblocks().get(0), ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));
        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0, UIVar.abstandvonRand + 20, UIVar.programmflaeche_h + UIVar.untenhohe - 70 - UIVar.buttonbarzeile_h - 250, ProjectManager.getActProjectVar().projectType.getProjectblocks().get(1).getWidth(), UIVar.BlockHeight, ProjectManager.getActProjectVar().projectType.getProjectblocks().get(1), ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));

    }

    @Override
    public void update() {


        JSONArray array = ArduinoCompiler.getBoards();
        if (array == null) {
            Dialogs.showErrorDialog(UI.stage,"Fehler beim Laden der Arduino Boards");
            return;
        }
        counter = 0;
        for (int i = 0; i < array.length(); i++) {
            if (array.getJSONObject(i).has("boards")) {
                counter++;


            }
        }


        if (counter != UI.runselection.getElements().size()) {
            if (UI.runselection.getSelectedElement() != null) {
                savedidentifier = ((String) UI.runselection.getSelectedElement().getIdentifier());
            }

            UI.runselection.clear();

            for (int i = 0; i < array.length(); i++) {
                if (array.getJSONObject(i).has("boards")) {

                    Texture image = switch (array.getJSONObject(i).getJSONArray("boards").getJSONObject(0).getString("FQBN")) {
                        case "arduino:avr:uno" -> AssetLoader.arduinounoimage;
                        case "arduino:avr:mega" -> AssetLoader.arduinomegaimage;
                        case "arduino:avr:nano" -> AssetLoader.arduinonanoimage;
                        default -> AssetLoader.connector_offerd; //TODO image questionmark
                    };


                    UI.runselection.addelement(new DropDownElement(image, array.getJSONObject(i).getJSONArray("boards").getJSONObject(0).getString("name"), array.getJSONObject(i).getString("address")));
                    if (array.getJSONObject(i).getString("address").contains(savedidentifier)) {
                        UI.runselection.setSelectedElement(UI.runselection.getElements().get(UI.runselection.getElements().size() - 1));
                    }
                }
            }
            if (UI.runselection.getSelectedElement() == null && UI.runselection.getElements().size() > 0) {
                UI.runselection.setSelectedElement(UI.runselection.getElements().get(0));
            }

            if (UI.runselection.getSelectedElement() == null) {
                UI.runselection.setDefaultText("Bitte Gerät verbinden");
            }

        }


    }



    @Override
    public void runconfigsettings(VisTable builder, DeviceConfiguration configuration) {



        activeConfiguration = configuration;
        configurationname.setText(configuration.getName());

        if(configurationname.getText().length()==0) {
            configurationname.setColor(1, 0, 0, 1);
        }else{
            configurationname.setColor(1,1,1,1);
        }

        builder.add(new VisLabel("Name: ")).expandX().padTop(-(builder.getHeight()/15*14));
        builder.add(configurationname).expandX().padTop(-(builder.getHeight()/15*14)).padLeft(-70).row();



        configuration.updateEntry();

    }

    @Override
    public void changedrunconfig() {
        activeConfiguration = null;
    }

}
