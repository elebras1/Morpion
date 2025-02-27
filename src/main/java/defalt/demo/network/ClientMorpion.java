package defalt.demo.network;

import javafx.application.Platform;
import javafx.scene.control.Button;
import java.io.*;
import java.net.Socket;

public class ClientMorpion {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private byte[][] grid;
    private Button[][] buttons;
    private boolean isPlayerTurn;
    private int playerId;

    public ClientMorpion(String host, int port, Button[][] buttons) {
        this.buttons = buttons;
        try {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            grid = (byte[][]) in.readObject();
            String confirmation = (String) in.readObject();
            if (!"OK".equals(confirmation)) {
                throw new IOException("Erreur lors de l'initialisation du jeu");
            }
            playerId = (int) in.readObject();
            updateUI();
            isPlayerTurn = true; // Premier joueur commence

            new Thread(this::listenForUpdates).start();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void playTurn(int row, int col) {
        if (!isPlayerTurn || grid[row][col] != 0) return;

        grid[row][col] = (byte) playerId;
        updateUI();
        sendMove();
        isPlayerTurn = false;
    }

    private void sendMove() {
        try {
            out.writeObject(grid);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenForUpdates() {
        try {
            while (true) {
                Object received = in.readObject();
                if (!(received instanceof byte[][])) {
                    throw new IOException("Données corrompues reçues");
                }
                grid = (byte[][]) received;
                isPlayerTurn = true;
                Platform.runLater(this::updateUI);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connexion perdue avec le serveur.");
            e.printStackTrace();
        }

    }

    private void updateUI() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                byte value = grid[i][j];
                String text = value == 1 ? "X" : value == 2 ? "O" : "";
                buttons[i][j].setText(text);
            }
        }
    }
}