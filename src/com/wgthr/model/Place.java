package com.wgthr.model;

import java.util.List;
import javax.jdo.annotations.*;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Place {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    @Getter
    private String encodedKey;

    @Persistent
    @Getter
    @Setter
    private String title;

    @Persistent
    @Getter
    @Setter
    private List<String> votes;

}
