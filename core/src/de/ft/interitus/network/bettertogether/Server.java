package de.ft.interitus.network.bettertogether;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;
    public static void start() throws IOException {
        server = new ServerSocket(3141);

        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
                BufferedReader rein = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                PrintStream raus = new PrintStream(socket.getOutputStream());
                String s;

                while(rein.ready()) {
                    s = rein.readLine();
                    raus.println(s);
                }
            }

            catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }

    }

}
