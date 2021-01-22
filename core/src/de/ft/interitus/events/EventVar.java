/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events;


import de.ft.interitus.Program;
import de.ft.interitus.events.block.BlockCreateEvent;
import de.ft.interitus.events.block.BlockEventManager;
import de.ft.interitus.events.global.GlobalEventManager;
import de.ft.interitus.events.plugin.PluginEventManager;
import de.ft.interitus.events.plugin.store.PluginStoreEventManager;
import de.ft.interitus.events.rightclick.RightClickEventManager;


public class EventVar {


    @EventHandler(ListeningEvent = {BlockCreateEvent.class})
    public static void test(BlockCreateEvent block) {
        Program.logger.config("Listing to: " + block.getBlock().getBlockType().getName());


    }


    public static BlockEventManager blockEventManager = new BlockEventManager();
    public static RightClickEventManager rightClickEventManager = new RightClickEventManager();
    public static GlobalEventManager globalEventManager = new GlobalEventManager();
    public static PluginEventManager pluginEventManager = new PluginEventManager();
    public static PluginStoreEventManager pluginStoreEventManager = new PluginStoreEventManager();


}
