package de.ft.interitus.projecttypes.BlockTypes;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.ProjectTypes;

import java.awt.*;
import de.ft.interitus.utils.ArrayList;

public interface PlatformSpecificBlock {


    ArrayList<Parameter> getBlockParameter();

    String getName();

    String getdescription();

    ArrayList<BlockTopParameter> getblocktopparamter();

    Color blockcolor();

    BlockCategories getBlockCategoration();

    Texture getSmallImage();
    Texture getImageRight();
    Texture getImageLeft();
    Texture getImageCenter();
    Texture getDescriptionImage();


    int getID();

    ProjectTypes getProjectType();

    int getWidth();

    boolean canbedeleted();

    boolean canhasrightconnector();

    boolean canhasleftconnect();
}
