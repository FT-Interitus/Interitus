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
   ArrayList<BlockTopParameter> getblocktopparamter();
     int getWidth();
     String getname();
     Texture getModiImage();
}
