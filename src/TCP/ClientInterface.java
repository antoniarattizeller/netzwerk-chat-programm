package TCP;

import java.io.IOException;
import java.net.Socket;

public interface ClientInterface {
    /**
     * Konstruktor - create connection to server
     * @param host adress of server
     * @param port port from server
     * @throws IllegalArgumentException - negative port
     * @throws IOException
     * @throws IllegalStateException - no server with this
     */
    Socket connectToServer(String host, int port) throws IllegalArgumentException, IOException, IllegalStateException;

    /**
     * gives socket of the client connection back
     * @return Socket from client connection
     * @exception IllegalStateException if ther doesn't exist a client socket
     */
    Socket getClientSocket() throws IllegalStateException;
}