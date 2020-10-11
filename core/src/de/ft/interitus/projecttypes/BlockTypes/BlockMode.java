/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.ProgrammArea.ProgrammArea;
import de.ft.interitus.utils.ArrayList;

public abstract class BlockMode {

    public abstract ArrayList<Parameter> getBlockParameter();

    public abstract BlockSettings getblocksettings();

    public abstract int getWidth();

    public abstract String getname();

    public abstract Texture getModiImage();

    public ProgrammArea getProgrammArea(){
        return ProgrammArea.PROGRAMM_BLOCK;
    }
}
