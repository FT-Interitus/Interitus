/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Logging;

import de.ft.interitus.Program;
import de.ft.interitus.Var;

public class DebugPrinter {
    public static void detect() {
        Program.logger.config("OS: " + System.getProperty("os.name"));
        Program.logger.config("OS VERSION: " + System.getProperty("os.version"));
        Program.logger.config("Running Java Version " + System.getProperty("java.version"));
        Program.logger.config("Processor Cores: " + Runtime.getRuntime().availableProcessors());
        Program.logger.config("Processor Architecture: " + System.getProperty("os.arch"));
        if (Var.debug) {
            Program.logger.finest("");
        }

    }

}
