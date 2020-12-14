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
        Result result= new Result("",0,"");
        String[] req = request.split(" ");
        String i = req[1];
        GetResult getResult =new GetResult(Integer.parseInt(i), result);
        getResult.connect();
        //and then sending response to client
        if(result.getNick()==""){
            writer.println("");
        }
        else{
            writer.println(result.getNick()+" "+result.getScore()+" "+result.getDate());
        }
    }
}
