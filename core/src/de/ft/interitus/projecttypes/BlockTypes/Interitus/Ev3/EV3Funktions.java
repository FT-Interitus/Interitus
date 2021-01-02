/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.dropdownmenue.DropDownElement;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.deviceconnection.ev3connection.*;
import de.ft.interitus.deviceconnection.ev3connection.usb.USBConnectionHandle;
import de.ft.interitus.deviceconnection.ev3connection.usb.USBDevice;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectFunktions;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.Tool;
import de.ft.interitus.utils.ArrayList;
import org.json.JSONObject;

import java.util.UUID;

public class EV3Funktions implements ProjectFunktions {
    public com.badlogic.gdx.utils.DelayedRemovalArray<Device> ev3devices = new com.badlogic.gdx.utils.DelayedRemovalArray<>();
    public ConnectionHandle usbConnectionHandle = new USBConnectionHandle();
    private int counter = 0;
    private boolean displayedError = false;

    @Override
    public void create() {

        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0, UIVar.abstandvonRand + 20, 630, ProjectManager.getActProjectVar().projectType.getProjectblocks().get(0).getWidth(), UIVar.BlockHeight, ProjectManager.getActProjectVar().projectType.getProjectblocks().get(0), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator(), false));

    }

    public void removeEv3(Device device) {

        if (device instanceof USBDevice && ((USBDevice) device).getDevice().isOpen()) {
            try {
                ((USBDevice) device).getDevice().close();
            }catch (Exception e) {

            }

        }

        for (int i = 0; i < UI.runselection.getElements().size(); i++) {


            if (UI.runselection.getElements().get(i).getIdentifier().equals(device)) {
                UI.runselection.getElements().remove(UI.runselection.getElements().get(i));
                break;

            }

        }

    }

    public void addEv3(Device device) {
        if (device instanceof USBDevice && !((USBDevice) device).getDevice().isOpen()) {


            if (!((USBDevice) device).getDevice().open()) {

                if (!displayedError)
                    Dialogs.showErrorDialog(UI.stage, "\nMit dem EV3-Stein kann nicht kommuniziert werden!\nFolgende Dinge kannst du jetzt unternehmen:\n    -Die USB-Verbindung trennen und neu verbinden\n    -Interitus und/oder den EV3 Stein neustarten\n    -Andere EV3 Anwendungen schließen\n    -Den PC neustarten\n");

                displayedError = true;
                ev3devices.removeValue(device, false);
                return;
            }
            displayedError = false;


        }
        String name = device.getName();
        Program.logger.config(name);
        UI.runselection.addelement(new DropDownElement(AssetLoader.DigitalWrite_description_image, name, device));

        ArrayList<Byte> connected = new ArrayList<>();
        connected.addAll(Operations.playSound("../tools/Bluetooth/Connect", 100, false));
        device.getConnectionHandle().sendData(ev3.makeDirectCmd(connected, 0, 0), device);

        registernewEv3(device);


    }


    @Override
    public void update() {
        counter++;
        usbConnectionHandle.update();


        if (UI.runselection.getSelectedElement() == null && UI.runselection.getElements().size() > 0) {

            UI.runselection.setSelectedElement(UI.runselection.getElements().get(0));

        } else {

            if (UI.runselection.getElements().size() == 0) {

                UI.runselection.setDefaultText("Bitte Gerät verbinden");
                UI.runselection.setSelectedElement(null);

            }

        }


        if (counter >= 6) {

            counter = 0;

            if (UI.runselection.getSelectedElement() != null) {
                UI.runselection.getSelectedElement().setText(((USBDevice) UI.runselection.getSelectedElement().getIdentifier()).getName());

            }


        }


    }

    @Override
    public void switchedTo() {
        for (Device device : ev3devices) {

            if (device instanceof USBDevice) {
                ((USBDevice) device).getDevice().close();
            }

        }
        ev3devices.clear();
    }

    @Override
    public void runconfigsettings(VisTable builder, DeviceConfiguration configuration) {

    }

    @Override
    public void projectsettings(VisTable builder, Object settings) {

    }


    @Override
    public void changedrunconfig() {

    }

    @Override
    public boolean isblockconnected(Block block) {
        return true;
    }

    @Override
    public boolean isVariableAvailable(String name) {
        return false;
    }

    @Override
    public ArrayList<Tool> getProjectTools() {
        return null;
    }

    private void registernewEv3(Device device) {
        try {
            boolean existsfolder = false;
            for (String string : Ev3SystemUtils.listedfilestoStrings(Ev3SystemUtils.ListFilesinPath("/home/root/lms2012/apps/IR Control/", device))) {
                if (string.contentEquals("Interitus/")) {
                    existsfolder = true;
                    break;
                }
            }

            if (!existsfolder) {

                try {
                    Ev3SystemUtils.create_Dir("/home/root/lms2012/apps/IR Control/Interitus/", device);
                } catch (Exception e) {

                }

            }


            boolean existsUUID = false;
            boolean existsSettings = false;
            try {
                for (String string : Ev3SystemUtils.listedfilestoStrings(Ev3SystemUtils.ListFilesinPath("/home/root/lms2012/apps/IR Control/Interitus/", device))) {
                    if (string.contentEquals("uuid")) {

                        existsUUID = true;

                    }
                    if (string.contentEquals("settings")) {

                        existsSettings = true;

                    }

                }
            }catch (Exception e) {

            }


            if (!existsUUID) {
                try {
                    Ev3SystemUtils.downloadFile("/home/root/lms2012/apps/IR Control/Interitus/uuid", UUID.randomUUID().toString(), device);
                } catch (Exception e) {

                }
            }

            if (!existsSettings) {
                Ev3SystemUtils.downloadFile("/home/root/lms2012/apps/IR Control/Interitus/settings", new JSONObject().toString(), device);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
