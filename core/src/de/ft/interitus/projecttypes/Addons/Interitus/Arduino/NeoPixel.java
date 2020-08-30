/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.Addons.Interitus.Arduino;

import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.Wait.Wait;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.WhileLoopEnd;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.Tool;

import java.util.ArrayList;

public class NeoPixel implements Addon {
    ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();
    public NeoPixel() {

        platformSpecificBlocks.add(new WhileLoopEnd(InitArduino.arduino,this));

    }

    @Override
    public String getProjectTypebyName() {
        return "Arduino-Projekt";
    }

    @Override
    public ArrayList<PlatformSpecificBlock> getaddBlocks() {
        return platformSpecificBlocks;
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
