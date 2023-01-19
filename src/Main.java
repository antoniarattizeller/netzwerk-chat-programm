import UI.UserInterface;
import UI.UserInterfaceImpl;
import TCP.*;

import java.io.IOException;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        //create new Object vom Typ UserInterface
        UserInterface ui = new UserInterfaceImpl();

        ui.welcomeMessage();
        //ask for nickname with the method and save it in a String
        String nickname = ui.askForNickname();
        //ask if user wants to be the Server and save it in a boolean
        boolean userWantsToBeServer = ui.askIfServer();
        //IF (user is server) THEN...
        //...create a new object from ServerInterface (using the constructor) with port 5000
        //...create a new Thread for the 'server'
        //...start the 'serverThread' - process
        if (userWantsToBeServer) {
            ServerInterface server = new ServerImpl(5000);
            Thread serverThread = new Thread(server);
            serverThread.start();

        } //ELSE (user is a client) create a new object from type ClientInterface
        else {
            ClientInterface client = new ClientImpl();
            //TRY...
            //...create new Socket object (using constructor) with host: "localhost" and port:" 5000
            //...create a new object from type MessageHandlerInterface
            //...create a new object from type ClientMessageHandlerInterface for the 'clientSocket'
            //...create a new Thread for 'clientMessageHandler'
            //...start Thread
            try {
                Socket clientSocket = client.connectToServer("localhost", 5000);
                MessageHandlerInterface messageHandler = new MessageHandler();
                ClientMessageHandlerInterface clientMessageHandler = new ClientMessageHandlerImpl(clientSocket);
                Thread clientMessageHandlerThread = new Thread(clientMessageHandler);
                clientMessageHandlerThread.start();
                //WHILE-LOOP for sending messages
                while (true) {
                    messageHandler.sendMessage(clientSocket, nickname, ui.readMessageToBeSent());
                }
              //CATCH IOException
            } catch (IOException e) {
                System.out.println("error connecting: " + e.getMessage());
            }
        }
    }
}