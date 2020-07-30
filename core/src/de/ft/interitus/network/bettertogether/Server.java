package de.ft.interitus.network.bettertogether;

import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.UI.UI;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.BlockCalculator;
import de.ft.interitus.projecttypes.ProjectManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import de.ft.interitus.utils.ArrayList;
import java.util.concurrent.TimeUnit;

public class Server {
    private static Vector2 tempVector = new Vector2();
    private static Socket skt;
    private static  OutputStreamWriter outputStreamWriter;
    public static void start(int port) throws IOException {

        try {
            ServerSocket myServerSocket = new ServerSocket(port);

            while (true) {
                skt = myServerSocket.accept();

             startresiver();
              startsender();


              //V(VersionTag)|U(Usernname)|




            }
        }
        catch(IOException e)
            {
                e.printStackTrace();
            }

    }

    private static void sendstring(String str) {
        try {
            outputStreamWriter.write(str+"\n");
            outputStreamWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void startsender() {
        try {
            outputStreamWriter = new OutputStreamWriter(skt.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startresiver() {
        Thread resivier = new Thread() {
            @Override
            public void run() {
                while(skt.isConnected()) {
                    Reader reader = null;
                    try {
                        reader = new InputStreamReader(skt.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BufferedReader bfreader = new BufferedReader(reader);

                    try {
                        inputhandler(bfreader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        resivier.start();
    }

    private static void inputhandler(String readLine) {

        if(readLine==null) {
            return;
        }

        if(readLine.startsWith("!R!")) {
            if(register(readLine)==0) {
                sendstring("ok");



                ArrayList<SaveBlock> blocks = BlockCalculator.save();

                try {
                    ObjectOutputStream objectOutput = new ObjectOutputStream(skt.getOutputStream());
                    objectOutput.writeObject(blocks);
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }else{
                sendstring("error");

                try {
                    skt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(readLine.startsWith("!M!")){

            SharedVar.otherMousepos = tempVector.set(Integer.parseInt(readLine.split("t")[0].replace("!M!","")),Integer.parseInt(readLine.split("t")[1].replace("t","")));


        }else if(readLine.startsWith("!BM!")) {
            ProjectManager.getActProjectVar().blocks.get(Integer.parseInt(readLine.split("t")[0].replace("!BM!",""))).setX(Integer.parseInt(readLine.split("t")[1].replace("t","")));
            ProjectManager.getActProjectVar().blocks.get(Integer.parseInt(readLine.split("t")[0].replace("!BM!",""))).setY(Integer.parseInt(readLine.split("t")[2].replace("t","")));
        }else if(readLine.startsWith("!BNL!")) {
            if(Integer.parseInt(readLine.split("t")[1].replace("t",""))!=-1) {
                ProjectManager.getActProjectVar().blocks.get(Integer.parseInt(readLine.split("t")[0].replace("!BNL!",""))).setLeft(ProjectManager.getActProjectVar().blocks.get(Integer.parseInt(readLine.split("t")[1].replace("t",""))));

            }else {
                ProjectManager.getActProjectVar().blocks.get(Integer.parseInt(readLine.split("t")[0].replace("!BNL!", ""))).setLeft(null);
            }
        }else if(readLine.startsWith("!BNR!")) {
            if(Integer.parseInt(readLine.split("t")[1].replace("t",""))!=-1) {
                ProjectManager.getActProjectVar().blocks.get(Integer.parseInt(readLine.split("t")[0].replace("!BNR!",""))).setRight(ProjectManager.getActProjectVar().blocks.get(Integer.parseInt(readLine.split("t")[1].replace("t",""))));

            }else {
                ProjectManager.getActProjectVar().blocks.get(Integer.parseInt(readLine.split("t")[0].replace("!BNR!", ""))).setRight(null);

            }
        }






    }


    private static int register(String register) {
        int responsecode = 0;


         if(!Var.PROGRAMM_VERSION.contains(register.split("\\|")[0].replace("\\","").replace("V","").replace("!R!",""))) {
             responsecode=1; //Der Client hat eine andere Version als der Server!
         }

        String[] möglichkeiten = {"Zulassen", "Ablehnen"};


        final int zulassen = 1;
        final int ablehenn = 2;

        final int[] newresone = {0};
        //confirmdialog may return result of any type, here we are just using ints
        Dialogs.showConfirmDialog(UI.stage, "Verbindungsanfrage", "\nDer Benutzer "+register.split("\\|")[1].replace("\\","").replace("U","").replace("\\|","")+" möchte mit an deinem Projekt Arbeiten.\nMöchtest du die Anfrage zulassen?"+"+\n",
                möglichkeiten, new Integer[]{zulassen, ablehenn},
                new ConfirmDialogListener<Integer>() {
                    @Override
                    public void result(Integer result) {
                        if (result == zulassen) {
                            newresone[0] = 1;

                        }

                        if (result == ablehenn) {

                            newresone[0] = 2;
                        }


                    }
                });


        while(newresone[0]==0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(newresone[0]==2) {
            responsecode = 2;
        }


        return responsecode;
    }

}


