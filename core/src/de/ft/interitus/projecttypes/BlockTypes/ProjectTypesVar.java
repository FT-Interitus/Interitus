/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import de.ft.interitus.plugin.Native;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.ProjectType;
import de.ft.interitus.utils.ArrayList;

public class ProjectTypesVar {


    /**
     * 0 -> Arduino
     * 1 -> Raspberry Pi
     * 2 -> Ev3
     * 3... -> Plugins
     */
    public static ArrayList<ProjectType> projectTypes = new ArrayList<>();
    public static ArrayList<Addon> addons = new ArrayList<>();

    public static Plugin nativ = new Native();

}
