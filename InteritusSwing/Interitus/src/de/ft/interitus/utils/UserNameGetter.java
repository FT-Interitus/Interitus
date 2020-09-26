/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import de.ft.interitus.Programm;

public class UserNameGetter {
    public static String get() {
        String str = System.getProperty("user.name");
        Programm.logger.config("Username is: " + str.substring(0, 1).toUpperCase() + str.substring(1));
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}

