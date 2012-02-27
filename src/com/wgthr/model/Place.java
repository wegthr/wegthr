package com.wgthr.model;

import java.util.List;
import javax.jdo.annotations.*;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Place {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Getter
    private com.google.appengine.api.datastore.Key key;

    @Setter
    @JsonIgnore
    private Gathering gathering;

    @Getter
    @Setter
    private String index;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private List<String> votes;

    @JsonIgnore
    public Gathering getGathering() {
        return gathering;
    }
}
