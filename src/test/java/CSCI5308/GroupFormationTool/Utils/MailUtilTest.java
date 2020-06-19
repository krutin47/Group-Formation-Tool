package CSCI5308.GroupFormationTool.Utils;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MailUtilTest {

    @Test
    void sendmail() {
        IEmail email = SystemConfig.instance().getMailUtil();
        try {
            email.sendmail("krutin@dal.ca", "TEST", "This is a test");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}