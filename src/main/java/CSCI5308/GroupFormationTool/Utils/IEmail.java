package CSCI5308.GroupFormationTool.Utils;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface IEmail {
    public void sendmail(String to, String subject, String msg) throws AddressException, MessagingException, IOException;
}
