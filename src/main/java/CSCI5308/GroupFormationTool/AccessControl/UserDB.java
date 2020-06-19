package CSCI5308.GroupFormationTool.AccessControl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.apache.commons.lang3.RandomStringUtils;

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
			}
		} catch (SQLException e) {
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
	public boolean forgotPassword(String bannerID) {
		CallStoredProcedure proc = null;
//		IUserPersistence iUserPersistence = SystemConfig.instance().getUserDB();
		User user = new User();

		System.out.println("forgotPassword -->" + bannerID);
		try{
			if (user.getID() > 0) {
				String random = RandomStringUtils.randomAlphanumeric(10);
				proc = new CallStoredProcedure("spForgotPasswordUser(?,?)");
				proc.setParameter(1, user.getID());
				proc.setParameter(2, random);
				SystemConfig.instance().getMailUtil().sendmail(user.getEmail(), "password reset", "your password reset link is: localhost:8080/reset?_token=" + random + "&id=" + user.getID());
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

	@Override
	public boolean resetPassword(long id, String newPassword) {
		return false;
	}
}