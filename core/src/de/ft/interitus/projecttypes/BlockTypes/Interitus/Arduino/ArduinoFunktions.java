package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.UI.ManualConfig.DeviceParameter;
import de.ft.interitus.UI.ManualConfig.ManualConfigUI;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.dropdownmenue.DropDownElement;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.compiler.Arduino.ArduinoCompiler;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectFunktions;
import org.json.JSONArray;

import java.util.Arrays;
import java.util.Comparator;


public class ArduinoFunktions implements ProjectFunktions {
    private final VisTextField configurationname = new VisTextField();
    private final VisSelectBox<RunConfigSelectItem> selectboard = new VisSelectBox<>();
    private final VisSelectBox<RunConfigSelectItem> selectSerialPort = new VisSelectBox<>();
    private String savedidentifier = ""; //To know which is the Selected Board
    private boolean openedprogress = false;
    private DeviceConfiguration activeConfiguration;

    public ArduinoFunktions() {

        configurationname.setMaxLength(ManualConfigUI.MAX_NAME_LENGTH);

        configurationname.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (activeConfiguration != null) {
                    activeConfiguration.setName(((VisTextField) actor).getText());
                    if (configurationname.getText().length() == 0) {
                        configurationname.setColor(1, 0, 0, 1);
                    } else {
                        configurationname.setColor(1, 1, 1, 1);
                    }
                    activeConfiguration.updateEntry();
                }
            }
        });


        selectboard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                if(!openedprogress) {

                if (activeConfiguration != null) {
                    if (activeConfiguration.getParameters().size() > 0) {

                        activeConfiguration.getParameters().get(0).setParameter(selectboard.getSelected().getIdentifier());
                    } else {
                        activeConfiguration.getParameters().add(new DeviceParameter(selectboard.getSelected().getIdentifier()));

                    }


                }




                }


            }
        });

        selectSerialPort.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(!openedprogress) {


                    if (activeConfiguration != null) {
                        if (activeConfiguration.getParameters().size() > 1) {

                            activeConfiguration.getParameters().get(1).setParameter(selectSerialPort.getSelected().getIdentifier());
                        } else {
                            activeConfiguration.getParameters().add(new DeviceParameter(selectSerialPort.getSelected().getIdentifier()));
                        }
                    }
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


        JSONArray array = ((ArduinoCompiler) ProjectManager.getActProjectVar().projectType.getCompiler()).getBoards();
        if (array == null) {
            Dialogs.showErrorDialog(UI.stage, "Fehler beim Laden der Arduino Boards");
            return;
        }
        //Counts the connected Boards
        int counter = 0;
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

        array = null;

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void runconfigsettings(VisTable builder, DeviceConfiguration configuration) {

        selectboard.setDisabled(true);
        selectSerialPort.setDisabled(true);

        openedprogress = true;
        activeConfiguration = configuration;

        if(activeConfiguration.getParameters().size()<1) {
            activeConfiguration.getParameters().add(new DeviceParameter(""));
            activeConfiguration.getParameters().add(new DeviceParameter(""));
        }else if(activeConfiguration.getParameters().size()<2){
            activeConfiguration.getParameters().add(new DeviceParameter(""));
        }




        configurationname.setText(configuration.getName());

        if (configurationname.getText().length() == 0) {
            configurationname.setColor(1, 0, 0, 1);
        } else {
            configurationname.setColor(1, 1, 1, 1);
        }

        builder.add(new VisLabel("Name: ")).expandX().padTop(-(builder.getHeight() / 15 * 14));
        builder.add(configurationname).expandX().padTop(-(builder.getHeight() / 15 * 14)).padLeft(-70).row(); //TODO height dose'nt work

        builder.add(selectboard).expandX().padLeft(-100).row();
        builder.add(selectSerialPort).expandX().padLeft(-100).row();


        configuration.updateEntry();


        Thread thread = new Thread() {
            @Override
            public void run() {
                getInstalledBoards();
            }


        };
        thread.start();
    }

    @Override
    public void changedrunconfig() {
        openedprogress = false;
        activeConfiguration = null;

    }







    private void getInstalledBoards() {
        JSONArray deviceArray = ((ArduinoCompiler) ProjectManager.getActProjectVar().projectType.getCompiler()).getInstalledBoards();


        RunConfigSelectItem[] devices = new RunConfigSelectItem[deviceArray.length()];


        for (int i = 0; i < deviceArray.length(); i++) {



                devices[i] = new RunConfigSelectItem(deviceArray.getJSONObject(i).getString("name"), deviceArray.getJSONObject(i).getString("FQBN"));


        }

        Arrays.sort(devices, Comparator.comparing(RunConfigSelectItem::getContent));


        RunConfigSelectItem[] devices2 = new RunConfigSelectItem[devices.length+1];

        for(int i=0;i<devices2.length;i++) {

            if(i==devices.length) {

                devices2[i] = new RunConfigSelectItem("Arduino Nano Old Bootloader","arduino:avr:nano:cpu=atmega328old");

            }else{
                devices2[i] = devices[i];
            }

        }


        try {
            selectboard.clearItems();
        } catch (RuntimeException ignored) {

        }

        selectboard.setItems(devices2);

        if (openedprogress) {

            for (int i = 0; i < selectboard.getItems().size; i++) {

                if (activeConfiguration.getParameters().get(0).getParameter().toString().equals(selectboard.getItems().get(i).getIdentifier())) {
                    selectboard.setSelected(selectboard.getItems().get(i));
                }


            }


        }


        RunConfigSelectItem tempselected;

        //Get Serial Ports

         deviceArray = ((ArduinoCompiler) ProjectManager.getActProjectVar().projectType.getCompiler()).getBoards();


       devices = new RunConfigSelectItem[deviceArray.length()];

        for (int i = 0; i < deviceArray.length(); i++) {
            devices[i] = new RunConfigSelectItem(deviceArray.getJSONObject(i).getString("address"), deviceArray.getJSONObject(i).getString("address"));
        }
        try {
            selectSerialPort.clearItems();
        } catch (RuntimeException ignored) {


        }
        tempselected = selectSerialPort.getSelected();
        selectSerialPort.setItems(devices);
        selectSerialPort.setSelected(tempselected);

        if (openedprogress) {

            for (int i = 0; i < selectSerialPort.getItems().size; i++) {

                if (activeConfiguration.getParameters().get(1).getParameter().toString().equals(selectSerialPort.getItems().get(i).getIdentifier())) {
                    selectSerialPort.setSelected(selectSerialPort.getItems().get(i));
                }


            }


        }

        deviceArray = null;
        devices = null;

selectSerialPort.setDisabled(false);
selectboard.setDisabled(false);

        openedprogress = false;
    }

}
