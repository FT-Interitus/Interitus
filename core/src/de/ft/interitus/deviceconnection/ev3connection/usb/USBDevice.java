/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection.usb;

import de.ft.interitus.deviceconnection.ev3connection.ConnectionHandle;
import de.ft.interitus.deviceconnection.ev3connection.Device;
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
}
