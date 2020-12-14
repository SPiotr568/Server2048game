import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {

        int port = 2761;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Starting server...\n [SERVER]listening on port: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new ServerThread(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception... " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
