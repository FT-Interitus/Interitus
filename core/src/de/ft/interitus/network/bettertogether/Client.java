/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.network.bettertogether;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Saving.SaveBlockV1;
import de.ft.interitus.Program;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.BlockCalculator;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.block.BlockEventAdapter;
import de.ft.interitus.events.block.BlockNeighborSetEvent;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;
import de.ft.interitus.utils.Unproject;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Client {

    private static final ArrayList<String> sendinglist = new ArrayList<>();

    public static void connect(String ip, int Port) {

        EventVar.blockEventManager.addListener(new BlockEventAdapter() {
            @Override
            public void setNeighbor(BlockNeighborSetEvent e, Block block, Block neightbour, boolean right) {


                if (right) {
                    if (neightbour == null) {
                        sendinglist.add("!BNR!" + block.getIndex() + "t" + "-1" + "\n");
                    } else {
                        sendinglist.add("!BNR!" + block.getIndex() + "t" + neightbour.getIndex() + "\n");
                    }
                } else {
                    if (neightbour == null) {
                        sendinglist.add("!BNL!" + block.getIndex() + "t" + "-1" + "\n");
                    } else {
                        sendinglist.add("!BNL!" + block.getIndex() + "t" + neightbour.getIndex() + "\n");
                    }
                }


            }
        });

        try {
            Socket socket = new Socket(ip, Port);

            try {

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                outputStreamWriter.write("!R!V" + Var.PROGRAMM_VERSION + "\\|" + "U" + Var.username + "\n");
                outputStreamWriter.flush();


                Reader reader;
                reader = new InputStreamReader(socket.getInputStream());
                BufferedReader bfreader = new BufferedReader(reader);

                if (bfreader.readLine().contains("ok")) {
                    Program.logger.config("Accepted");


                    ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                    try {
                        ArrayList<SaveBlockV1> titleList;
                        Object object = objectInput.readObject();
                        titleList = (ArrayList<SaveBlockV1>) object;
                        BlockCalculator.extractV1(titleList);
                    } catch (ClassNotFoundException e) {
                        Program.logger.config("The title list has not come from the server");
                        e.printStackTrace();
                    }


                    Timer blockmovingtimer = new Timer();
                    blockmovingtimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if (ProjectManager.getActProjectVar().marked_blocks != null) {
                                //sendinglist.add("!BM!" + ProjectManager.getActProjectVar().marked_block.getIndex() + "t" + ProjectManager.getActProjectVar().marked_block.getX() + "t" + ProjectManager.getActProjectVar().marked_block.getY() + "\n");

                            }


                        }
                    }, 50, 50);


                    sendinglist.clear();

                    while (true) {

                        while (sendinglist.size() != 0) {

                            outputStreamWriter.write(sendinglist.get(0));
                            outputStreamWriter.flush();
                            sendinglist.remove(0);


                            try {
                                TimeUnit.MILLISECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            outputStreamWriter.write("!M!" + (int) Unproject.unproject().x + "t" + (int) Unproject.unproject().y + "\n");
                            outputStreamWriter.flush();

                            try {
                                TimeUnit.MILLISECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                        }


                        outputStreamWriter.write("!M!" + (int) Unproject.unproject().x + "t" + (int) Unproject.unproject().y + "\n");
                        outputStreamWriter.flush();


                        try {
                            TimeUnit.MILLISECONDS.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                } else {
                    Program.logger.config("Error");
                    socket.close();
                    return;
                }


            } catch (IOException e) {
                Program.logger.config("The socket for reading the object has problem");
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
