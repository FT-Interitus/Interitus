package de.ft.interitus.projecttypes.device.BlockTypes.Ev3;

import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;

import java.awt.*;
import java.util.ArrayList;

public class Sound implements PlatformSpecificBlock,EV3Block {
    @Override
    public String getCode() {
        return "SOUND(TONE,100,700,1000)";
    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return null;
    }

    @Override
    public String getName() {
        return "Sound";
    }

    @Override
    public String getdescription() {
        return "Spielt Töne ab";
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
        return BlockCategories.ActionBlocks;
    }
}
