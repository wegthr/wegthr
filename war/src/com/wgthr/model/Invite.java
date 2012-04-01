package com.wgthr.model;

import javax.jdo.annotations.*;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

@PersistenceCapable
public class Invite {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    @Getter
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

    public Invite() {
    }

    public Invite(final String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    @JsonIgnore
    public Gathering getGathering() {
        return gathering;
    }
}
