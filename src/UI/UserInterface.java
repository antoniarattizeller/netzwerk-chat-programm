package UI;

import java.io.IOException;

public interface UserInterface {
    /**
     * prints welcomeMessage
     */
    void welcomeMessage();

    /**
     * ask for username
     * @return name of user
     */
    String askForNickname();

    /**
     * ask if user wants to be server
     * @return boolean, whether user wants to be server or not
     */
    boolean askIfServer();

    /**
     * gives message
     * @param name from person who sends
     * @param message that gets send
     * @throws IllegalArgumentException - if at least one argument is NULL
     */
    void printChatMessage(String name, String message) throws IllegalArgumentException;

    /**
     * reads what message the user wants to send (Command Handler)
     * @return message to be sent
     */
    String readMessageToBeSent();

    /**
     * reads input from user
     * @return
     * @throws IOException
     */
    String userInputReader() throws IOException;
}