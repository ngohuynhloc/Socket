package Bai1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8000);
            System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage, clientMessage;

            while (true) {
                System.out.print("Client: ");
                clientMessage = userInput.readLine();
                out.println(clientMessage);

                serverMessage = in.readLine();
                if (serverMessage != null) {
                    System.out.println("Server: " + serverMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
