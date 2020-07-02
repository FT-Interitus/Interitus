package de.ft.interitus.Logging;

import de.ft.interitus.Programm;
import de.ft.interitus.Var;

public class DebugPrinter {
    public static void detect() {
        Programm.logger.config("OS: " + System.getProperty("os.name"));
        Programm.logger.config("OS VERSION: " + System.getProperty("os.version"));
        Programm.logger.config("Running Java Version " + System.getProperty("java.version"));
        Programm.logger.config("Processor Cores: " + Runtime.getRuntime().availableProcessors());
        Programm.logger.config("Processor Architecture: " + System.getProperty("os.arch"));
        if (Var.debug) {
            Programm.logger.finest("");
        }

    }

}
