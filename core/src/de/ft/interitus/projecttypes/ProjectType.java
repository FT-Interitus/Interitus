/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import de.ft.interitus.Block.Generators.*;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Var;
import de.ft.interitus.compiler.Compiler;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.utils.ArrayList;

public class ProjectType {

    private final de.ft.interitus.plugin.Plugin pluginRegister;
    private final BlockVarGenerator blockVarGenerator;
    private final ProjectFunktions projectFunktions;
    private final Compiler compiler;
    String name;
    ArrayList<PlatformSpecificBlock> projectblocks;
    BlockGenerator blockGenerator = null;

    private BlockToSaveGenerator blocktoSaveGenerator;
    private boolean codeshowable = true;


    public ProjectType(Plugin pluginRegister, String name, ArrayList<PlatformSpecificBlock> blocks, BlockGenerator blockgenerator, BlockToSaveGenerator blocktoSaveGenerator, BlockVarGenerator blockVarGenerator, ProjectFunktions projectFunktions, Compiler compiler) {
        this.projectblocks = blocks;

        this.name = name;
        this.blockGenerator = blockgenerator;

        this.blocktoSaveGenerator = blocktoSaveGenerator;
        this.pluginRegister = pluginRegister;
        this.blockVarGenerator = blockVarGenerator;
        this.projectFunktions = projectFunktions;
        this.compiler = compiler;
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





    public BlockToSaveGenerator getBlocktoSaveGenerator() {
        return blocktoSaveGenerator;
    }

    public void setBlocktoSaveGenerator(BlockToSaveGenerator blocktoSaveGenerator) {
        this.blocktoSaveGenerator = blocktoSaveGenerator;
    }

    public Plugin getPluginRegister() {
        return pluginRegister;
    }


    public ProjectVar init() {
        ProjectVar blockvar = this.blockVarGenerator.generate();
        blockvar.projectType = this;
        return blockvar;

    }


    public void initProject() {
        projectFunktions.create();
        Tools.update();

    }

    public void update() {


        try {

            projectFunktions.update();

        } catch (Throwable e) {


            if(Var.inProgram) {
                DisplayErrors.customErrorstring = "Fehler im ProjectTyp";
                DisplayErrors.error = e;

            e.printStackTrace();
            }
        }


    }

    public ProjectFunktions getProjectFunktions() {
        return projectFunktions;
    }


    public boolean isCodeshowable() {
        return codeshowable;
    }

    public void setCodeshowable(boolean codeshowable) {
        this.codeshowable = codeshowable;
    }

    public Compiler getCompiler() {
        return compiler;
    }
}
