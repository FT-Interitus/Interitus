/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import java.io.Serializable;
import java.util.List;

public class ArrayList<E> extends java.util.ArrayList<E> implements Serializable {

    public ArrayList() {

    }

    public ArrayList(List<E> list) {
        super(list);
    }

    public E getLastObject() {
        return this.get(this.size() - 1);
    }
}
