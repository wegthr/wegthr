package com.wgthr.rest;

import com.wgthr.model.Attendee;
import com.wgthr.model.Gathering;
import com.wgthr.notify.Notifier;
import com.wgthr.persist.Persist;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;

@Singleton
@Path("/gathering/*")
public class GatheringService {

    @Inject
    private Persist persist;

    @Inject
    private Notifier notifier;

    @Inject
    private Logger logger;

    @POST
    @Produces("application/json")
    @Path("create.json")
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

    @GET
    @Produces("application/json")
    @Path("get.json")
    public Gathering get(@QueryParam("key") final String gatheringKey) {
        logger.info("Retrieving gathering [gatheringKey=" + gatheringKey + "]");
        return persist.find(Gathering.class, gatheringKey);
    }

    @GET
    @Produces("application/json")
    @Path("invite.json")
    public Gathering invite(@QueryParam("invite") final String attendeeKey) {
        logger.info("Retrieving gathering [attendeeKey=" + attendeeKey + "]");
        final Attendee attendee = persist.find(Attendee.class, attendeeKey);
        return attendee.getGathering();
    }
}
