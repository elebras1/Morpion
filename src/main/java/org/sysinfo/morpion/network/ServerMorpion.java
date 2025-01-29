package org.sysinfo.morpion.network;

import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMorpion {
    private static final int port = 2048;
    private final Grid grid = new Grid();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started, waiting for players...");

        Socket player1Socket = serverSocket.accept();
        System.out.println("Player 1 connected.");
        Socket player2Socket = serverSocket.accept();
        System.out.println("Player 2 connected.");

        ObjectInputStream player1In = new ObjectInputStream(player1Socket.getInputStream());
        ObjectOutputStream player1Out = new ObjectOutputStream(player1Socket.getOutputStream());

        ObjectInputStream player2In = new ObjectInputStream(player2Socket.getInputStream());
        ObjectOutputStream player2Out = new ObjectOutputStream(player2Socket.getOutputStream());

        player1Out.writeObject(this.grid.getGrid());
        player1Out.flush();

        player2Out.writeObject(this.grid.getGrid());
        player2Out.flush();

        boolean inGame = true;
        while (inGame) {
            System.out.println("Waiting for Player 1's move...");
            byte[][] gridPlayer1 = (Grid) player1In.readObject();
            this.grid.set(gridPlayer1);
            player2Out.writeObject(this.grid.getGrid());
            player2Out.flush();
            if(this.grid.isWin()) {
                break;
            }

            System.out.println("Waiting for Player 2's move...");
            byte[][] gridPlayer2 = (Grid) player2In.readObject();
            this.grid.set(gridPlayer2);
            player1Out.writeObject(this.grid.getGrid());
            player1Out.flush();
            if(this.grid.isWin()) {
                break;
            }
        }

        player1Socket.close();
        player2Socket.close();
        serverSocket.close();
    }
}
