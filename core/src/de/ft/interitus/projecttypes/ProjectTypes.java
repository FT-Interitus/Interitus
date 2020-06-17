package de.ft.interitus.projecttypes;

import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Block.Generators.*;
import de.ft.interitus.plugin.PluginRegister;
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
private BlocktoSaveGenerator blocktoSaveGenerator;
private de.ft.interitus.plugin.PluginRegister pluginRegister;
private BlockVarGenerator blockVarGenerator;
    public ProjectTypes(PluginRegister pluginRegister, ProgrammableObjekt PO, String name, ArrayList<PlatformSpecificBlock> blocks, BlockGenerator blockgenerator, BlockUpdateGenerator updategenerator, WireGenerator wireGenerator, WireNodeGenerator wireNodeGenerator, BlocktoSaveGenerator blocktoSaveGenerator,BlockVarGenerator blockVarGenerator) {
        this.projectblocks = blocks;
        this.PO = PO;
        this.name = name;
        this.blockGenerator = blockgenerator;
        this.blockUpdateGenerator = updategenerator;
        this.wireGenerator = wireGenerator;
        this.wireNodeGenerator = wireNodeGenerator;
        this.blocktoSaveGenerator = blocktoSaveGenerator;
        this.pluginRegister = pluginRegister;
        this.blockVarGenerator = blockVarGenerator;
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

    public BlocktoSaveGenerator getBlocktoSaveGenerator() {
        return blocktoSaveGenerator;
    }

    public void setBlocktoSaveGenerator(BlocktoSaveGenerator blocktoSaveGenerator) {
        this.blocktoSaveGenerator = blocktoSaveGenerator;
    }

    public PluginRegister getPluginRegister() {
        return pluginRegister;
    }


    public BlockVar init() {
      return this.blockVarGenerator.generate();

    }


}
