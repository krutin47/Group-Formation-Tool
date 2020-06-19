package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;

import java.util.*;

public class UserDBMock implements IUserPersistence
{
	public void loadUserByID(long id, User user)
	{
		user.setID(id);
		user.setBannerID("B00000000");
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey@dal.ca");
	}

	public void loadUserByBannerID(String bannerID, User user)
	{
		user.setID(1);
		user.setBannerID(bannerID);
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey@dal.ca");
	}
	
	public boolean createUser(User user)
	{
		user.setID(0);
		user.setBannerID("B00000000");
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey@dal.ca");
		return true;
	}
	
	public boolean updateUser(User user)
	{
		user.setID(0);
		user.setBannerID("B00000000");
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey@dal.ca");
		return true;
	}

	@Override
	public List<String> fetchOldPasswords(long id, int count) {
		List<String> list = new ArrayList<>();
		list.add("password");
		return list;
	}
}
