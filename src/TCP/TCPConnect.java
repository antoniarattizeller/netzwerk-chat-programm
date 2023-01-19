package TCP;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

//Client
public class TCPConnect {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);

        DataOutputStream daos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dais = new DataInputStream(socket.getInputStream());
        daos.writeInt(5);
        System.out.println("Nachricht: " + dais.readInt());
    }
}


