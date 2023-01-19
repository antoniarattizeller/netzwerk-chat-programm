package TCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import UI.*;

public class ClientMessageHandlerImpl implements ClientMessageHandlerInterface {
    private Socket clientSocket;
    private UserInterface ui = new UserInterfaceImpl();

    public ClientMessageHandlerImpl(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        DataInputStream dataInputStream = null;

        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            System.out.println("error creating client input stream from client Message Handler: " + e.getMessage());
        }
        while (true) {
            try {
                String clientName = dataInputStream.readUTF();
                String clientMessage = dataInputStream.readUTF();
                ui.printChatMessage(clientName, clientMessage);
            } catch (IOException e) {
                System.out.println("error reading from client socket: " + e.getMessage());
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    System.out.println("error closing the client socket: " + ex.getMessage());
                }
                break;
            }
        }
    }
}
