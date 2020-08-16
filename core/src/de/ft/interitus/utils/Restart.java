/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import java.io.IOException;

public class Restart {

    protected static void restart() {

        String java = "";
        if (OSChecker.isWindows()) {

            java = System.getProperty("java.home") + "/bin/java.exe";

        } else if (OSChecker.isUnix()) {
            java = System.getProperty("java.home") + "/bin/java";

        } else if (OSChecker.isMac()) {

            java = System.getProperty("java.home") + "/bin/java";

        }


        try {
            Process proc = Runtime.getRuntime().exec(java + " -jar updater.jar -u -r");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);


    }
}
