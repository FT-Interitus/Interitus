/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection.Exception;

public class Ev3ErrorAnalyser {
    public static void analyze(byte returns) throws Exception {
        if(returns==(byte)0x00) {
            return;
        }

        if(returns==(byte)0x01) {

            throw new Ev3UnknownHandleException("Handle Error");
        }


        if(returns==(byte)0x02) {

            throw new Ev3HandleNotReadyException("The Ev3 Handle wasn't available yet");
        }

        if(returns==(byte)0x03) {

            throw new Ev3CorruptFileException("The File you are looking for is Corrupt");
        }

        if(returns==(byte)0x04) {

            throw new Ev3NoHandlesAvailableException("All Handles are in use (Did you forgot to close Handle?)");
        }


        if(returns==(byte)0x05) {

            throw new Ev3NoPermissionException("You don't have the Permission for the File or Folder or the Folder already exist");
        }

        if(returns==(byte)0x06) {

            throw new Ev3IllegalPathException("Your Path isn't correct");
        }

        if(returns==(byte)0x07) {

            throw new Ev3FileExitsException("The File you want to Create already exits");
        }

        if(returns==(byte)0x08) {

            throw new Ev3EndOfFileException("The File you want to read already reached the end");
        }
        if(returns==(byte)0x09) {

            throw new Ev3SizeErrorException("The given Size wasn't correctly");
        }

        if(returns==(byte)0x0A) {

            throw new Ev3UnknownHandleException("An unknown Error Occurred");
        }

        if(returns==(byte)0x0B) {

            throw new Ev3IllegalFilenameException("The given File Name isn't legal");
        }
        if(returns==(byte)0x0C) {

            throw new Ev3IllegalFilenameException("Reset your Connection to the Brick!");
        }

    }
}
