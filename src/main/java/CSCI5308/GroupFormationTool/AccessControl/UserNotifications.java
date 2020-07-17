package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.SystemConfig;

import javax.mail.MessagingException;
import java.io.IOException;

public class UserNotifications implements IUserNotifications {
    @Override
    public void sendUserLoginCredentials(User user, String rawPassword) {
        try {
            SystemConfig.instance().getMailUtil().sendmail(user.getEmail(), "Welcome to Online student portal",
                    "<h1>Greetings!!!</h1>" +
                            "<p>you have granted access to the Online Student Portal.</p>" +
                            "<p>Here are your credentials:</p><br>" +
                            "<p><strong>Banner ID: </strong>" + user.getBannerID() +
                            "</p><p><strong>Password: </strong>" + rawPassword +
                            "</p><br>" +
                            "<p>We will see you soon... Have a grate Day!!</p>");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
