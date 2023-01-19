package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPOffer {
    public static void main(String[] args) throws IOException {
        ServerSocket serversocket = new ServerSocket(5000);
        Socket socket = serversocket.accept();

        DataOutputStream daos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dais = new DataInputStream(socket.getInputStream());
        daos.writeInt(5);
        System.out.println("Nachricht: " + dais.readInt());
    }
}
