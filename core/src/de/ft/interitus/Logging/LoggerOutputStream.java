package de.ft.interitus.Logging;

import de.ft.interitus.Programm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerOutputStream extends OutputStream {
    public Logger logger;
    private String mem;
    Level level;
    public ArrayList<Byte> bytes = new ArrayList<>();

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
        logger.log(level, mem+"\n");
        mem ="";

    }

}
