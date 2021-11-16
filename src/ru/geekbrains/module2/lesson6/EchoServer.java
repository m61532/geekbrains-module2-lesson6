package ru.geekbrains.module2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        Socket socket = null;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Waiting for connection...");
            socket = serverSocket.accept();
            System.out.println("Connection established");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                final String message = dataInputStream.readUTF();
                if (message.startsWith("/end")) {
                    dataOutputStream.writeUTF("/end");
                    break;
                }
                dataOutputStream.writeUTF("Echo: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
