package com.wgthr.notify.mail;

import com.wgthr.model.Attendee;
import com.wgthr.model.Gathering;
import com.wgthr.notify.Notifier;
import java.util.Properties;
import javax.inject.Singleton;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Singleton
public class MailNotifier implements Notifier {

    @Override
    public void sendInvites(final Gathering gathering) {

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        final MimeMessage message = new MimeMessage(session);
        try {
            
            message.setFrom(new InternetAddress("notifications@wgthr.com", "wgthr.com Notifications"));
            message.setReplyTo(new Address[] { new InternetAddress("voting@wgthr.com", "wgthr.com Notifications" )});
            for (Attendee attendee : gathering.getAttendees()) {
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress( attendee.getEmailAddress() ));
            }
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress( gathering.getOrganizerEmail() ) );
            message.setSubject("You have been invited to " + gathering.getTitle());
            message.setText("You have been invited to " + gathering.getTitle());
            
            Transport.send(message);
            
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
