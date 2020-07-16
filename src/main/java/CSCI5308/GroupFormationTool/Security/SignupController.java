package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Controller
public class SignupController
{
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";

	private IPasswordPolicies passwordPolicies = SystemConfig.instance().getPasswordPolicies();
	private Properties properties = SystemConfig.instance().getProperties();
	private final String minLengthActive = properties.getProperty("spring.passwordPolicy.minLengthActive");
	private final String maxLengthActive = properties.getProperty("spring.passwordPolicy.maxLengthActive");
	private final String minUppercaseActive = properties.getProperty("spring.passwordPolicy.minUppercaseActive");
	private final String minLowercaseActive = properties.getProperty("spring.passwordPolicy.minLowercaseActive");
	private final String minSpecialActive = properties.getProperty("spring.passwordPolicy.minSpecialActive");
	private final String notAllowedCharacterActive = properties.getProperty("spring.passwordPolicy.notAllowedCharacterActive");
	private final String minLength = properties.getProperty("spring.passwordPolicy.minLength");
	private final String maxLength = properties.getProperty("spring.passwordPolicy.maxLength");
	private final String minUppercase = properties.getProperty("spring.passwordPolicy.minUppercase");
	private final String minLowercase = properties.getProperty("spring.passwordPolicy.minLowercase");
	private final String minSpecial = properties.getProperty("spring.passwordPolicy.minSpecial");
	private final String notAllowedCharacter = properties.getProperty("spring.passwordPolicy.notAllowedCharacter");

