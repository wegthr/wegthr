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
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    @Getter
    private String key;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String organizerEmail;

    @Getter
    @Persistent(mappedBy = "gathering")
    private List<Place> places;

    @Getter
    @Persistent(mappedBy = "gathering")
    private List<Invite> invitations;

    public void setPlaces(final List<String> places) {

        if (this.places == null) {
            this.places = new ArrayList<Place>(places.size());
        }

        Integer i = 1;
        for (final String title : places) {
            final Place place = new Place();
            place.setIndex(Integer.toString(i++));
            place.setTitle(title);
            this.places.add(place);
        }

    }

    public void setInvitations(final List<String> emails) {

        if (this.invitations == null) {
            invitations = new ArrayList<Invite>(emails.size());
        }

        for (final String email : emails) {
            final Invite attendee = new Invite(email);
            attendee.setGathering(this);
            invitations.add(attendee);
        }

    }
}
