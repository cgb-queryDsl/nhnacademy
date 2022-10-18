package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{
    Socket socket;
    DataOutputStream out;

    public Sender(Socket socket) {
        this.socket = socket;

        try {
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e){
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (out != null) {
            try {
                out.writeUTF(scanner.nextLine());
            } catch (IOException e){
            }
        }
    }
}
