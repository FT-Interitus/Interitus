/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.Addons.Interitus.Arduino;

import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.Wait;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.Tool;

import java.util.ArrayList;

public class NeoPixel extends Addon {
    @Override
    public String getProjectTypebyName() {
        System.out.println("Hier");
        return "Arduino-Projekt";
    }

    @Override
    public ArrayList<PlatformSpecificBlock> getaddBlocks() {
        return null;
    }

    @Override
    public ArrayList<Tool> getTools() {
        return null;
    }

    @Override
    public String getName() {
        return "NeoPixel";
    }
}
