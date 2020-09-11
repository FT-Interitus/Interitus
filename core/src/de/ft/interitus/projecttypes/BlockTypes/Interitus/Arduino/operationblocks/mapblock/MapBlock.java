package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.mapblock;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;

import java.awt.*;

public class MapBlock extends PlatformSpecificBlock {
    public MapBlock(ProjectTypes projectTypes, Addon addon) {
        super(projectTypes, addon);
        super.blockModis.add(new MapModiDefault());
        super.actBlockModiIndex=0;
;    }

    @Override
    public String getName() {

        return "MapBlock";
    }

    @Override
    public String getdescription() {
        return "Dieser Block macht map und muh";
    }

    @Override
    public Color blockcolor() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategoration() {
        return BlockCategories.Data_Operation;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.DigitalWrite_description_image;
    }

    @Override
    public boolean canbedeleted() {
        return true;
    }

    @Override
    public boolean canhasrightconnector() {
        return true;
    }

    @Override
    public boolean canhasleftconnector() {
        return true;
    }
}
