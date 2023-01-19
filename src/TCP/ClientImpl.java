package TCP;

import java.io.IOException;
import java.net.Socket;
public class ClientImpl implements ClientInterface{
    private Socket clientSocket;

    @Override
    public Socket connectToServer(String host, int port) throws IOException, IllegalStateException {
        this.clientSocket = new Socket(host, port);
        return this.clientSocket;
    }

    @Override
    public Socket getClientSocket() throws IllegalStateException {
        if (this.clientSocket == null) {
            throw new IllegalStateException("There is no client Socket at the moment.");
        }
        return this.clientSocket;
    }
}
