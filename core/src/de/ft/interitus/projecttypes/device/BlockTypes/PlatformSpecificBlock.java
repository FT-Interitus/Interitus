package de.ft.interitus.projecttypes.device.BlockTypes;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;

import java.awt.*;
import java.util.ArrayList;

public interface PlatformSpecificBlock {


    ArrayList<Parameter> getBlockParameter();
    String getName();
    String getdescription();
    ArrayList<BlockTopParameter> getblocktopparamter();
    Color blockcolor();
    BlockCategories getBlockCategoration();
    Texture getSmallImage();
    Texture getImage();



}
