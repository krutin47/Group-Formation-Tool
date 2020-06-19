package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class ResetPasswordController {
    @GetMapping("/reset")
    public String displayResetPassword(Model model)
    {
        return "reset";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView processResetPassword(
            @RequestParam(name = "password") String bannerID) {
        boolean success = true;
        ModelAndView m;
        //Go to login after successfully sending request
        m = new ModelAndView("login");
        return m;
    }
}
