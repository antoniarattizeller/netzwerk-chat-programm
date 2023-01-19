package TCP;

import java.io.IOException;
import java.net.Socket;

public interface MessageHandlerInterface extends Runnable {
    /**
     * sends messages to all the members of the chat
     * @param message to send
     * @throws IOException - connection problems
     */
    void sendMessage(Socket socket, String name, String message) throws IOException;

    /**
     * reads a message
     * @return String[0] == name, String[1] == message
     * @throws IOException
     */
    String[] receiveMessage() throws IOException;

    /**
     * sends message to all connection except to the sending client
     * @param originSocket - connection where message comes from
     * @param name - client who sends message
     * @param message = message to send
     * @throws IOException
     */
    void sendMessageToAllOtherClients(Socket originSocket, String name, String message) throws IOException;
}