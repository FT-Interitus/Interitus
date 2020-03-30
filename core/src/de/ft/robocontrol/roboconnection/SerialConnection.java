package de.ft.robocontrol.roboconnection;
import com.fazecast.jSerialComm.*;
import java.util.Scanner;

public class SerialConnection {

public static void serial() {
    // determine which serial port to use

    SerialPort ports[] = SerialPort.getCommPorts();
    System.out.println("Select a port:");
    int i = 1;
    for (SerialPort port : ports) {
        System.out.println(i++ + ". " + port.getSystemPortName());
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
    Scanner data = new Scanner(port.getInputStream());
    System.out.println(data.hasNextLine());
    while (data.hasNextLine()) {
        System.out.println("ndrinn");
        int number = 0;
        try {
            number = Integer.parseInt(data.nextLine());
        } catch (Exception e) {
        }
        System.out.println("nubmer    "+number);
    }

}

}
