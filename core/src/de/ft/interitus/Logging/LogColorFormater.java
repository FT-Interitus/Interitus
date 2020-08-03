/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Logging;

import de.ft.interitus.Var;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogColorFormater extends Formatter {
    // ANSI escape code
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_CRAY = "\u001B[37m";
    public static final String ANSI_WHITE = "\u001b[37;1m";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public String format(LogRecord record) {
        LocalDateTime now = LocalDateTime.now();
        StringBuilder builder = new StringBuilder();

        if (record.getLevel() == Level.INFO) {

            builder.append(ANSI_CRAY);

            builder.append("[INFO]    ");

            builder.append("[");
            builder.append(dtf.format(now));
            builder.append("] ");

            builder.append(record.getSourceClassName().split("\\.")[record.getSourceClassName().split("\\.").length - 1]);

            builder.append(": ");
            builder.append(ANSI_CRAY);
            builder.append(record.getMessage());
            if (!record.getMessage().endsWith("\n")) {
                builder.append("\n");
            }
        }

        if (record.getLevel() == Level.CONFIG) { //CONFIG == DEBUG
            if (Var.debug) {
                builder.append(ANSI_YELLOW);
                builder.append("[DEBUG]   ");
                builder.append(ANSI_CRAY);


                builder.append("[");
                builder.append(dtf.format(now));
                builder.append("] ");
                builder.append(record.getSourceClassName().split("\\.")[record.getSourceClassName().split("\\.").length - 1]);
                builder.append(": ");
                builder.append(ANSI_CRAY);
                builder.append(record.getMessage());
                builder.append("\n");
            }
        }

        if (record.getLevel() == Level.SEVERE) {

            builder.append(ANSI_RED);


            builder.append("[ERROR]   ");

            builder.append("[");
            builder.append(dtf.format(now));
            builder.append("] ");

            builder.append(record.getSourceClassName().split("\\.")[record.getSourceClassName().split("\\.").length - 1]);

            builder.append(": ");
            builder.append(ANSI_RED);
            builder.append(record.getMessage());
            builder.append("\n");
        }


        if (record.getLevel() == Level.WARNING) {

            builder.append(ANSI_YELLOW);

            builder.append("[WARNING] ");
            builder.append("[");
            builder.append(dtf.format(now));
            builder.append("] ");

            builder.append(record.getSourceClassName().split("\\.")[record.getSourceClassName().split("\\.").length - 1]);

            builder.append(": ");
            builder.append(ANSI_YELLOW);
            builder.append(record.getMessage());
            builder.append("\n");
        }

        if (record.getLevel() == Level.FINEST) {

            builder.append("\n");
        }


        return builder.toString();
    }


}
