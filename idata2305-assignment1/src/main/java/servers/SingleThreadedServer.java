package servers;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

import computation.SearchSimulator;

public class SingleThreadedServer implements Runnable {

    protected String serverType = "Singlethreaded Server";

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public SingleThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {

        openServerSocket();

        while (!isStopped()) {
            // wait for a connection
            // on receiving a request, execute the heavy computation
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                SearchSimulator.processClientRequest(clientSocket, serverType);
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Server Stopped");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        // implementation to stop the server from the main thread if needed
        this.isStopped = true;
    }

    private void openServerSocket() {
        // open server socket here
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Can´t open port: " + serverPort, e);
        }
    }
}