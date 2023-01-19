package TCP;

import UI.UserInterface;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import UI.*;

import static java.lang.Thread.sleep;

public class MessageHandler implements MessageHandlerInterface, Runnable {
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    ServerInterface server;
    UserInterface ui = new UserInterfaceImpl();
    int readerThreadCounter = 0;

    public MessageHandler(ServerInterface server) {
        this.server = server;
    }

    public MessageHandler() {
        this(null);
    }

    @Override
    public void sendMessage(Socket socket, String name, String message) throws IOException {
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.dataOutputStream.writeUTF(name);
        this.dataOutputStream.writeUTF(message);
    }

    @Override
    public String[] receiveMessage() throws IOException {
        return new String[0];
    }

    @Override
    public void sendMessageToAllOtherClients(Socket originSocket, String name, String message) throws IOException {
        for (Socket receiverSocket : server.getListOfConnections()) {
            if (receiverSocket != originSocket) {
                this.sendMessage(receiverSocket, name, message);
            }
        }
    }

    @Override
    public void run() {
        this.readerThreadCounter++;
        System.out.println("MessageReaderThread Nr. " + this.readerThreadCounter + " started.");
        //get current connection
        Socket clientInputSocket = server.getLastestConnection();
        DataInputStream myDataInputStream = null;
        try {
            //get Inputstream from current connection
            myDataInputStream = new DataInputStream(clientInputSocket.getInputStream());
        } catch (IOException e) {
            System.out.println("error in Threaded Message Handler: " + e.getMessage());
        }

        while (true) {
            try {
                String clientName = myDataInputStream.readUTF();
                String clientMessage = myDataInputStream.readUTF();

                ui.printChatMessage(clientName, clientMessage);
                this.sendMessageToAllOtherClients(clientInputSocket, clientName, clientMessage);
            } catch (IOException e) {
                System.out.println("error in Message Reader Thread Nr.  " + this.readerThreadCounter + ": " + e.getMessage());
                server.deleteConnectionFromListOfConnections(clientInputSocket);
                try {
                    clientInputSocket.close();
                } catch (IOException ex) {
                    System.out.println("Error closing from socket in Thread Nr. " + this.readerThreadCounter);
                }
                break;
            }

        }
    }
}