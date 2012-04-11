package com.wgthr;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import lombok.Getter;

@Singleton
public class Settings {

    @Inject
    @Named("hostname")
    @Getter
    private String hostname;

    @Inject
    @Named("mail.invite.sender.address")
    @Getter
    private String mailInviteSenderAddress;
    
    @Inject
    @Named("mail.invite.sender.name")
    @Getter
    private String mailInviteSenderName;
    
    @Inject
    @Named("mail.invite.subject")
    @Getter
    private String mailInviteSubject;

}
