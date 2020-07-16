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

@Controller
public class ResetPasswordController {
    @GetMapping("/reset")
    public ModelAndView displayResetPassword(@RequestParam(name = "_token") String _token,
                                             @RequestParam(name = "id") String id)
    {
        System.out.println("_token ----> " + _token);
        System.out.println("id ----> " + id);
        ModelAndView m = new ModelAndView("reset");
        m.addObject("_token", _token);
        m.addObject("id", id);
        return m;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView processResetPassword(@RequestParam(name = "password") String password,
                                             @RequestParam(name = "passwordConfirmation") String passwordConfirmation,
                                             @RequestParam(name = "_token") String _token,
                                             @RequestParam(name = "id") String id) {
        boolean success = false;
        ModelAndView m;
        System.out.println("<-------------> ");
        System.out.println("_token ----> " + _token);
        System.out.println("id ----> " + id);
        System.out.println("password ----> " + password);
        System.out.println("passwordConfirmation ----> " + passwordConfirmation);
        if(password.equals(passwordConfirmation)){
            IUserPersistence userDB = SystemConfig.instance().getUserDB();
            success = userDB.resetPassword(Long.parseLong(id), password, _token);
        }
        if (success){
            //Go to login after successfully sending request
            m = new ModelAndView("login");
            return m;
        } else {
            //Go to error page otherwise
            m = new ModelAndView("error");
            return m;
        }
    }
}
