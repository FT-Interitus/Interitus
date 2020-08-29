/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.Addons.Interitus.Arduino;

import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.Tool;

import java.util.ArrayList;

public class NeoPixel implements Addon {
    @Override
    public String getProjectTypebyName() {
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

    @Override
    public Plugin getPlugin() {
        return ProgrammingSpace.nativ;
    }
}
