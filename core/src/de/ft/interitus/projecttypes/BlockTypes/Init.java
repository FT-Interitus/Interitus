/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import de.ft.interitus.Programm;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.block.BlockEventAdapter;
import de.ft.interitus.events.block.BlockKillMovingWiresEvent;
import de.ft.interitus.plugin.ProgramRegistry;
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

        ProgramRegistry.addProjectTypes();


        InitNativAddons.init();

        EventVar.blockEventManager.addListener(new BlockEventAdapter() {
            @Override
            public void killmovingwires(BlockKillMovingWiresEvent e) {
                if (ProjectManager.getActProjectVar().moving_wires != null) {
                    ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().isconnectorclicked = false;
                    ProjectManager.getActProjectVar().showleftdocker = false;

                    try {
                        ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().tempwire.getLeft_connection().setWire_right(null);
                        ProjectManager.getActProjectVar().visible_wires.remove(ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().tempwire);
                        ProjectManager.getActProjectVar().wires.remove(ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().tempwire);

                        try {

                            ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().tempwire.getRight_connectionObject().getwirenode().setWire_left(null);
                            ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().tempwire.setRight_connection(null);

                        } catch (NullPointerException a) {
                            //Falls hier keine Wire ist
                        }
                        ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().tempwire = null;
                    } catch (Exception ignored) {

                    }

                    ProjectManager.getActProjectVar().moving_wires = null;
                }
            }
        });

        Programm.logger.config("Block loaded");
    }
}
