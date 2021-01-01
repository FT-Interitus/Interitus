/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.UI;

import de.ft.interitus.events.Listener;

import java.util.ArrayList;
import java.util.function.Consumer;

public class EventManager<T> {
ArrayList<T> listerens = new ArrayList<>();

    T template ;
public EventManager() {


}



public void addListener(T listener) {
   if(listener.getClass().isAssignableFrom(template.getClass()))
        listerens.add(listener);
}
public void fireForEach(Consumer<T> action){
    for(T listener:listerens) action.accept(listener);

}

}
