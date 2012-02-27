package com.wgthr.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Attendee {

    @Id
    @Getter
    @Setter
    private Long id;
    
    @PrimaryKey
    @Getter
    @Setter
    private String emailAddress;
    
    @Getter
    @Setter
    private String vote;

    public Attendee() {
    }

    public Attendee(final String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}
