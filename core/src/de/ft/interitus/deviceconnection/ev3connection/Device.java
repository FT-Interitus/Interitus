/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

public interface Device {

    ConnectionHandle getConnectionHandle();
    String path();
    String getName();



}
