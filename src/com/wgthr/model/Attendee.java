package com.wgthr.model;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

@PersistenceCapable
public class Attendee {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Getter
    private Key key;

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
