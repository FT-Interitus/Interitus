/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.tools.arduinotools;

import de.ft.interitus.deviceconnection.arduino.SerialCommunication;
import de.ft.interitus.projecttypes.Tool;

import javax.swing.*;

public class SerialMonitor implements Tool {
    @Override
    public void open() {
        SerialCommunication.connect();
        // try {
        // UIManager.setLookAndFeel(new DarculaLaf());
        // } catch (UnsupportedLookAndFeelException e) {
        //     e.printStackTrace();
        // }
        JFrame jFrame = new SerialMonitorJFrame("Serial Monitor");
        jFrame.setSize(800, 600);
        jFrame.setVisible(true);
    }

    @Override
    public void close() {
        SerialCommunication.disconnect();
    }

    @Override
    public String getName() {
        return null;
    }
}
