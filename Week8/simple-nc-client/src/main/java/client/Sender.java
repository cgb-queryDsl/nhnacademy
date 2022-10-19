package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{
    Socket socket;
    PrintWriter printWriter;

    public Sender(Socket socket) {
        this.socket = socket;

        try {
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (Exception e){
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (printWriter != null) {
            printWriter.println(scanner.nextLine());
            printWriter.flush();
        }
    }
}
