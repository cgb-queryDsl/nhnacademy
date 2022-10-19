package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver extends Thread{

    Socket socket;
//    DataInputStream in;
    BufferedReader in;

    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
        }
    }

    public void run() {
        while (in != null) {
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                System.exit(1);
            }
        }
    }
}
