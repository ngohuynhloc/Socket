package Bai2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockClient extends JFrame {
    private JLabel timeLabel;
    private Timer timer;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClockClient() {
        setTitle("Clock Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(timeLabel, BorderLayout.CENTER);

        try {
            socket = new Socket("localhost", 10000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    out.println("time");
                    try {
                        String time = in.readLine();
                        timeLabel.setText(time);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            timer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
            
                new ClockClient().setVisible(true);
            }
}
