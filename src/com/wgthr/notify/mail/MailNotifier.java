package com.wgthr.notify.mail;

import com.wgthr.Settings;
import com.wgthr.model.Invite;
import com.wgthr.model.Gathering;
import com.wgthr.notify.Notifier;
import java.util.Properties;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Singleton
public class MailNotifier implements Notifier {

    @Inject private Settings settings;
    
    @Override
    public void sendInvites(final Gathering gathering) {

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        final MimeMessage message = new MimeMessage(session);
        try {
            
            final InternetAddress from = new InternetAddress(settings.getMailInviteSenderAddress(), settings.getMailInviteSenderName());
            message.setFrom(from);
            message.setReplyTo(new Address[] { from });
            for (Invite attendee : gathering.getInvitations()) {
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress( attendee.getEmailAddress() ));
            }
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress( gathering.getOrganizerEmail() ) );
            message.setSubject(settings.getMailInviteSubject());
            
            final String text = gathering.getOrganizerEmail() + " has invited you to " + gathering.getTitle();
            message.setText(text);
            
            Transport.send(message);
            
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
