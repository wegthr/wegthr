package com.wgthr.model;

import java.util.List;
import javax.jdo.annotations.*;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Place {

    @Id
    @PrimaryKey
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String index;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private List<String> votes;

}
