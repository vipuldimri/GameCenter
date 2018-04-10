/*
Class to send email in background thread 
*/
package gamecenter;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailThread extends Thread
{
    String To;
    String passwordgamezone;
    String OwnerName;
    String GameZoneName;
    public SendEmailThread(String To,String passwordgamezone,String OwnerName,String GameZoneName)
    {
        this.To = To;
        this.passwordgamezone = passwordgamezone;
        this.OwnerName = OwnerName;
        this.GameZoneName = GameZoneName;
    }
    @Override
    public void run()
    {
        String from = "starktechnologies2018@gmail.com";
        String password = "sample@123";       
        String sub = "Password";
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
       
        

           Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           @Override
           protected PasswordAuthentication getPasswordAuthentication()
           {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
  
          try 
          {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(To));    
          
           message.setSubject(sub);
           String mss = "Dear "+OwnerName+" Your Password is "+passwordgamezone+", for Login into "+GameZoneName+"  please reset your password for safety purpose "+System.lineSeparator() + "Regards starktechnologies";
    
           message.setText(mss);    
   
           Transport.send(message);    
          } 
          catch (MessagingException e)
          {
              throw new RuntimeException(e);
          }    
          
          
          
    }
    
}
