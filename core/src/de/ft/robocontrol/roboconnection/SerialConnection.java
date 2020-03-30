package de.ft.robocontrol.roboconnection;
import com.fazecast.jSerialComm.*;
import java.util.Scanner;

public class SerialConnection {

    public static int empfangen(SerialPort sport){
        Scanner data = new Scanner(sport.getInputStream());
        System.out.println(data.hasNextLine());
        if (data.hasNextLine()) {
            int number = 0;
            try {
                number = Integer.parseInt(data.nextLine());
            } catch (Exception e) {
            }
            System.out.println("nubmer    "+number);
            return number;
        }else{
            return -1;
        }


    }


public static void serial() {
    // determine which serial port to use

    SerialPort ports[] = SerialPort.getCommPorts();
    System.out.println("Select a port:");
    int i = 1;
    for (SerialPort port : ports) {
        System.out.println(i++ + ". " + port.getSystemPortName());
        System.out.println(port.getDescriptivePortName()+"   deks");
        System.out.println("i     "+i);
try {
    SerialPort testport = ports[i-2];

    if (testport.openPort()) {
        System.out.println("Successfully opened the port.");
        System.out.println("baudrate:   " + testport.getDescriptivePortName());
    } else {
        System.out.println("Unable to open the port.");
        return;
    }

    testport.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);



    for(int a=0;a<100;a++) {
        System.out.println("test:   " + empfangen(testport));
    }
}catch (ArrayIndexOutOfBoundsException e){}

        System.out.println("-----------------------------------------------------------");

    }
    Scanner s = new Scanner(System.in);
    int chosenPort = s.nextInt();


    // open and configure the port
    SerialPort port = ports[chosenPort - 1];

    if (port.openPort()) {
        System.out.println("Successfully opened the port.");
        System.out.println("baudrate:   "+port.getBaudRate());
    } else {
        System.out.println("Unable to open the port.");
        return;
    }


    port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

    // enter into an infinite loop that reads from the port and updates the GUI
    while(true) {
        System.out.println(empfangen(port));
    }
}

}
