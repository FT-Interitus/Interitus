package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.If;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;

import java.awt.*;

public class If extends PlatformSpecificBlock {
    public If(ProjectTypes projectTypes, Addon addon) {
        super(projectTypes, addon);
    }

    @Override
    public String getName() {
        return "If";
    }

    @Override
    public String getdescription() {
        return "Dieser Block vergleicht mehrere Daten";
    }

    @Override
    public Color blockcolor() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategoration() {
        return BlockCategories.Programm_Sequence;
    }

    @Override
    public Texture getSmallImage() {
        return null;
    }


    @Override
    public Texture getDescriptionImage() {
        return null;
    }

    @Override
    public boolean canbedeleted() {
        return false;
    }

    @Override
    public boolean canhasrightconnector() {
        return false;
    }

    @Override
    public boolean canhasleftconnector() {
        return false;
    }
}
