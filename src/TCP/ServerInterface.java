package TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public interface ServerInterface extends Runnable {
    /**
     * server offers a connection, where clients can connect
     * @return Socket for connecting clients
     * @throws IllegalArgumentException - if port doesn't exists (zB. bei port '-1')
     * @throws IllegalStateException - port is already occupied
     */
    Socket offerConnection() throws IllegalStateException, IOException;

    /**
     * adds socket to socketList
     * @param socket
     * @throws IllegalArgumentException - if socket is NULL
     */
    void addConnectionsToListOfConnections (Socket socket) throws IllegalArgumentException;

    /**
     * delets socket from socketList
     * @param socket
     * @throws IllegalArgumentException - if that socket doesn't exists in socketList
     */
    void deleteConnectionFromListOfConnections (Socket socket) throws IllegalArgumentException;

    /**
     * Get method for Server Socket. if doesn't exist yet, gets created
     * @return ServerSocket
     */
    ServerSocket getServerSocket();

    /**
     * returns current connection
     * @return Socket, die aktuellste Verbindung
     * @throws IllegalStateException Wenn noch keine Verbindungen eingegangen sind
     */
    Socket getLastestConnection() throws IllegalStateException;

    /**
     * get method for list of connections
     * @return list of connections
     */
    ArrayList<Socket> getListOfConnections();
}