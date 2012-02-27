package com.wgthr.rest;

import com.wgthr.model.Gathering;
import com.wgthr.model.Place;
import com.wgthr.notify.Notifier;
import com.wgthr.persist.Persist;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Singleton
@Path("/gathering.json")
public class GatheringService {

    @Inject
    private Persist persist;
    
    @Inject
    private Notifier notifier;

    @POST
    @Produces("application/json")
    public Gathering create(@FormParam("organizerEmail") final String organizerEmail, @FormParam("title") final String title,
            @FormParam("attendees[]") final List<String> attendees) {

        final Gathering gathering = new Gathering();
        gathering.setTitle(title);
        gathering.setOrganizerEmail(organizerEmail);
        gathering.setAttendees(attendees);
        gathering.setPlaces(Arrays.asList(title));

        persist.persist(gathering);
        
        notifier.sendInvites(gathering);

        return gathering;
    }

}
