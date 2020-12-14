import java.sql.*;

public class GetResult {
    private String dbName = "game2048";
    private String url = "jdbc:mysql://localhost:3306/" + dbName;
    private String userName = "root";
    private String password = "";
    private Connection connection;
    private Statement statement;
    private String query;
    private SQLResultParser sqlResultParser;
    private int i;
    private Result result;

    public GetResult(int i, Result result) {
        this.i = i;
        this.result =result;
    }


    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url,userName,password);
            System.out.println("Connected to db: " + dbName);
            sqlResultParser = new SQLResultParser();
            query = sqlResultParser.createGetQuery(i);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                String nick = rs.getString("nick");
                int score = rs.getInt("score");
                String date = rs.getString("date");
                result.setNick(nick);
                result.setScore(score);
                result.setDate(date);
                System.out.println(nick+score+date);
            }
            rs.close();
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
