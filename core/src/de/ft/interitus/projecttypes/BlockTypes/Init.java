/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import de.ft.interitus.Program;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.block.BlockEventAdapter;
import de.ft.interitus.events.block.BlockKillMovingWiresEvent;
import de.ft.interitus.projecttypes.Addons.Interitus.Arduino.InitNativAddons;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.InitEv3;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.RaspberryPi.InitRaspberryPI;
import de.ft.interitus.projecttypes.ProjectManager;

public class Init {

    public static void initBlocks() {

        ProjectTypesVar.projectTypes.add(InitArduino.init());
        ProjectTypesVar.projectTypes.add(InitRaspberryPI.init());
        ProjectTypesVar.projectTypes.add(InitEv3.init());

        // ProgramRegistry.addProjectTypes();


        InitNativAddons.init();

        EventVar.blockEventManager.addListener(new BlockEventAdapter() {
            @Override
            public void killmovingwires(BlockKillMovingWiresEvent e) {

            }
        });

        Program.logger.config("Block loaded");
    }
}
