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

public EventManager() {


}



public void addListener(T listener) {

        listerens.add(listener);
}
public void fireForEach(Consumer<T> action){
    for(T listener:listerens) {
        if(listener==null) continue;
        action.accept(listener);

    }

}

}
