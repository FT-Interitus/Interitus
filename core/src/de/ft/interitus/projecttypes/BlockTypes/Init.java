/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import de.ft.interitus.Programm;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.block.BlockEventAdapter;
import de.ft.interitus.events.block.BlockKillMovingWiresEvent;
import de.ft.interitus.plugin.PluginGateway;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.Addons.Interitus.Arduino.InitNativAddons;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.InitEv3;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.RaspberryPi.InitRaspberryPI;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectTypes;

public class Init {

    public static void initBlocks() {

        ProjectTypesVar.projectTypes.add(InitArduino.init());
        ProjectTypesVar.projectTypes.add(InitRaspberryPI.init());
        ProjectTypesVar.projectTypes.add(InitEv3.init());


        ProjectTypesVar.projectTypes.addAll(PluginGateway.pluginprojekttypes);


        InitNativAddons.init();

        EventVar.blockEventManager.addListener(new BlockEventAdapter() {
            @Override
            public void killmovingwires(BlockKillMovingWiresEvent e) {
                if (ProjectManager.getActProjectVar().movingwires != null) {
                    ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().isconnectorclicked = false;
                    ProjectManager.getActProjectVar().showleftdocker = false;

                    try {
                        ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().tempwire.getLeft_connection().setWire_right(null);
                        ProjectManager.getActProjectVar().visiblewires.remove(ProjectManager.getActProjectVar().wire_beginn.getBlockupdate().tempwire);
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

                    ProjectManager.getActProjectVar().movingwires = null;
                }
            }
        });

        Programm.logger.config("Block loaded");
    }
}
