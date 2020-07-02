package de.ft.interitus.Logging;

import de.ft.interitus.Programm;

import java.io.PrintStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class LoggerInit {
   public static void init() {

Programm.logger.setLevel(Level.ALL);

       Handler handler = new ConsoleHandler();
       handler.setLevel(Level.ALL);
       Programm.logger.setUseParentHandlers(false);
       handler.setFormatter(new LogColorFormater());
       Programm.logger.addHandler(handler);


       System.setOut(new PrintStream(new LoggerOutputStream(Programm.logger, Level.INFO)));
       System.setErr(new PrintStream(new LoggerOutputStream(Programm.logger, Level.SEVERE)));
       System.out.println(" Logger inited");

   }
}
