import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private final Socket socket;
    private Result result;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);

            String request;
            do {
                request = reader.readLine();
                if(request == null){
                    break;
                }
                else if (request.startsWith("GET")) {
                    doGet(request, writer);
                } else if (request.startsWith("PUT")) {
                    doPut(request, writer);
                }
            }while (request != null);
            System.out.println("Connection closed by server");
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception..." + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void doGet(String request, PrintWriter writer){
        System.out.println("[SERVER] GET request from client");
        System.out.println("[CLIENT]" + request);
        new Request(request, writer).start();
    }


    public void doPut(String request, PrintWriter writer){
        System.out.println("[SERVER] PUT request from client");
        System.out.println("[CLIENT]" + request);
        String[] req = request.split(" ");
        String nick = req[1];
        String score = req[2];
        String date = req[3];
        String time = req[4];
        result = new Result(nick, Integer.parseInt(score), date + " " + time);
        //sending data do db
        PutResults sendResult =new PutResults(result);
        sendResult.connect();

        //if all is correct send ok
        writer.println("OK");
    }
}
