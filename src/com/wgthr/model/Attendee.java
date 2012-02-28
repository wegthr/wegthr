package com.wgthr.model;

import javax.jdo.annotations.*;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

@PersistenceCapable
public class Attendee {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String key;

    @Getter
    @Setter
    private String emailAddress;

    @Getter
    @Setter
    private String vote;

    @Setter
    @JsonIgnore
    private Gathering gathering;

    public Attendee() {
    }

    public Attendee(final String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    @JsonIgnore
    public Gathering getGathering() {
        return gathering;
    }
}
