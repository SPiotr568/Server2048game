import java.io.PrintWriter;

public class Request extends Thread {
    private String request;
    private PrintWriter writer;

    public Request(String request, PrintWriter writer) {
        this.request = request;
        this.writer = writer;
    }

    @Override
    public void run() {
        //sending request do db


        //and then sending response to client
        writer.println("[SERVEER] "+request);

    }
}
