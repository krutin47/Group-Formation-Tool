package CSCI5308.GroupFormationTool.AccessControl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import CSCI5308.GroupFormationTool.SystemConfig;

public class CurrentUser
{
	private static CurrentUser uniqueInstance = null;
	
	private CurrentUser()
	{
		
	}
	
	public static CurrentUser instance()
	{
		SystemConfig.instance().getLOG().debug("Current User instance :: " + uniqueInstance);
		if (null == uniqueInstance)
		{
			uniqueInstance = new CurrentUser();
		}
		return uniqueInstance;
	}
	
	public User getCurrentAuthenticatedUser()
	{
		SystemConfig.instance().getLOG().debug("In getCurrentAuthenticatedUser method");

		IUserPersistence userDB = SystemConfig.instance().getUserDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		SystemConfig.instance().getLOG().debug("Checking authentication :: " + authentication.isAuthenticated());
		if (authentication.isAuthenticated())
		{
			String bannerID = authentication.getPrincipal().toString();
			SystemConfig.instance().getLOG().debug("Checking authentication :: " + authentication.isAuthenticated());

			User u = new User();
			userDB.loadUserByBannerID(bannerID, u);

			SystemConfig.instance().getLOG().debug("Checking User validation :: " + u.isValidUser());
			if (u.isValidUser())
			{
				SystemConfig.instance().getLOG().debug("Valid User");
				return u;
			}
			SystemConfig.instance().getLOG().warn("User is not valid");
		}
		return null;
	}



}
