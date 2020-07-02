package de.ft.interitus.desktop;

import de.ft.interitus.Programm;
import de.ft.interitus.Var;

import java.awt.*;
import java.io.*;

public class LoggingSystem {
    public static void RedirectLog() { //TODO doenst work with the new log system
        try {

            if(Var.programmarguments.get(Var.programmarguments.indexOf("-do")+1).contains("-")) {
                throw new AfterIndexIsAnOtherArguementException();

            }
            String output =  Var.programmarguments.get(Var.programmarguments.indexOf("-do")+1);
            File file = new File(output);
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                PrintWriter writer = new PrintWriter(output);
                writer.print("");
                writer.close();
            }

            PrintStream fileOut = new PrintStream(output);
            System.setOut(fileOut);
        }catch (IndexOutOfBoundsException | FileNotFoundException | AfterIndexIsAnOtherArguementException e) {

            String output = "LOG_"+System.currentTimeMillis();
            Programm.logger.warning("Using default Output File");
            File file = new File(output);
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException b) {
                    b.printStackTrace();
                }
            }else{
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(output);
                } catch (FileNotFoundException fileNotFoundException) {

                }
                writer.print("");
                writer.close();
            }

            PrintStream fileOut = null;
            try {
                fileOut = new PrintStream(output);
            } catch (FileNotFoundException fileNotFoundException) {

            }
            System.setOut(fileOut);
        }
    }

    private static class AfterIndexIsAnOtherArguementException extends Throwable {
    }
}
