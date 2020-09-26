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
    private final ArrayList<Character> chararray = new ArrayList<>();

    private final HidDevice device;
    private final ConnectionHandle connectionHandle;
    private final String path;

    public USBDevice(HidDevice device, ConnectionHandle connectionHandle, String path) {
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

            Byte[] data = connectionHandle.sendData(ev3.makeDirectCmd(Operations.getBrickname(), 4, 20), this);
            chararray.clear();
            for (int i = 5; i < (int) data[0]; i++) {

                if (data[i].byteValue() == (byte) 0x00) {
                    break;
                }

                chararray.add(((char) data[i].byteValue()));


            }
            ev3.printHex("recv", data);

            char[] array = new char[chararray.size()];

            for (int i = 0; i < array.length; i++) {


                array[i] = chararray.get(i);

            }

            return new String(array);


        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        return "-1";
    }
}
