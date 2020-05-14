package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.NeopxielController;

import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;

import java.awt.*;
import java.util.ArrayList;

public class SetTableColor implements PlatformSpecificBlock { //TODO Description
    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return null;
    }

    @Override
    public String getName() {
        return "Neopixel-Farben";
    }

    @Override
    public String getdescription() {
        return null;
    }

    @Override
    public ArrayList<BlockTopParameter> getblocktopparamter() {
        return null;
    }

    @Override
    public Color blockcolor() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategoration() {
        return null;
    }
}
