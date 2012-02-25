package com.wgthr.model;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

public class Attendee {

    @PrimaryKey
    @Getter
    @Setter
    private String emailAddress;
    
    @Persistent
    @Getter
    @Setter
    private String vote;

    public Attendee() {
    }

    public Attendee(final String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}
