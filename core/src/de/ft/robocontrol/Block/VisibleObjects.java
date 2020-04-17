package de.ft.robocontrol.Block;

public interface VisibleObjects {


    boolean isVisible();
    boolean amiablock();
    boolean amiwirenode();
    Block getblock();
    WireNode getwirenode();
    float getX_entrance();
    float getY_entrance();
    float getW_entrance();
    float getH_entrance();

    float getX_exit();
    float getY_exit();
    float getW_exit();
    float getH_exit();

    void setWire_right(Wire o);
    void setWire_left(Wire o);
}
