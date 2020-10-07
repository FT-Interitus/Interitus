/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.arduino;

import com.fazecast.jSerialComm.SerialPort;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UI;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;

public class SerialCommunication {
    public static SerialPort serialPort;
    public static String getActualPort(){
        if(( UI.runselection.getSelectedElement()!=null)) {
            return ((ArrayList<String>) UI.runselection.getSelectedElement().getIdentifier()).get(1);
        }else{
            return null;
        }
    }
    public static void connect(){
        int port=0;
        if(getActualPort()!=null) {
            for (int i = 0; i < SerialPort.getCommPorts().length; i++) {
                if (SerialPort.getCommPorts()[i].getSystemPortName().contentEquals(getActualPort())) {
                    port = i;
                    break;
                }
            }
            serialPort = SerialPort.getCommPorts()[port];
            Program.logger.config(String.valueOf(port));
            serialPort.openPort();
        }
    }
    public static void disconnect(){
        serialPort.closePort();
    }
    public static void methode() {

        Program.logger.config(((ArrayList<String>) UI.runselection.getSelectedElement().getIdentifier()).get(1));
    }
}
