package com.wgthr.model;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.*;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Gathering {

    @Id
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String organizerEmail;

    @Getter
    @Setter
    private List<Place> places;

    @Getter
    @Setter
    private List<Attendee> attendees;
    
    public void setPlaces(final List<String> places) {
        
        if(this.places == null) {
            this.places = new ArrayList<Place>(places.size());
        }
        
        Integer i = 1;
        for (final String title : places) {
            final Place place = new Place();
            place.setIndex(Integer.toString(i++));
            place.setTitle(title);
            this.places.add( place );
        }
        
    }
        
    public void setAttendees(final List<String> emails) {
        
        if(this.attendees == null) {
            attendees = new ArrayList<Attendee>(emails.size());
        }
        
        for (final String email : emails) {
            attendees.add( new Attendee(email) );
        }
        
    }
    
}
