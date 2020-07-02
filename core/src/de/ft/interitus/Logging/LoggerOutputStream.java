/*
 * Copyright (c) 2020.
 * Author Tim & Felix
 */

package de.ft.interitus.Logging;

import java.io.OutputStream;
import java.util.ArrayList;
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
     */
    @Override
    public void flush() {
        if(level==Level.INFO) {
            logger.log(level, mem + "\n");
            mem = "";
        }else{
            logger.log(level, mem + "");
            mem = "";
        }

    }

}
