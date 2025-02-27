package defalt.demo.network;

import defalt.demo.game.Grid;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMorpion {


    public static void main(String[] args) throws IOException, ClassNotFoundException {final int port = 2048;
        Grid grid = new Grid();
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

        player1Out.writeObject(grid.getGrid());
        player1Out.flush();

        player2Out.writeObject(grid.getGrid());
        player2Out.flush();

        boolean inGame = true;
        while (inGame) {
            System.out.println("Waiting for Player 1's move...");
            byte[][] gridPlayer1 = (byte[][]) player1In.readObject();
            grid.setGrid(gridPlayer1);
            player2Out.writeObject(grid.getGrid());
            player2Out.flush();
            if(grid.isWin()) {
                break;
            }

            System.out.println("Waiting for Player 2's move...");
            byte[][] gridPlayer2 = (byte[][]) player2In.readObject();
            grid.setGrid(gridPlayer2);
            player1Out.writeObject(grid.getGrid());
            player1Out.flush();
            if(grid.isWin()) {
                break;
            }
        }

        player1Socket.close();
        player2Socket.close();
        serverSocket.close();
    }
}
