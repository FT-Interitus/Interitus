package de.ft.robocontrol.Block;

import java.util.ArrayList;

public class BlockAttribute {

    //platform verfügbarkeit

    private boolean ARDUINO;
    private boolean ARDUINO_ROBOARM;
    private boolean ARDUINO_NEOPIXEL_CONTROLER;

    private boolean Raspberrypi;

    private boolean EV3;

    private ArrayList<Parameter> blockparamenter;




    public BlockAttribute() {




    }

    public void setBlockparamenter(ArrayList<Parameter> blockparamenter) {
        this.blockparamenter = blockparamenter;
    }

    public ArrayList<Parameter> getBlockparamenter() {
        return blockparamenter;
    }


//Getter und Setter für platformen


    public boolean isARDUINO() {
        return ARDUINO;
    }

    public boolean isARDUINO_NEOPIXEL_CONTROLER() {
        return ARDUINO_NEOPIXEL_CONTROLER;
    }

    public boolean isARDUINO_ROBOARM() {
        return ARDUINO_ROBOARM;
    }

    public boolean isEV3() {
        return EV3;
    }

    public boolean isRaspberrypi() {
        return Raspberrypi;
    }



    public void setARDUINO(boolean ARDUINO) {
        this.ARDUINO = ARDUINO;
    }

    public void setARDUINO_NEOPIXEL_CONTROLER(boolean ARDUINO_NEOPIXEL_CONTROLER) {
        this.ARDUINO_NEOPIXEL_CONTROLER = ARDUINO_NEOPIXEL_CONTROLER;
    }

    public void setARDUINO_ROBOARM(boolean ARDUINO_ROBOARM) {
        this.ARDUINO_ROBOARM = ARDUINO_ROBOARM;
    }

    public void setEV3(boolean EV3) {
        this.EV3 = EV3;
    }

    public void setRaspberrypi(boolean raspberrypi) {
        Raspberrypi = raspberrypi;
    }




}
