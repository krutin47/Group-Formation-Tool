package CSCI5308.GroupFormationTool.Utils;

import CSCI5308.GroupFormationTool.Utils.IEmail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

// Learned from @source :: https://www.tutorialspoint.com/spring_boot/spring_boot_sending_email.htm
public class MailUtil implements IEmail {

    public void sendmail(String to, String subject, String msg) throws AddressException, MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("csci5308group5@gmail.com", "Admin@123");
            }
        });

        Message newMailMsg = new MimeMessage(session);
        newMailMsg.setFrom(new InternetAddress("csci5308group5@gmail.com", false));

        //attach your recipient here
        newMailMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

        // Write your subject here
        newMailMsg.setSubject(subject);

        // Write your HTML msg here
        newMailMsg.setContent(msg, "text/html");

        // TO set the date
        newMailMsg.setSentDate(new Date());

        Transport.send(newMailMsg);
    }
}