package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;

public class UserDB implements IUserPersistence
{
	private Logger LOG;

	public void loadUserByID(long id, User user)
	{
		LOG = SystemConfig.instance().getLOG();
		LOG.info("In loadUserByID method");
		CallStoredProcedure proc = null;
		try
		{
			LOG.info("calling stored Procedure");
			proc = new CallStoredProcedure("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
				}
				LOG.debug("Fetched ResultSet records :: " + results.getRow());
			}
		}
		catch (SQLException e)
		{
			LOG.error("Error in sql query", e);
		}
		finally
		{
			if (null != proc)
			{
				LOG.info("cleaning up the resources.");
				proc.cleanup();
			}
		}
	}

	public void loadUserByBannerID(String bannerID, User user)
	{
		LOG = SystemConfig.instance().getLOG();
		LOG.info("In loadUserByBannerID method");
		CallStoredProcedure proc = null;
		long userID = -1;
		try
		{
			LOG.info("calling stored Procedure");
			proc = new CallStoredProcedure("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					userID = results.getLong(1);
				}
				LOG.debug("Fetched ResultSet records :: " + results.getRow());
			}
		}
		catch (SQLException e)
		{
			LOG.error("Error in sql query", e);
		}
		finally
		{
			if (null != proc)
			{
				LOG.info("cleaning up the resources.");
				proc.cleanup();
			}
		}
		// If we found the ID load the full details.
		if (userID > -1)
		{
			LOG.info("Calling the loadUserByID method to Load the User");
			loadUserByID(userID, user);
		}
	}
	
	public boolean createUser(User user)
	{
		LOG = SystemConfig.instance().getLOG();
		LOG.info("In createUser method");
		CallStoredProcedure proc = null;
		try
		{
			LOG.info("calling stored Procedure");
			proc = new CallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerID());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
		}
		catch (SQLException e)
		{
			LOG.error("Error in sql query", e);
			return false;
		}
		finally
		{
			if (null != proc)
			{
				LOG.info("cleaning up the resources.");
				proc.cleanup();
			}
		}
		return true;
	}
	
	public boolean updateUser(User user)
	{
		// Coming in M2!
		return false;
	}

	@Override
	public List<String> fetchOldPasswords(long id, int count) {
		LOG = SystemConfig.instance().getLOG();
		LOG.info("In fetchOldPasswords method");

		CallStoredProcedure proc = null;
		List<String> oldPasswords = new ArrayList<>(count);
		try
		{
			LOG.info("calling stored Procedure");
			proc = new CallStoredProcedure("spFetchOldPassword(?, ?)");
			proc.setParameter(1, id);
			proc.setParameter(2, count);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					 oldPasswords.add(results.getString(1));
				}
				LOG.debug("Fetched ResultSet records :: " + results.getRow());
			}
		}
		catch (SQLException e)
		{
			LOG.error("Error in sql query", e);
		}
		finally
		{
			if (null != proc)
			{
				LOG.info("cleaning up the resources.");
				proc.cleanup();
			}
		}
		return oldPasswords;
	}
}
