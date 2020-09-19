/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection.usb;

import de.ft.interitus.deviceconnection.ev3connection.ConnectionHandle;
import de.ft.interitus.deviceconnection.ev3connection.Device;
import de.ft.interitus.deviceconnection.ev3connection.ev3;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoFunktions;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.EV3Funktions;
import de.ft.interitus.projecttypes.ProjectManager;
import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;

import java.util.ArrayList;

public class USBConnectionHandle implements ConnectionHandle {
   public static HidServices hidServices = HidManager.getHidServices();
private static ArrayList<HidDevice> devices = new ArrayList<>();
private static ArrayList<HidDevice> removeinglist = new ArrayList<>();
    @Override
    public Byte[] sendData(byte[] sendingbytes, Device device) {
        HidDevice legodevice;
        if(device instanceof USBDevice) {
           legodevice = ((USBDevice) device).getDevice();
        }else{
            throw new IllegalArgumentException("Not Compatible Device");
        }



        Byte[] readed = null;
        int val = legodevice.write(sendingbytes, sendingbytes.length, (byte) 0);
        if (val != -1) {
            try {

                readed = legodevice.read();

            }catch (Exception e) {

            }


        } else {
            System.err.println(legodevice.getLastErrorMessage());
        }


        return readed;
    }

    @Override
    public void update() {




        for(HidDevice hiddevice:hidServices.getAttachedHidDevices()) {

            if(hiddevice.getProductId()!= ev3.ID_PRODUCT_EV3)
                continue;
            if(hiddevice.getVendorId()!=ev3.ID_VENDOR_LEGO)
                continue;

            boolean match = false;
            for(Device device:((EV3Funktions) ProjectManager.getActProjectVar().projectType.getProjectFunktions()).ev3devices) {

                if(hiddevice.getPath().contentEquals(((USBDevice) device).getDevice().getPath())) {
                    match = true;

                    break;
                }


            }
            if(!match) {
                Device device =new USBDevice(hiddevice, ((EV3Funktions) ProjectManager.getActProjectVar().projectType.getProjectFunktions()).usbConnectionHandle, hiddevice.getPath());
                ((EV3Funktions) ProjectManager.getActProjectVar().projectType.getProjectFunktions()).ev3devices.add(device);
                ((EV3Funktions) ProjectManager.getActProjectVar().projectType.getProjectFunktions()).addEv3(device);

            }

        }

        for(Device device:((EV3Funktions) ProjectManager.getActProjectVar().projectType.getProjectFunktions()).ev3devices) {
            if(!(device instanceof USBDevice)) {
                continue;
            }
            boolean match = false;


            for(HidDevice hiddevice:hidServices.getAttachedHidDevices()) {

                if(hiddevice.getPath().contentEquals(((USBDevice) device).getDevice().getPath())) {
                    match=true;
                }

            }

            if(!match) {

                ((EV3Funktions) ProjectManager.getActProjectVar().projectType.getProjectFunktions()).removeEv3(device);
                ((EV3Funktions) ProjectManager.getActProjectVar().projectType.getProjectFunktions()).ev3devices.removeValue(device,false);


            }


        }



    }


}
