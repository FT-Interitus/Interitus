/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends InputStream implements AutoCloseable {
    private long bytesRead = 0;

    private final InputStream stream;

    public CountingInputStream(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public int read() throws IOException {
        int result = stream.read();
        if (result != -1) {
            bytesRead++;
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        super.close();
        stream.close();
    }


    public long getBytesRead() {
        return bytesRead;
    }
}
