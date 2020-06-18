package CSCI5308.GroupFormationTool.Database;
import java.sql.*;

public class CallQuery {

    private Connection connection;
    private Statement statement;
    private String query;


    public CallQuery(String query) throws SQLException
    {
        this.query = query;
        connection = null;
        statement = null;
        openConnection();
        createStatement();
    }
    private void createStatement() throws SQLException
    {
        statement = connection.createStatement();
    }

    private void openConnection() throws SQLException
    {
        connection = ConnectionManager.instance().getDBConnection();
    }

    public void cleanup()
    {
        try
        {
            if (null != statement)
            {
                statement.close();
            }
            if (null != connection)
            {
                if (!connection.isClosed())
                {
                    connection.close();
                }
            }
        }
        catch (Exception e)
        {
            // Logging needed.
        }
    }


    public ResultSet executeWithResults(String query) throws SQLException
    {
        if (statement.execute(query))
        {
            System.out.println("Executed");
            return statement.getResultSet();
        }
        else {
            System.out.println("Not Executed");

        }
        return null;
    }


    public void execute(String query) throws SQLException
    {
        statement.execute(query);
    }
    public void executeUpdate(String query) throws SQLException
    {
        statement.executeUpdate(query);

    }

}


