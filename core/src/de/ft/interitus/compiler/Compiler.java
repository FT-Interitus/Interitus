/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.compiler;

public interface Compiler {
    String compile();

    boolean compileandrun();

    String getCompilerVersion();

    void interupt();
}
