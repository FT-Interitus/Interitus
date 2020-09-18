/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection.usb;

import de.ft.interitus.deviceconnection.ev3connection.ConnectionHandle;
import de.ft.interitus.deviceconnection.ev3connection.Device;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoFunktions;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.EV3Funktions;
import de.ft.interitus.projecttypes.ProjectManager;
import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;

public class USBConnectionHandle implements ConnectionHandle {
   public static HidServices hidServices = HidManager.getHidServices();

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

                readed = legodevice.read(6);

            }catch (Exception e) {

            }


        } else {
            System.err.println(legodevice.getLastErrorMessage());
        }


        return readed;
    }

    @Override
    public void update() {


        //TODO add New Devices to ArrayList!!! 19.09.2020


        for(Device device:((EV3Funktions) ProjectManager.getActProjectVar().projectType.getProjectFunktions()).ev3devices) {
            if(!(device instanceof USBDevice)) {
                continue;
            }




        }



    }


}
