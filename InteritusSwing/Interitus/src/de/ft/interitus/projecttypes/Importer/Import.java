/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.Importer;

import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.ProjectTypes;

import java.io.File;

public interface Import {


    /**
     * You need to show a Error in case the user selected a no compatible File
     * @param file
     */
    void importproject(File file);
    String getName();
    Plugin getPlugin();
    ProjectTypes getTargetProjectType();

}
