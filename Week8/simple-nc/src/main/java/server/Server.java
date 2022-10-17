package server;

import sun.misc.Signal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("This is Server");
        System.out.println("Usage: snc [option] [hostname] [port]");
        System.out.println("Options: ");
        System.out.println("-l  <port>");
        System.out.println("=========================================");

        Scanner scanner = new Scanner(System.in);
        String temp[] = scanner.nextLine().split(" ");

        String command = temp[0];
        String option = temp[1];
        int port = Integer.parseInt(temp[2]);

        if (command.equals("snc") && option.equals("-l")) {
            ServerSocket serverSocket = null;
            Socket socket = null;

            try {
                serverSocket = new ServerSocket(port);

                socket = serverSocket.accept();

                Sender sender = new Sender(socket);
                Receiver receiver = new Receiver(socket);

                sender.start();
                receiver.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

