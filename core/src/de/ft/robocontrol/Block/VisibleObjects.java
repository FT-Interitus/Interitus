package de.ft.robocontrol.Block;

public interface VisibleObjects {


    boolean isVisible();
    boolean amiablock();
    Block getblock();
    int getX_entrance();
    int getY_entrance();
    int getW_entrance();
    int getH_entrance();

    int getX_exit();
    int getY_exit();
    int getW_exit();
    int getH_exit();
}
