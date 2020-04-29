package de.ft.interitus.events;


import de.ft.interitus.events.block.BlockEventManager;
import de.ft.interitus.events.rightclick.RightClickEventListener;
import de.ft.interitus.events.rightclick.RightClickEventManager;
import de.ft.interitus.events.rightclick.RightClickOpenEvent;

public class EventVar {
    public static BlockEventManager blockEventManager = new BlockEventManager();
    public static RightClickEventManager rightClickEventManager = new RightClickEventManager();


}
