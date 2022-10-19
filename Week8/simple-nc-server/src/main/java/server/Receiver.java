package server;

import java.io.*;
import java.net.Socket;

public class Receiver extends Thread{

    Socket socket;
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
