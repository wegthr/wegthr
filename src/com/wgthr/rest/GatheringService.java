package com.wgthr.rest;

import com.wgthr.model.Invite;
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
            @FormParam("invitations[]") final List<String> attendees) {

        final Gathering gathering = new Gathering();
        gathering.setTitle(title);
        gathering.setOrganizerEmail(organizerEmail);
        gathering.setInvitations(attendees);
        gathering.setPlaces(Arrays.asList(title));

        persist.persist(gathering);

        notifier.sendInvites(gathering);

        return gathering;
    }

    @GET
    @Produces("application/json")
    @Path("{key}.json")
    public Gathering get(@PathParam("key") final String key) {
        logger.info("Retrieving gathering [key=" + key + "]");
        final Gathering gathering = persist.find(Gathering.class, key);
        return gathering;
    }

    @GET
    @Produces("application/json")
    @Path("invite/{key}.json")
    public Gathering attendee(@PathParam("key") final String inviteKey) {
        logger.info("Retrieving gathering [attendeeKey=" + inviteKey + "]");
        final Invite attendee = persist.find(Invite.class, inviteKey);
        return attendee.getGathering();
    }
}
