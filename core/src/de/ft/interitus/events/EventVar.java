package de.ft.interitus.events;


import de.ft.interitus.events.block.BlockEventManager;
import de.ft.interitus.events.global.GlobalEventManager;
import de.ft.interitus.events.rightclick.RightClickEventManager;

public class EventVar {
    public static BlockEventManager blockEventManager = new BlockEventManager();
    public static RightClickEventManager rightClickEventManager = new RightClickEventManager();
    public static GlobalEventManager globalEventManager = new GlobalEventManager();

}
