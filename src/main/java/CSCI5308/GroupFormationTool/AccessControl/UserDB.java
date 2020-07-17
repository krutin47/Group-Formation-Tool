package CSCI5308.GroupFormationTool.AccessControl;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class UserDB implements IUserPersistence {
	public void loadUserByID(long id, User user) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Logging needed.
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	public void loadUserByBannerID(String bannerID, User user) {
		CallStoredProcedure proc = null;
		long userID = -1;
		try {
			proc = new CallStoredProcedure("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					userID = results.getLong(1);
				}
				System.out.println("results ---> " + results.getRow());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Logging needed.
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		// If we found the ID load the full details.
		if (userID > -1) {
			loadUserByID(userID, user);
		}
	}

	public boolean createUser(User user) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerID());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
		} catch (SQLException e) {
			// Logging needed
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	public boolean updateUser(User user) {
		// Coming in M2!
		return false;
	}

	@Override
	public List<String> fetchOldPasswords(long id, int count) {
		CallStoredProcedure proc = null;
		List<String> oldPasswords = new ArrayList<>(count);
		try
		{
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
			}
		}
		catch (SQLException e)
		{
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return oldPasswords;
	}

	@Override
	public boolean forgotPassword(String bannerID) {
		CallStoredProcedure proc = null;
		IUserPersistence iUserPersistence = SystemConfig.instance().getUserDB();
		synchronized(this) {
			User user = new User(bannerID, iUserPersistence);
			System.out.println("forgotPassword --> " + bannerID);
			System.out.println("USER ----> " + user);
			System.out.println("USER.ID ----> " + user.getID());
			try{
				if (user.getID() > -1) {
					String random = RandomStringUtils.randomAlphanumeric(10);
					System.out.println("random String ----> " + random);
					proc = new CallStoredProcedure("spForgotPasswordUser(?,?)");
					proc.setParameter(1, user.getID());
					proc.setParameter(2, random);
					ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
					builder.scheme("https");
					URI newUri = builder.build().toUri();
					SystemConfig.instance().getMailUtil().sendmail(user.getEmail(), "password reset", "your password reset link is: " + newUri.getScheme() + "://" + newUri.getAuthority() + "/reset?_token=" + random + "&id=" + user.getID());
					proc.execute();
					return true;
				}
			} catch (SQLException | IOException | MessagingException e) {
				e.printStackTrace();
				return false;
			} finally {
				if (null != proc)
				{
					proc.cleanup();
				}
			}
			return false;
		}
	}

	@Override
	public boolean resetPassword(long id, String newPassword, String _token) {
		CallStoredProcedure proc = null;
		IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
		try{
			proc = new CallStoredProcedure("spFetchToken(?)");
			proc.setParameter(1, id);
			ResultSet resultSet = proc.executeWithResults();
			if (null != resultSet) {
				while (resultSet.next()) {
					if (resultSet.getString(1).equals(_token)){
						CallStoredProcedure Sproc = new CallStoredProcedure("spResetPassword(?,?)");
						Sproc.setParameter(1, id);
						Sproc.setParameter(2, passwordEncryption.encryptPassword(newPassword));
						Sproc.execute();
					} else {
						return false;
					}
				}
				System.out.println("results ---> " + resultSet.getRow());
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}
}