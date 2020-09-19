/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection.usb;

import de.ft.interitus.deviceconnection.ev3connection.ConnectionHandle;
import de.ft.interitus.deviceconnection.ev3connection.Device;
import de.ft.interitus.deviceconnection.ev3connection.Operations;
import de.ft.interitus.deviceconnection.ev3connection.ev3;
import de.ft.interitus.utils.ArrayList;
import org.hid4java.HidDevice;

public class USBDevice implements Device {

     private final HidDevice device;
     private final ConnectionHandle connectionHandle;
     private final String path;

    public USBDevice(HidDevice device,ConnectionHandle connectionHandle,String path) {
        this.device = device;
        this.connectionHandle = connectionHandle;
        this.path = path;
    }


    public HidDevice getDevice() {
        return device;
    }


    @Override
    public ConnectionHandle getConnectionHandle() {
        return connectionHandle;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public String getName() {
try {

    Byte[] data = connectionHandle.sendData(ev3.makeDirectCmd(Operations.getBrickname(), 1, 1), this);

    ev3.printHex("recv", data);
}catch (Exception ignored){
    ignored.printStackTrace();
}

        return null;
    }
}
