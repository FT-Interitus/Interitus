/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;


import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.ProgrammArea.ProgrammArea;
import de.ft.interitus.utils.ArrayList;

import java.awt.image.BufferedImage;

public abstract class BlockModi {

    public abstract ArrayList<Parameter> getBlockParameter();

    public abstract BlockSettings getblocksettings();

    public abstract int getWidth();

    public abstract String getname();

    public abstract BufferedImage getModiImage();

    public ProgrammArea getProgrammArea(){
        return ProgrammArea.PROGRAMM_BLOCK;
    }
}
