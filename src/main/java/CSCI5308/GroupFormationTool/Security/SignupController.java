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

	@GetMapping("/signup")
	public String displaySignup(Model model)
	{
		Properties properties = SystemConfig.instance().getProperties();
		String minLengthActive = properties.getProperty("spring.passwordPolicy.minLengthActive");
		String maxLengthActive = properties.getProperty("spring.passwordPolicy.maxLengthActive");
		String minUppercaseActive = properties.getProperty("spring.passwordPolicy.minUppercaseActive");
		String minLowercaseActive = properties.getProperty("spring.passwordPolicy.minLowercaseActive");
		String minSpecialActive = properties.getProperty("spring.passwordPolicy.minSpecialActive");
		String notAllowedCharacterActive = properties.getProperty("spring.passwordPolicy.notAllowedCharacterActive");
		String minLength = properties.getProperty("spring.passwordPolicy.minLength");
		String maxLength = properties.getProperty("spring.passwordPolicy.maxLength");
		String minUppercase = properties.getProperty("spring.passwordPolicy.minUppercase");
		String minLowercase = properties.getProperty("spring.passwordPolicy.minLowercase");
		String minSpecial = properties.getProperty("spring.passwordPolicy.minSpecial");
		String notAllowedCharacter = properties.getProperty("spring.passwordPolicy.notAllowedCharacter");
		List<String> suggesion = new ArrayList<>(6);
		if (minLengthActive.equals("true")){ suggesion.add("Password must be more than " + minLength + " Letter long");}
		if (maxLengthActive.equals("true")){ suggesion.add("Password must be less than " + maxLength + " Letter long");}
		if (minUppercaseActive.equals("true")){ suggesion.add("Password must contain " + minUppercase + " Uppercase Letter");}
		if (minLowercaseActive.equals("true")){ suggesion.add("Password must contain " + minLowercase + " Lowercase Letter");}
		if (minSpecialActive.equals("true")){ suggesion.add("Password must contain " + minSpecial + " Special characters");}
		if (notAllowedCharacterActive.equals("true")){ suggesion.add("Password must not contain " + notAllowedCharacter);}

		model.addAttribute("hint",suggesion);

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
		IPasswordPolicies passwordPolicies = SystemConfig.instance().getPasswordPolicies();
		Properties properties = SystemConfig.instance().getProperties();
		properties.getProperty("spring.passwordPolicy.minLength");
		String minLengthActive = properties.getProperty("spring.passwordPolicy.minLengthActive");
		String maxLengthActive = properties.getProperty("spring.passwordPolicy.maxLengthActive");
		String minUppercaseActive = properties.getProperty("spring.passwordPolicy.minUppercaseActive");
		String minLowercaseActive = properties.getProperty("spring.passwordPolicy.minLowercaseActive");
		String minSpecialActive = properties.getProperty("spring.passwordPolicy.minSpecialActive");
		String notAllowedCharacterActive = properties.getProperty("spring.passwordPolicy.notAllowedCharacterActive");
		String minLength = properties.getProperty("spring.passwordPolicy.minLength");
		String maxLength = properties.getProperty("spring.passwordPolicy.maxLength");
		String minUppercase = properties.getProperty("spring.passwordPolicy.minUppercase");
		String minLowercase = properties.getProperty("spring.passwordPolicy.minLowercase");
		String minSpecial = properties.getProperty("spring.passwordPolicy.minSpecial");
		String notAllowedCharacter = properties.getProperty("spring.passwordPolicy.notAllowedCharacter");
		if (minLengthActive.equals("true")){
			if(!passwordPolicies.passwordCheckMinLength(password, Integer.parseInt(minLength))){
				System.out.println("invalid");
				// Something wrong with the input data.
				m = new ModelAndView("signup");
				List<String> suggesion = new ArrayList<>(6);
				suggesion.add("Password must be more than " + minLength + " Letter long");
				if (maxLengthActive.equals("true")){ suggesion.add("Password must be less than " + maxLength + " Letter long");}
				if (minUppercaseActive.equals("true")){ suggesion.add("Password must contain " + minUppercase + " Uppercase Letter");}
				if (minLowercaseActive.equals("true")){ suggesion.add("Password must contain " + minLowercase + " Lowercase Letter");}
				if (minSpecialActive.equals("true")){ suggesion.add("Password must contain " + minSpecial + " Special characters");}
				if (notAllowedCharacterActive.equals("true")){ suggesion.add("Password must not contain " + notAllowedCharacter);}
				m.addObject("hint",suggesion);
				m.addObject("errorMessage", "Password must be more than " + minLength + " Letter long");
				return m;
			}
		}
		if (maxLengthActive.equals("true")){
			if(!passwordPolicies.passwordCheckMaxLength(password, Integer.parseInt(maxLength))){
				// Something wrong with the input data.
				m = new ModelAndView("signup");
				List<String> suggesion = new ArrayList<>(6);
				if (minLengthActive.equals("true")){ suggesion.add("Password must be more than " + minLength + " Letter long");}
				suggesion.add("Password must be less than " + maxLength + " Letter long");
				if (minUppercaseActive.equals("true")){ suggesion.add("Password must contain " + minUppercase + " Uppercase Letter");}
				if (minLowercaseActive.equals("true")){ suggesion.add("Password must contain " + minLowercase + " Lowercase Letter");}
				if (minSpecialActive.equals("true")){ suggesion.add("Password must contain " + minSpecial + " Special characters");}
				if (notAllowedCharacterActive.equals("true")){ suggesion.add("Password must not contain " + notAllowedCharacter);}
				m.addObject("hint",suggesion);
				m.addObject("errorMessage", "Password must be less than " + maxLength + " Letter long");
				return m;
			}
		}
		if (minUppercaseActive.equals("true")){
			if(!passwordPolicies.passwordCheckMinUppercaseCharacter(password, Integer.parseInt(minUppercase))){
				// Something wrong with the input data.
				m = new ModelAndView("signup");
				System.out.println("invalid uppercase");
				List<String> suggesion = new ArrayList<>(6);
				if (minLengthActive.equals("true")){ suggesion.add("Password must be more than " + minLength + " Letter long");}
				if (maxLengthActive.equals("true")){ suggesion.add("Password must be less than " + maxLength + " Letter long");}
				suggesion.add("Password must contain " + minUppercase + " Uppercase Letter");
				if (minLowercaseActive.equals("true")){ suggesion.add("Password must contain " + minLowercase + " Lowercase Letter");}
				if (minSpecialActive.equals("true")){ suggesion.add("Password must contain " + minSpecial + " Special characters");}
				if (notAllowedCharacterActive.equals("true")){ suggesion.add("Password must not contain " + notAllowedCharacter);}
				m.addObject("hint",suggesion);
				m.addObject("errorMessage", "Password must contain " + minUppercase + " Uppercase Letter");
				return m;
			}
		}
		if (minLowercaseActive.equals("true")){
			if(!passwordPolicies.passwordCheckMinLowercaseCharacter(password, Integer.parseInt(minLowercase))){
				// Something wrong with the input data.
				m = new ModelAndView("signup");
				System.out.println("invalid lowercase character");
				List<String> suggesion = new ArrayList<>(6);
				if (minLengthActive.equals("true")){ suggesion.add("Password must be more than " + minLength + " Letter long");}
				if (maxLengthActive.equals("true")){ suggesion.add("Password must be less than " + maxLength + " Letter long");}
				if (minUppercaseActive.equals("true")){ suggesion.add("Password must contain " + minUppercase + " Uppercase Letter");}
				suggesion.add("Password must contain " + minLowercase + " Lowercase Letter");
				if (minSpecialActive.equals("true")){ suggesion.add("Password must contain " + minSpecial + " Special characters");}
				if (notAllowedCharacterActive.equals("true")){ suggesion.add("Password must not contain " + notAllowedCharacter);}
				m.addObject("hint",suggesion);
				m.addObject("errorMessage", "Password must contain " + minLowercase + " Lowercase Letter");
				return m;
			}
		}
		if (minSpecialActive.equals("true")){
			if(!passwordPolicies.passwordCheckMinSpecialCharacter(password, Integer.parseInt(minSpecial))){
				// Something wrong with the input data.
				m = new ModelAndView("signup");
				System.out.println("invalid special character");
				List<String> suggesion = new ArrayList<>(6);
				if (minLengthActive.equals("true")){ suggesion.add("Password must be more than " + minLength + " Letter long");}
				if (maxLengthActive.equals("true")){ suggesion.add("Password must be less than " + maxLength + " Letter long");}
				if (minUppercaseActive.equals("true")){ suggesion.add("Password must contain " + minUppercase + " Uppercase Letter");}
				if (minLowercaseActive.equals("true")){ suggesion.add("Password must contain " + minLowercase + " Lowercase Letter");}
				suggesion.add("Password must contain " + minSpecial + " Special characters");
				if (notAllowedCharacterActive.equals("true")){ suggesion.add("Password must not contain " + notAllowedCharacter);}
				m.addObject("hint",suggesion);
				m.addObject("errorMessage", "Password must contain " + minSpecial + " Special characters");
				return m;
			}
		}
		if (notAllowedCharacterActive.equals("true")){
			if(passwordPolicies.passwordCheckNotAllowedCharacter(password, notAllowedCharacter.split(","))){
				// Something wrong with the input data.
				m = new ModelAndView("signup");
				System.out.println("invalid not allowed character");
				List<String> suggesion = new ArrayList<>(6);
				if (minLengthActive.equals("true")){ suggesion.add("Password must be more than " + minLength + " Letter long");}
				if (maxLengthActive.equals("true")){ suggesion.add("Password must be less than " + maxLength + " Letter long");}
				if (minUppercaseActive.equals("true")){ suggesion.add("Password must contain " + minUppercase + " Uppercase Letter");}
				if (minLowercaseActive.equals("true")){ suggesion.add("Password must contain " + minLowercase + " Lowercase Letter");}
				if (minSpecialActive.equals("true")){ suggesion.add("Password must contain " + minSpecial + " Special characters");}
				suggesion.add("Password must not contain " + notAllowedCharacter);
				m.addObject("hint",suggesion);
				m.addObject("errorMessage", "Password must not contain " + notAllowedCharacter);
				return m;
			}
		}

		if (User.isBannerIDValid(bannerID) &&
				User.isEmailValid(email) &&
				User.isFirstNameValid(firstName) &&
				User.isLastNameValid(lastName) &&
				password.equals(passwordConfirm))
		{
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
			// This is lame, I will improve this with auto-signin for M2.
			m = new ModelAndView("login");
		}
		else
		{
			// Something wrong with the input data.
			m = new ModelAndView("signup");
			List<String> suggesion = new ArrayList<>(6);
			if (minLengthActive.equals("true")){ suggesion.add("Password must be more than " + minLength + " Letter long");}
			if (maxLengthActive.equals("true")){ suggesion.add("Password must be less than " + maxLength + " Letter long");}
			if (minUppercaseActive.equals("true")){ suggesion.add("Password must contain " + minUppercase + " Uppercase Letter");}
			if (minLowercaseActive.equals("true")){ suggesion.add("Password must contain " + minLowercase + " Lowercase Letter");}
			if (minSpecialActive.equals("true")){ suggesion.add("Password must contain " + minSpecial + " Special characters");}
			if (notAllowedCharacterActive.equals("true")){ suggesion.add("Password must not contain " + notAllowedCharacter);}
			m.addObject("hint",suggesion);
			m.addObject("errorMessage", "Invalid data, please check your values.");
			return m;
		}
		return m;
	}
}
