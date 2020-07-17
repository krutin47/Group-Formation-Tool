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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
public class ForgotController {

    private final String USERNAME = "username";

    @GetMapping("/forgot")
    public String displayForgotPassword(Model model)
    {
        return "forgot";
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ModelAndView processForgotPassword(
            @RequestParam(name = USERNAME) String bannerID) {
        boolean success = false;

        if (User.isBannerIDValid(bannerID)){
            IUserPersistence userDB = SystemConfig.instance().getUserDB();
            success = userDB.forgotPassword(bannerID);
        }
        ModelAndView m;
        if (success) {
            System.out.println("in if");
            //Go to login after successfully sending request
            m = new ModelAndView("login");
        } else {
            System.out.println("in else");
            // Something wrong with the input data.
            m = new ModelAndView("forgot");
            m.addObject("errorMessage", "Invalid Banner ID, please check your value.");
        }
        return m;
    }
}
