package org.sysinfo.morpion.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMorpion {
    private static final int port = 2048;
    private static List<BufferedReader> clientsIn = new ArrayList<>();
    private static List<PrintWriter> clientsOut = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur start");

            for (int i = 0; i < 2; i++) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Player " + (i + 1) + " connected.");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                clientsIn.add(in);
                clientsOut.add(out);

                out.println("Player " + (i + 1));
                out.println("Waiting other player...");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
