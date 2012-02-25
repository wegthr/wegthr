package com.wgthr.rest;

import com.wgthr.model.Gathering;
import com.wgthr.model.Place;
import com.wgthr.persist.Persist;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Singleton
@Path("/gathering.json")
public class GatheringService {

    @Inject
    private Persist persist;

    @POST
    @Produces("application/json")
    public Gathering create(@QueryParam("organizerEmail") final String organizerEmail, @QueryParam("title") final String title,
            @QueryParam("attendees") final List<String> attendees) {

        final Gathering gathering = new Gathering();
        gathering.setTitle(title);
        gathering.setOrganizerEmail(organizerEmail);
        gathering.addAttendees(attendees);

        final Place place = new Place();
        place.setTitle(title);
        System.out.println("organizerEmail: " + organizerEmail);
        System.out.println("title: " + title);
        System.out.println("attendees: " + attendees);
        gathering.setPlaces(Arrays.asList(place));

        persist.persist(gathering);

        return gathering;
    }

}
