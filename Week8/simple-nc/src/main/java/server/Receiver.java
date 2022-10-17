package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread{

    Socket socket;
    DataInputStream in;

    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
        }
    }

    public void run() {
        while (in != null) {
            try {
                System.out.println(in.readUTF());
            } catch (IOException e) {
            }
        }
    }
}