	@GetMapping("/signup")
	public String displaySignup(Model model)
	{
		SystemConfig.instance().getLOG().info("In displaySignup method");
		SystemConfig.instance().getLOG().debug("check minLength is " + minLengthActive + " : " + minLength);
		SystemConfig.instance().getLOG().debug("check maxLength is " + maxLengthActive + " : " + maxLength);
		SystemConfig.instance().getLOG().debug("check minUppercase is " + minUppercaseActive + " : " + minUppercase);
		SystemConfig.instance().getLOG().debug("check minLowercase is " + minLowercaseActive + " : " + minLowercase);
		SystemConfig.instance().getLOG().debug("check minSpecial is " + minSpecialActive + " : " + minSpecial);
		SystemConfig.instance().getLOG().debug("check notAllowedCharacter is " + notAllowedCharacterActive + " : " + notAllowedCharacter);

		List<String> suggestions = new ArrayList<>(6);
		if (minLengthActive.equals("true")){ suggestions.add("Password must be more than " + minLength + " Letter long");}
		if (maxLengthActive.equals("true")){ suggestions.add("Password must be less than " + maxLength + " Letter long");}
		if (minUppercaseActive.equals("true")){ suggestions.add("Password must contain " + minUppercase + " Uppercase Letter");}
		if (minLowercaseActive.equals("true")){ suggestions.add("Password must contain " + minLowercase + " Lowercase Letter");}
		if (minSpecialActive.equals("true")){ suggestions.add("Password must contain " + minSpecial + " Special characters");}
		if (notAllowedCharacterActive.equals("true")){ suggestions.add("Password must not contain " + notAllowedCharacter);}

		model.addAttribute("hint",suggestions);

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView processSignup(
			@RequestParam(name = USERNAME) String bannerID,
			@RequestParam(name = PASSWORD) String password,
			@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
			@RequestParam(name = FIRST_NAME) String firstName,
			@RequestParam(name = LAST_NAME) String lastName,
			@RequestParam(name = EMAIL) String email)
	{
		boolean success = false;
		ModelAndView m;

		SystemConfig.instance().getLOG().info("In processSignup method");
		SystemConfig.instance().getLOG().debug("check minLength is " + minLengthActive + " : " + minLength);
		SystemConfig.instance().getLOG().debug("check maxLength is " + maxLengthActive + " : " + maxLength);
		SystemConfig.instance().getLOG().debug("check minUppercase is " + minUppercaseActive + " : " + minUppercase);
		SystemConfig.instance().getLOG().debug("check minLowercase is " + minLowercaseActive + " : " + minLowercase);
		SystemConfig.instance().getLOG().debug("check minSpecial is " + minSpecialActive + " : " + minSpecial);
		SystemConfig.instance().getLOG().debug("check notAllowedCharacter is " + notAllowedCharacterActive + " : " + notAllowedCharacter);

		if (minLengthActive.equals("true")){
			if(!passwordPolicies.passwordCheckMinLength(password, Integer.parseInt(minLength))){
				// Something wrong with the input data.
				SystemConfig.instance().getLOG().warn("Error in the Password input:: minLength not satisfied");

				m = new ModelAndView("signup");
				List<String> suggestions = new ArrayList<>(6);

				suggestions.add("Password must be more than " + minLength + " Letter long");
				if (maxLengthActive.equals("true")){ suggestions.add("Password must be less than " + maxLength + " Letter long");}
				if (minUppercaseActive.equals("true")){ suggestions.add("Password must contain " + minUppercase + " Uppercase Letter");}
				if (minLowercaseActive.equals("true")){ suggestions.add("Password must contain " + minLowercase + " Lowercase Letter");}
				if (minSpecialActive.equals("true")){ suggestions.add("Password must contain " + minSpecial + " Special characters");}
				if (notAllowedCharacterActive.equals("true")){ suggestions.add("Password must not contain " + notAllowedCharacter);}

				m.addObject("hint",suggestions);
				m.addObject("errorMessage", "Password must be more than " + minLength + " Letter long");

				return m;
			}
		}
		if (maxLengthActive.equals("true")){
			if(!passwordPolicies.passwordCheckMaxLength(password, Integer.parseInt(maxLength))){
				// Something wrong with the input data.
				SystemConfig.instance().getLOG().warn("Error in the Password input:: maxLength not satisfied");

				m = new ModelAndView("signup");
				List<String> suggestions = new ArrayList<>(6);

				if (minLengthActive.equals("true")){ suggestions.add("Password must be more than " + minLength + " Letter long");}
				suggestions.add("Password must be less than " + maxLength + " Letter long");
				if (minUppercaseActive.equals("true")){ suggestions.add("Password must contain " + minUppercase + " Uppercase Letter");}
				if (minLowercaseActive.equals("true")){ suggestions.add("Password must contain " + minLowercase + " Lowercase Letter");}
				if (minSpecialActive.equals("true")){ suggestions.add("Password must contain " + minSpecial + " Special characters");}
				if (notAllowedCharacterActive.equals("true")){ suggestions.add("Password must not contain " + notAllowedCharacter);}

				m.addObject("hint",suggestions);
				m.addObject("errorMessage", "Password must be less than " + maxLength + " Letter long");

				return m;
			}
		}
		if (minUppercaseActive.equals("true")){
			if(!passwordPolicies.passwordCheckMinUppercaseCharacter(password, Integer.parseInt(minUppercase))){
				// Something wrong with the input data.
				SystemConfig.instance().getLOG().warn("Error in the Password input:: minUppercase not satisfied");

				m = new ModelAndView("signup");
				List<String> suggestions = new ArrayList<>(6);

				if (minLengthActive.equals("true")){ suggestions.add("Password must be more than " + minLength + " Letter long");}
				if (maxLengthActive.equals("true")){ suggestions.add("Password must be less than " + maxLength + " Letter long");}
				suggestions.add("Password must contain " + minUppercase + " Uppercase Letter");
				if (minLowercaseActive.equals("true")){ suggestions.add("Password must contain " + minLowercase + " Lowercase Letter");}
				if (minSpecialActive.equals("true")){ suggestions.add("Password must contain " + minSpecial + " Special characters");}
				if (notAllowedCharacterActive.equals("true")){ suggestions.add("Password must not contain " + notAllowedCharacter);}

				m.addObject("hint",suggestions);
				m.addObject("errorMessage", "Password must contain " + minUppercase + " Uppercase Letter");

				return m;
			}
		}
		if (minLowercaseActive.equals("true")){
			if(!passwordPolicies.passwordCheckMinLowercaseCharacter(password, Integer.parseInt(minLowercase))){
				// Something wrong with the input data.
				SystemConfig.instance().getLOG().warn("Error in the Password input:: minLowercase not satisfied");

				m = new ModelAndView("signup");
				List<String> suggestions = new ArrayList<>(6);

				if (minLengthActive.equals("true")){ suggestions.add("Password must be more than " + minLength + " Letter long");}
				if (maxLengthActive.equals("true")){ suggestions.add("Password must be less than " + maxLength + " Letter long");}
				if (minUppercaseActive.equals("true")){ suggestions.add("Password must contain " + minUppercase + " Uppercase Letter");}
				suggestions.add("Password must contain " + minLowercase + " Lowercase Letter");
				if (minSpecialActive.equals("true")){ suggestions.add("Password must contain " + minSpecial + " Special characters");}
				if (notAllowedCharacterActive.equals("true")){ suggestions.add("Password must not contain " + notAllowedCharacter);}

				m.addObject("hint",suggestions);
				m.addObject("errorMessage", "Password must contain " + minLowercase + " Lowercase Letter");

				return m;
			}
		}
		if (minSpecialActive.equals("true")){
			if(!passwordPolicies.passwordCheckMinSpecialCharacter(password, Integer.parseInt(minSpecial))){
				// Something wrong with the input data.
				SystemConfig.instance().getLOG().warn("Error in the Password input:: minSpecialCharacter not satisfied");

				m = new ModelAndView("signup");
				List<String> suggestions = new ArrayList<>(6);

				if (minLengthActive.equals("true")){ suggestions.add("Password must be more than " + minLength + " Letter long");}
				if (maxLengthActive.equals("true")){ suggestions.add("Password must be less than " + maxLength + " Letter long");}
				if (minUppercaseActive.equals("true")){ suggestions.add("Password must contain " + minUppercase + " Uppercase Letter");}
				if (minLowercaseActive.equals("true")){ suggestions.add("Password must contain " + minLowercase + " Lowercase Letter");}
				suggestions.add("Password must contain " + minSpecial + " Special characters");
				if (notAllowedCharacterActive.equals("true")){ suggestions.add("Password must not contain " + notAllowedCharacter);}

				m.addObject("hint",suggestions);
				m.addObject("errorMessage", "Password must contain " + minSpecial + " Special characters");

				return m;
			}
		}
		if (notAllowedCharacterActive.equals("true")){
			if(passwordPolicies.passwordCheckNotAllowedCharacter(password, notAllowedCharacter.split(","))){
				// Something wrong with the input data.
				SystemConfig.instance().getLOG().warn("Error in the Password input :: Not allowed characters present");

				m = new ModelAndView("signup");
				List<String> suggestions = new ArrayList<>(6);

				if (minLengthActive.equals("true")){ suggestions.add("Password must be more than " + minLength + " Letter long");}
				if (maxLengthActive.equals("true")){ suggestions.add("Password must be less than " + maxLength + " Letter long");}
				if (minUppercaseActive.equals("true")){ suggestions.add("Password must contain " + minUppercase + " Uppercase Letter");}
				if (minLowercaseActive.equals("true")){ suggestions.add("Password must contain " + minLowercase + " Lowercase Letter");}
				if (minSpecialActive.equals("true")){ suggestions.add("Password must contain " + minSpecial + " Special characters");}
				suggestions.add("Password must not contain " + notAllowedCharacter);

				m.addObject("hint",suggestions);
				m.addObject("errorMessage", "Password must not contain " + notAllowedCharacter);

				return m;
			}
		}

		SystemConfig.instance().getLOG().info("No Error in the Password input");
		if (User.isBannerIDValid(bannerID) &&
				User.isEmailValid(email) &&
				User.isFirstNameValid(firstName) &&
				User.isLastNameValid(lastName) &&
				password.equals(passwordConfirm))
		{
			SystemConfig.instance().getLOG().info("Creating a new User");
			User u = new User();
			u.setBannerID(bannerID);
			u.setPassword(password);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setEmail(email);
			IUserPersistence userDB = SystemConfig.instance().getUserDB();
			IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
			success = u.createUser(userDB, passwordEncryption, null);
		}
		if (success)
		{
			SystemConfig.instance().getLOG().info("New User is Created");
			m = new ModelAndView("login");
		}
		else
		{
			// Something wrong with the input data.
			SystemConfig.instance().getLOG().warn("Error in the input data");

			m = new ModelAndView("signup");
			List<String> suggestions = new ArrayList<>(6);

			if (minLengthActive.equals("true")){ suggestions.add("Password must be more than " + minLength + " Letter long");}
			if (maxLengthActive.equals("true")){ suggestions.add("Password must be less than " + maxLength + " Letter long");}
			if (minUppercaseActive.equals("true")){ suggestions.add("Password must contain " + minUppercase + " Uppercase Letter");}
			if (minLowercaseActive.equals("true")){ suggestions.add("Password must contain " + minLowercase + " Lowercase Letter");}
			if (minSpecialActive.equals("true")){ suggestions.add("Password must contain " + minSpecial + " Special characters");}
			if (notAllowedCharacterActive.equals("true")){ suggestions.add("Password must not contain " + notAllowedCharacter);}

			m.addObject("hint",suggestions);
			m.addObject("errorMessage", "Invalid data, please check your values.");

			return m;
		}
		return m;
	}
}
