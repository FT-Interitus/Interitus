/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

public interface ConnectionHandle {
     Byte[] sendData(byte[] sendingbytes,Device device);
     void update();


}
