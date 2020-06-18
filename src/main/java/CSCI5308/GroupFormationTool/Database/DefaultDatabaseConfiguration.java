package CSCI5308.GroupFormationTool.Database;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration
{
	private static final String URL = "jdbc:mysql://db-5308.cs.dal.ca/CSCI5308_5_DEVINT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "CSCI5308_5_DEVINT_USER";
	private static final String PASSWORD = "CSCI5308_5_DEVINT_5209";

	public String getDatabaseUserName()
	{
		return USER;
	}

	public String getDatabasePassword()
	{
		return PASSWORD;
	}

	public String getDatabaseURL()
	{
		return URL;
	}
}
