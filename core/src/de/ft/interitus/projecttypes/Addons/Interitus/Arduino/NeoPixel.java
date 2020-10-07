/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.Addons.Interitus.Arduino;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.WhileLoopEnd;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.Tool;

import java.io.Serializable;
import de.ft.interitus.utils.ArrayList;

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
        return ProgramingSpace.nativ;
    }

    @Override
    public void getAddonSettings(VisTable table) {
        table.add(new VisLabel("nothing")).expandX().fillY().row();
    }

    @Override
    public Serializable getAddonSettings() {
        return new ArrayList<String>();
    }

    @Override
    public void setAddonSettings(Object object) {

    }
}
