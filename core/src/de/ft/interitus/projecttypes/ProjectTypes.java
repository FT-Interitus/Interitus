package de.ft.interitus.projecttypes;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockGenerator;
import de.ft.interitus.Block.BlockUpdateGenerator;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.ProgrammableObjekt;

import java.util.ArrayList;

public class ProjectTypes {
    ProgrammableObjekt PO;
    String name;
    ArrayList<PlatformSpecificBlock> projectblocks;
    BlockGenerator blockGenerator =null;
    BlockUpdateGenerator blockUpdateGenerator = null;

    public ProjectTypes(ProgrammableObjekt PO, String name, ArrayList<PlatformSpecificBlock> blocks, BlockGenerator blockgenerator, BlockUpdateGenerator updategenerator) {
        this.projectblocks = blocks;
        this.PO = PO;
        this.name = name;
        this.blockGenerator = blockgenerator;
        this.blockUpdateGenerator = updategenerator;
    }

    public ProgrammableObjekt getPO() {
        return PO;
    }

    public void setPO(ProgrammableObjekt PO) {
        this.PO = PO;
    }

    public String getName() {
        return name;
    }

    public ArrayList<PlatformSpecificBlock> getProjectblocks() {
        return projectblocks;
    }

    public void setProjectblocks(ArrayList<PlatformSpecificBlock> projectblocks) {
        this.projectblocks = projectblocks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlockGenerator getBlockGenerator() {
        return blockGenerator;
    }

    public void setBlockGenerator(BlockGenerator blockGenerator) {
        this.blockGenerator = blockGenerator;
    }

    public void setBlockUpdateGenerator(BlockUpdateGenerator blockUpdateGenerator) {
        this.blockUpdateGenerator = blockUpdateGenerator;
    }

    public BlockUpdateGenerator getBlockUpdateGenerator() {
        return blockUpdateGenerator;
    }
}
