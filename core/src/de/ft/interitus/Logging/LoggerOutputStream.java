/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Logging;

import de.ft.interitus.Program;
import de.ft.interitus.utils.ArrayList;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerOutputStream extends OutputStream {
    public Logger logger;
    public ArrayList<Byte> bytes = new ArrayList<>();
    Level level;
    private String mem = "";

    public LoggerOutputStream(Logger log, Level level) {
        super();
        this.logger = log;
        this.level = level;

    }

    @Override
    public void write(int b) {

        byte[] bytes = new byte[1];
        bytes[0] = (byte) (b & 0xff);
        mem = mem + new String(bytes);

        if (mem.endsWith("\n")) {
            mem = mem.substring(0, mem.length() - 1);
            flush();
        }


    }

    /**
     * Flushes the output stream.
     * Disabled Debug flushing because you should use logger in Program.logger
     */

    @Override
    public void flush() {




        if (level == Level.INFO) {


            //logger.log(level, mem + "\n");
            mem = "";


        } else {
            logger.log(level, mem + "");
            mem = "";
        }



    }

}
