package client;

import server.Receiver;
import server.Sender;
import server.Server;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("This is Client");
        System.out.println("Usage: snc 127.0.0.1 [port]");
        System.out.println("=========================================");

        Scanner scanner = new Scanner(System.in);
        String temp [] = scanner.nextLine().split(" ");

        String command = temp[0];
        String ip = temp[1];
        int port = Integer.parseInt(temp[2]);

        if (command.equals("snc") && ip.equals("127.0.0.1")) {
            try {
                String severIp = "127.0.0.1";
                Socket socket = new Socket(severIp, port);

                Sender sender = new Sender(socket);
                Receiver receiver = new Receiver(socket);

                sender.start();
                receiver.start();
            } catch (ConnectException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
