package ru.geekbrains.module2.lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String newMessage = null;
        String messageToWrite = null;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Waiting for connection...");
            socket = serverSocket.accept();
            System.out.println("Connection established");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("Connected to server");
            while (true) {
                newMessage = dataInputStream.readUTF();
                if (!newMessage.equals("nullMessage")) {
                    System.out.println("Incoming: " + newMessage);
                }
                newMessage = "nullMessage";
                if (reader.ready()) {
                    newMessage = reader.readLine();
                }
                dataOutputStream.writeUTF(newMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
