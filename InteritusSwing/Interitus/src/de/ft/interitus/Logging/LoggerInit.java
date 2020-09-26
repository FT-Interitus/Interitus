/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Logging;

import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Programm;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.programmdata.Data;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class LoggerInit {
    public static FileHandler fh = null;

    public static void init() {

        Programm.logger.setLevel(Level.ALL);

        Handler handler = new ConsoleHandler();
        if (!new File(System.getProperty("user.home") + "/" + Data.foldername + "/log").isDirectory()) {
            new File(System.getProperty("user.home") + "/" + Data.foldername + "/log").mkdir();
        }

        if (new File(System.getProperty("user.home") + "/" + Data.foldername + "/it.lock").exists()) {
            if (!Var.savemode) {
                if (!Var.disableprogrammnotclosedwarniung) {
                    DisplayErrors.errorStringwithoutException = "Das Programm wurde unerwartet beendet! \nError LOGs findest du hier: \n" + System.getProperty("user.home") + "/" + Data.foldername + "/log";
                }
            }
        } else {
            try {
                if (!new File(System.getProperty("user.home") + "/" + Data.foldername).exists()) {
                    new File(System.getProperty("user.home") + "/" + Data.foldername).mkdir();
                }
                new File(System.getProperty("user.home") + "/" + Data.foldername + "/it.lock").createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Var.lognamefile = System.currentTimeMillis() + "_LOG.log";
        Var.logname = System.getProperty("user.home") + "/" + Data.foldername + "/log/" + Var.lognamefile;

        if (!new File(System.getProperty("user.home") + "/" + Data.foldername + "/log/").exists()) {
            new File(System.getProperty("user.home") + "/" + Data.foldername + "/log/").mkdir();
        }

        if (!new File(Var.logname).exists()) {
            try {
                new File(Var.logname).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            fh = new FileHandler(Var.logname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.setLevel(Level.ALL);
        Programm.logger.setUseParentHandlers(false);
        handler.setFormatter(new LogColorFormater());
        Programm.logger.addHandler(handler);
        Programm.logger.addHandler(fh);


        System.setOut(new PrintStream(new LoggerOutputStream(Programm.logger, Level.INFO)));
        System.setErr(new PrintStream(new LoggerOutputStream(Programm.logger, Level.SEVERE)));
        Programm.logger.config("Logger init finished");

    }


}
