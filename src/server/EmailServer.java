package server;
import threads.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EmailServer {

    private static final String HOST = "10.111.168.138";
    private static final int PORT = 4444;

    public static void main(String[] args) throws IOException {


        // the server is running on the computer with the socket bound to the specified specific port
        ServerSocket serverSocket = new ServerSocket(PORT);

        // the server is listening to the socket to check if any client has made a connection request yet
        Socket clientSocket = serverSocket.accept();

        ServerThread serverThread = new ServerThread("ServerThread 1", HOST, PORT, clientSocket);
        Thread thread = new Thread(serverThread);
        thread.start();

    }

}
