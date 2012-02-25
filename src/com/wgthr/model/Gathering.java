package com.wgthr.model;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.*;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Gathering {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Persistent
    @Getter
    @Setter
    private String title;

    @Persistent
    @Getter
    @Setter
    private String organizerEmail;

    @Persistent
    @Getter
    @Setter
    private List<Place> places;

    @Persistent
    @Getter
    @Setter
    private List<Attendee> attendees;
    
    public void addAttendees(final List<String> emails) {
        
        if(attendees == null) {
            attendees = new ArrayList<Attendee>(emails.size());
        }
        
        for (final String email : emails) {
            attendees.add( new Attendee(email) );
        }
        
    }
    
}
