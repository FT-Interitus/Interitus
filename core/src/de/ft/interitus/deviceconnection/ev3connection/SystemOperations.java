/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

public class SystemOperations {

    private static final byte firwareupdate = (byte) 0xA0;

    public static byte[] firmwareUpdate() {

        byte[] bytearray = new byte[1];
        bytearray[0] =firwareupdate;

        return bytearray;

    }



}
