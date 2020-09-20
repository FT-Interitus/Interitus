/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import java.math.BigInteger;
import java.util.ArrayList;

public class Utils {
    public static void downloadFile(String name,String content,Device device) {

        ArrayList<Byte> beginningbytes = SystemOperations.beginDownload(1000,name);

        byte[] bytes = new byte[beginningbytes.size()];

        for(int i=0;i<bytes.length;i++) {

            bytes[i] = beginningbytes.get(i);

        }


      Byte[] filehandel =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(bytes),device);

       ev3.printHex("recv",filehandel);

    }

}
