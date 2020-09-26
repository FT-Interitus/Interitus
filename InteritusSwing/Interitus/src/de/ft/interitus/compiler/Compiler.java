/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.compiler;

import org.json.JSONException;

public interface Compiler {
    String compile();

    boolean compileandrun() throws JSONException;

    String getCompilerVersion() throws JSONException;

    void interupt();
}
