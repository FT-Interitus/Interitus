/*
 * Copyright (c) 2020.
 * Author Tim & Felix
 */

package de.ft.interitus.utils;

public class UserNameGetter {
    public static String get() {
        String str = System.getProperty("user.name");
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}

