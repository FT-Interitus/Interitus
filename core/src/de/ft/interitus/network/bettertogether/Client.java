package de.ft.interitus.network.bettertogether;

import de.ft.interitus.Programm;
import de.ft.interitus.Var;
import de.ft.interitus.utils.StringUtils;
import de.ft.interitus.utils.Unproject;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void connect(String ip, int Port) {
        try {
            Socket socket = new Socket(ip,Port);
            ArrayList<String> titleList = new ArrayList<String>();
            try {

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                outputStreamWriter.write("!R!V"+ Var.PROGRAMM_VERSION+"\\|"+"U"+Var.username+"\n");
                outputStreamWriter.flush();


                Reader reader;
                reader = new InputStreamReader(socket.getInputStream());
                BufferedReader bfreader = new BufferedReader(reader);

                if(bfreader.readLine().contains("ok")) {
                    Programm.logger.config("Accepted");



                    boolean test = true;
                    while(test) {
                        outputStreamWriter.write((int)Unproject.unproject().x+"t"+(int)Unproject.unproject().y+"\n");
                        outputStreamWriter.flush();
                        Programm.logger.config(Unproject.unproject().x+"t"+Unproject.unproject().y);
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }




                }else{
                    Programm.logger.config("Error");
                    socket.close();
                    return;
                }


                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                try {
                    Object object = objectInput.readObject();
                    titleList =  (ArrayList<String>) object;
                    System.out.println(titleList.get(1));
                } catch (ClassNotFoundException e) {
                    System.out.println("The title list has not come from the server");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println("The socket for reading the object has problem");
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
