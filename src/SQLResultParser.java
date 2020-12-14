public class SQLResultParser {
    public String createQuery(Result result) {
        String query = "";
        query = "INSERT INTO results VALUES (null, '" + result.getNick() + "', '" + result.getScore() + "', '" + result.getDate() +"');" ;
        return query;
    }

    public String createGetQuery(int i) {
        String query = "";
        query = "SELECT * FROM results ORDER BY results.score DESC LIMIT " + i + ", 1;";
        System.out.println(query);
        return query;
    }
}

