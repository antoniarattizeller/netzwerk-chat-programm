package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class UserInterfaceImpl implements UserInterface {
    @Override
    public void welcomeMessage() {
        System.out.println("Welcome to the group chat!");
    }

    @Override
    public String askForNickname() {
        System.out.print("Enter your nickname: ");
        String nickname = userInputReader();
        System.out.println("Welcome " + nickname + "!");
        return nickname;
    }

    @Override
    public boolean askIfServer() {
        boolean server = false;
        boolean notDecided = true;
        while (notDecided) {
            System.out.println("Do you want to be the Server? (y/n)");
            String answer = userInputReader();
            switch (answer) {
                case "y", "Y":
                    server = true;
                    notDecided = false;
                    System.out.println("You are the Server!");
                    break;
                case "n", "N":
                    server = false;
                    notDecided = false;
                    System.out.println("You are not the Server.");
                    break;
                default:
                    server = false;
                    notDecided = true;
                    break;
            }
        }
        return server;
    }

    @Override
    public void printChatMessage(String name, String message) throws IllegalArgumentException, IllegalStateException {
        if (name == null || message == null) {
            throw new IllegalArgumentException("something is NULL");
        }
        System.out.println(name + ": " + message);
    }

    @Override
    public String readMessageToBeSent() {
        //TODO: spaeter Protokoll reinbauen
        return userInputReader();
    }

    @Override
    public String userInputReader() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputMessage = null;
        try {
            inputMessage = reader.readLine();
        } catch (IOException e) {
            System.out.println("error while input reading: " + e.getMessage());
        }
        return inputMessage;
    }
}