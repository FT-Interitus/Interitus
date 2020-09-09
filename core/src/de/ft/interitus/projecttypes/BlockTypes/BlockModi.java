/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.utils.ArrayList;

public interface BlockModi {

    ArrayList<Parameter> getBlockParameter();

    BlockSettings getblocksettings();

    int getWidth();

    String getname();

    Texture getModiImage();
}
