package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;

public interface IUserPersistence
{
	public void loadUserByID(long id, User user);
	public void loadUserByBannerID(String bannerID, User user);
	public boolean createUser(User user);
	public boolean updateUser(User user);
	public List<String> fetchOldPasswords(long id, int count);
	public boolean forgotPassword(String bannerID);
	public boolean resetPassword(long id, String newPassword, String _token);
}
