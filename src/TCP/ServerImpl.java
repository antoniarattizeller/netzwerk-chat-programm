package TCP;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerImpl implements ServerInterface, Runnable {
    private ServerSocket serverSocket;
    private int port;
    ArrayList<Socket> listOfConnections = new ArrayList<Socket>();

    public ServerImpl(int port) {
        if(port < 1 || port > 65535) {
            throw new IllegalArgumentException("Port out of range!");
        }
        this.port = port;
    }

    @Override
    public Socket offerConnection() throws IllegalStateException, IOException {
        ServerSocket serverSocket = this.getServerSocket();
        Socket socket = serverSocket.accept();
        return socket;
    }

    @Override
    public void addConnectionsToListOfConnections(Socket socket) throws IllegalArgumentException {
        if (!listOfConnections.contains(socket)) {
            listOfConnections.add(socket);
        }
    }

    @Override
    public void deleteConnectionFromListOfConnections(Socket socket) throws IllegalArgumentException {
        listOfConnections.remove(socket);
    }

    @Override
    public ServerSocket getServerSocket() {
        if (this.serverSocket == null) {
            try {
                this.serverSocket = new ServerSocket(this.port);
            } catch (IOException e) {
                System.out.println("error in getServerSocket: " + e.getMessage());
            }
        }
        return this.serverSocket;
    }

    @Override
    public Socket getLastestConnection() throws IllegalStateException {
        if (this.listOfConnections.isEmpty()) {
            throw new IllegalStateException("No connection yet!");
        }
        return listOfConnections.get(listOfConnections.size() - 1);
    }

    @Override
    public ArrayList<Socket> getListOfConnections() {
        return this.listOfConnections;
    }

    @Override
    public void run() {
        MessageHandlerInterface messageHandler = new MessageHandler(this);
        while (true) {
            try {
                //accept new connection
                Socket newConnection = offerConnection();
                //add new connection to listOfConnections
                addConnectionsToListOfConnections(newConnection);
                System.out.println("new Connection!");
                //STARTS Threads to print messages from that connection
                Thread readClientMessagesThread = new Thread(messageHandler);
                readClientMessagesThread.start();
            } catch (IOException e) {
                System.out.println("Error in serverThread " + e.getMessage());
            }

        }
    }
}