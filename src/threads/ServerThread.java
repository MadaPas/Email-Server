package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private String name;
    private String host;
    private int port;
    private Socket clientSocket;

    public ServerThread(String name, String host, int port, Socket clientSocket) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        System.out.println( Thread.currentThread().getName()+ " is running.");
        System.out.println("-----------------------------");
        try {
            // Obtain input and output streams to communicate with client
            // Input stream of client is connected to output stream of server
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Type 'Disconnect' to terminate the connection.");

            while (!clientSocket.isClosed()) {
                System.out.println("Running... on port " + clientSocket.getRemoteSocketAddress() + ".");
                out.println("Client input: " + in.readLine());

                if(in.readLine().equalsIgnoreCase("Disconnect")) {
                    System.exit(1);
                    //clientSocket.close();
                }

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("Could not accept client connection at host: " + host + " - port: " + port);
        }
        System.out.println("Terminating...");
    }
}
