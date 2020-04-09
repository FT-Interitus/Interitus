package de.ft.robocontrol.data;

import de.ft.robocontrol.Block.Devices;
import de.ft.robocontrol.UI.setup.SetupWindow;

import java.util.ArrayList;

public class VerbindungsSpeicher {
    public static ArrayList<VerbindungsSpeicher> verbundungen = new ArrayList<VerbindungsSpeicher>();
    public String name = "";
    private int device = Devices.UNDEFINIERT;
    private ArduinoSpeicher arduinospeicher;
    private RaspberryPiSpeicher raspberrypispeicher;
    private EV3Speicher ev3Speicher;
    private EV3devSpeicher ev3devSpeicher;

    public void setDevice(int device) {
        this.device = device;
        if(device==Devices.ARDUINO_UNO){
            arduinospeicher =new ArduinoSpeicher();
            raspberrypispeicher=null;
            ev3devSpeicher=null;
            ev3Speicher=null;
        }
        if(device==Devices.ARDUINO_MEGA){
            arduinospeicher =new ArduinoSpeicher();
            raspberrypispeicher=null;
            ev3devSpeicher=null;
            ev3Speicher=null;
        }
        if(device== Devices.RASPBERRYPI4){
            raspberrypispeicher=new RaspberryPiSpeicher();
            arduinospeicher=null;
            ev3Speicher=null;
            ev3devSpeicher=null;
        }
        if(device==Devices.RASPBERRY3Bplus){
            arduinospeicher =new ArduinoSpeicher();
            arduinospeicher=null;
            ev3Speicher=null;
            ev3devSpeicher=null;
        }
        if(device==Devices.RASPBERRYPIZEROW){
            arduinospeicher =new ArduinoSpeicher();
            arduinospeicher=null;
            ev3Speicher=null;
            ev3devSpeicher=null;
        }
        if(device==Devices.EV3){
            ev3Speicher=new EV3Speicher();
            arduinospeicher=null;
            raspberrypispeicher=null;
            ev3devSpeicher=null;
        }
        if(device==Devices.EV3_DEV){
          ev3devSpeicher=new EV3devSpeicher();
          arduinospeicher=null;
          raspberrypispeicher=null;
          ev3Speicher=null;
        }

    }


    public ArduinoSpeicher getArduinospeicher() {
        return arduinospeicher;
    }

    public int getDevice() {
        return device;
    }

    public VerbindungsSpeicher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public class ArduinoSpeicher{
        public String id="";
        public ArduinoSpeicher(){

        }
    }
    public class RaspberryPiSpeicher{
        public String ip="";
        public int port=22;
        public String username="";
        public String password="";
        public RaspberryPiSpeicher(){

        }
    }

    public class EV3Speicher{
        public EV3Speicher(){

        }
    }

    public class EV3devSpeicher{
        public EV3devSpeicher(){

        }
    }

}



