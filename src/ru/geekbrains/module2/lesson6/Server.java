package ru.geekbrains.module2.lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Socket socket;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String newMessage;
        String messageToWrite;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Waiting for connection...");
            socket = serverSocket.accept();
            System.out.println("Connection established");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("Connected to server");
            while (true) {
                if (dataInputStream.available() != 0) {
                    newMessage = dataInputStream.readUTF();
                    System.out.println("Incoming: " + newMessage);
                }
                if (reader.ready()) {
                    messageToWrite = reader.readLine();
                    dataOutputStream.writeUTF(messageToWrite);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
