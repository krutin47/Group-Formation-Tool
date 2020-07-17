package CSCI5308.GroupFormationTool.Database;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.*;

public class CallQuery {
    private Connection connection;
    private Statement statement;
    private String query;

    public CallQuery(String query) throws SQLException
    {
        SystemConfig.instance().getLOG().info("In Constructor");
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
            SystemConfig.instance().getLOG().info("performing CleanUp");
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
            SystemConfig.instance().getLOG().error("Can not perform CleanUp");
        }
    }

    public ResultSet executeWithResults(String query) throws SQLException
    {
        SystemConfig.instance().getLOG().debug("checking parameter is not null ::" + query.isEmpty());
        if (statement.execute(query))
        {
            SystemConfig.instance().getLOG().info("Query Executed");
            return statement.getResultSet();
        }
        else {
            SystemConfig.instance().getLOG().warn("Not Executed");
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

