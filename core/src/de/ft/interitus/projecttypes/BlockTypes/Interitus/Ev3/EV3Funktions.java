/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3;

import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.dropdownmenue.DropDownElement;
import de.ft.interitus.deviceconnection.ev3connection.*;
import de.ft.interitus.deviceconnection.ev3connection.usb.USBConnectionHandle;
import de.ft.interitus.deviceconnection.ev3connection.usb.USBDevice;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectFunktions;

public class EV3Funktions implements ProjectFunktions {
    public com.badlogic.gdx.utils.DelayedRemovalArray<Device> ev3devices = new com.badlogic.gdx.utils.DelayedRemovalArray<>();
    public ConnectionHandle usbConnectionHandle = new USBConnectionHandle();
    private int counter =8;

    @Override
    public void create() {

    }

   public void removeEv3(Device device) {

        for (int i = 0; i < UI.runselection.getElements().size(); i++) {


            if (((Device) UI.runselection.getElements().get(i).getIdentifier()).equals(device)) {
                UI.runselection.getElements().remove(UI.runselection.getElements().get(i));
                break;

            }

        }

    }

    public void addEv3(Device device) {
UI.runselection.addelement(new DropDownElement(AssetLoader.DigitalWrite_description_image,"EV3",device));


    }

    @Override
    public void update() {
        counter++;
        usbConnectionHandle.update();

        if(UI.runselection.getSelectedElement()!=null) {
            if (UI.runselection.getSelectedElement().getIdentifier() instanceof USBDevice) {
                if (!((USBDevice) UI.runselection.getSelectedElement().getIdentifier()).getDevice().isOpen()) {
                    ((USBDevice) UI.runselection.getSelectedElement().getIdentifier()).getDevice().open();


                }
            }

            for (Device device : ev3devices) {

                if (device instanceof USBDevice) {

                    if (device != UI.runselection.getSelectedElement().getIdentifier()) {

                        if (((USBDevice) device).getDevice().isOpen()) {
                            ((USBDevice) device).getDevice().close();
                        }

                    }


                }

            }
        }


        if (UI.runselection.getSelectedElement() == null && UI.runselection.getElements().size()>0) {

            UI.runselection.setSelectedElement(UI.runselection.getElements().get(0));

        }else{

            if(UI.runselection.getElements().size()==0) {

                UI.runselection.setDefaultText("Bitte Gerät verbinden");
               UI.runselection.setSelectedElement(null);

            }

        }


        if(counter>=10) {

            counter=0;

           if(UI.runselection.getSelectedElement()!=null) {
               ((USBDevice) UI.runselection.getSelectedElement().getIdentifier()).getName();
           }



        }


    }

    @Override
    public void switchedto() {
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
        return false;
    }

    @Override
    public boolean isVariableAvailable(String name) {
        return false;
    }


}
