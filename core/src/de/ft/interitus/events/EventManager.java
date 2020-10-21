/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events;

import de.ft.interitus.Block.Block;
import de.ft.interitus.utils.ArrayList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class EventManager {

    private static final ArrayList<Method> listeners = new ArrayList<>();

    public static void fireEvent(Object caller, Event event) {

        for (Method method : listeners) {
            if (!method.isAnnotationPresent(EventHandler.class)) continue;
            if (!Arrays.asList(method.getAnnotation(EventHandler.class).ListeningEvent()).contains(event.getClass())) continue;
            if(method.getParameterTypes().length!=1) continue;
            if(!Arrays.asList(method.getParameterTypes()[0].getInterfaces()).contains(Event.class)) continue;

            try {

                method.invoke(caller, event);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void addListenerClass(Class clazz) {
        for (Method method : clazz.getMethods()) {
            if (!method.isAnnotationPresent(EventHandler.class)) continue;
            listeners.add(method);
        }

    }


}
