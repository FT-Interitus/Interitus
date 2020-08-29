/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.Addons.Interitus.Arduino;

import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;

public class InitNativAddons {
    public static void init() {
        ProjectTypesVar.addons.add(new NeoPixel());
    }
}
