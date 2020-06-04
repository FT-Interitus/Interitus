package de.ft.interitus.projecttypes;

import de.ft.interitus.Block.Generators.BlockGenerator;
import de.ft.interitus.Block.Generators.BlockUpdateGenerator;
import de.ft.interitus.Block.Generators.WireGenerator;
import de.ft.interitus.Block.Generators.WireNodeGenerator;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.ProgrammableObjekt;

import java.util.ArrayList;

public class ProjectTypes {
    ProgrammableObjekt PO;
    String name;
    ArrayList<PlatformSpecificBlock> projectblocks;
    BlockGenerator blockGenerator =null;
    BlockUpdateGenerator blockUpdateGenerator = null;
private WireGenerator wireGenerator;
private WireNodeGenerator wireNodeGenerator;
    public ProjectTypes(ProgrammableObjekt PO, String name, ArrayList<PlatformSpecificBlock> blocks, BlockGenerator blockgenerator, BlockUpdateGenerator updategenerator, WireGenerator wireGenerator, WireNodeGenerator wireNodeGenerator) {
        this.projectblocks = blocks;
        this.PO = PO;
        this.name = name;
        this.blockGenerator = blockgenerator;
        this.blockUpdateGenerator = updategenerator;
        this.wireGenerator = wireGenerator;
        this.wireNodeGenerator = wireNodeGenerator;
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


    public void setWireGenerator(WireGenerator wireGenerator) {
        this.wireGenerator = wireGenerator;
    }

    public WireGenerator getWireGenerator() {
        return wireGenerator;
    }

    public void setWireNodeGenerator(WireNodeGenerator wireNodeGenerator) {
        this.wireNodeGenerator = wireNodeGenerator;
    }

    public WireNodeGenerator getWireNodeGenerator() {
        return wireNodeGenerator;
    }


}
