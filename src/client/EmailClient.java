package client;


import threads.ClientThread;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EmailClient {

    private static final Scanner scn = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        System.out.print("Specify which host to connect to: ");
        String host = scn.nextLine();

        int port = 4444;

        // creating new socket when server has accepted the connection
        Socket clientSocket = new Socket(host, port);

        ClientThread clientThread = new ClientThread("ClientThread a", clientSocket);
        Thread thread = new Thread(clientThread);
        thread.start();




    }
}
