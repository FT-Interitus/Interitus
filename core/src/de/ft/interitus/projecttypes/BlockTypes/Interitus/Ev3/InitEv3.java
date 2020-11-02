/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3;

import com.badlogic.gdx.graphics.Color;
import de.ft.interitus.Block.Interitus.*;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.compiler.Interitus.EV3.EV3compiler;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.actionBlocks.light.StatusLightBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.programmsequence.Thread.ThreadBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.programmsequence.Wait.Wait;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ParameterVariableType;
import de.ft.interitus.projecttypes.ProjectType;
import de.ft.interitus.utils.ArrayList;

public class InitEv3 {
    static ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();
    public static ParameterVariableType floatvar;
    public static ParameterVariableType stringvar;
    public static ParameterVariableType booleanvar;

    public static ProjectType init() {
        ProjectType projectType = new ProjectType(ProgramingSpace.nativ, "Ev3-Projekt", platformSpecificBlocks, new DefaultBlockGenerator(), new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator(), new EV3Funktions(), new EV3compiler());

        floatvar = new ParameterVariableType("float", AssetLoader.Plug_IntParameter,new Color(23f/255f,141f/255f,209f/255f,1f),"int");
        stringvar = new ParameterVariableType("String", AssetLoader.Plug_StringParameter,new Color(156f/255f,19f/255f,19f/255f,1f),"char[]");
        booleanvar = new ParameterVariableType("boolean", AssetLoader.Plug_BooleanParameter,new Color(245f/255f,169f/255f,56f/255f,1f));

        /**
         * Need to be the first one
         * @see EV3Funktions
         */
        platformSpecificBlocks.add(new ThreadBlock(projectType, null));

        platformSpecificBlocks.add(new StatusLightBlock(projectType, null));
        platformSpecificBlocks.add(new Wait(projectType, null));
        return projectType;
    }
}
