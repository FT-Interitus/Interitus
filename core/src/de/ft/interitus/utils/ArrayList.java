package de.ft.interitus.utils;

public class ArrayList<E> extends java.util.ArrayList<E> {

    public E getLastObject() {
        return (E) this.get(this.size()-1);
    }
}
