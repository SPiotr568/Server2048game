import java.sql.*;

public class PutResults {

    private String dbName = "game2048";
    private String url = "jdbc:mysql://localhost:3306/" + dbName;
    private String userName = "root";
    private String password = "";
    private Connection connection;
    private Statement statement;
    private String query;
    private SQLResultParser sqlResultParser;
    private Result result;

    public PutResults(Result result) {
        this.result = result;
    }

    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url,userName,password);
            System.out.println("Connected to db: " + dbName);
            sqlResultParser = new SQLResultParser();
            query = sqlResultParser.createQuery(result);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            if (connection != null)
                connection.close();
            System.out.println("Connection closed !!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
