package Bai2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10000);
            System.out.println("Time server started. Waiting for a client...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage;

            while (true) {
                clientMessage = in.readLine();
                if (clientMessage != null) {
                    if (clientMessage.equalsIgnoreCase("time")) {
                        LocalDateTime now = LocalDateTime.now();
                        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                        out.println(formattedTime);
                        System.out.println("Sent time: " + formattedTime);
                    } else {
                        System.out.println("Received: " + clientMessage);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
