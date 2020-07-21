package de.ft.interitus.projecttypes;

import de.ft.interitus.Block.Generators.*;
import de.ft.interitus.compiler.Compiler;
import de.ft.interitus.plugin.PluginRegister;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;

import java.util.ArrayList;

public class ProjectTypes {
    ProgrammableObjekt PO;
    String name;
    ArrayList<PlatformSpecificBlock> projectblocks;
    private final de.ft.interitus.plugin.PluginRegister pluginRegister;
    BlockUpdateGenerator blockUpdateGenerator = null;
    private final BlockVarGenerator blockVarGenerator;
    BlockGenerator blockGenerator = null;
    private WireGenerator wireGenerator;
    private WireNodeGenerator wireNodeGenerator;
    private BlocktoSaveGenerator blocktoSaveGenerator;
    private ProjectCreator projectCreator;
    private Compiler compiler;

    public ProjectTypes(PluginRegister pluginRegister, ProgrammableObjekt PO, String name, ArrayList<PlatformSpecificBlock> blocks, BlockGenerator blockgenerator, BlockUpdateGenerator updategenerator, WireGenerator wireGenerator, WireNodeGenerator wireNodeGenerator, BlocktoSaveGenerator blocktoSaveGenerator, BlockVarGenerator blockVarGenerator, ProjectCreator projectCreator,Compiler compiler) {
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
        this.projectCreator = projectCreator;
        this.compiler = compiler;
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

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PlatformSpecificBlock> getProjectblocks() {
        return projectblocks;
    }

    public void setProjectblocks(ArrayList<PlatformSpecificBlock> projectblocks) {
        this.projectblocks = projectblocks;
    }

    public BlockGenerator getBlockGenerator() {
        return blockGenerator;
    }

    public void setBlockGenerator(BlockGenerator blockGenerator) {
        this.blockGenerator = blockGenerator;
    }

    public BlockUpdateGenerator getBlockUpdateGenerator() {
        return blockUpdateGenerator;
    }

    public void setBlockUpdateGenerator(BlockUpdateGenerator blockUpdateGenerator) {
        this.blockUpdateGenerator = blockUpdateGenerator;
    }

    public WireGenerator getWireGenerator() {
        return wireGenerator;
    }

    public void setWireGenerator(WireGenerator wireGenerator) {
        this.wireGenerator = wireGenerator;
    }

    public WireNodeGenerator getWireNodeGenerator() {
        return wireNodeGenerator;
    }

    public void setWireNodeGenerator(WireNodeGenerator wireNodeGenerator) {
        this.wireNodeGenerator = wireNodeGenerator;
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


    public ProjectVar init() {
        ProjectVar blockvar = this.blockVarGenerator.generate();
        blockvar.projectType = this;
        return blockvar;

    }


  public void initProject() {
        projectCreator.create();
  }

    public Compiler getCompiler() {
        return compiler;
    }
}
