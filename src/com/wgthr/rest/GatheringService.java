package com.wgthr.rest;

import com.wgthr.model.Invite;
import com.wgthr.model.Gathering;
import com.wgthr.notify.Notifier;
import com.wgthr.persist.Persist;
import com.wgthr.persist.Transactional;
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
    @Transactional
    public Gathering create(@FormParam("organizerEmail") final String organizerEmail, @FormParam("title") final String title,
            @FormParam("invitations[]") final List<String> attendees) {
        logger.info("organizerEmail=" + organizerEmail);
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
    public Gathering getGatheringByInvite(@PathParam("key") final String inviteKey) {
        logger.info("Retrieving gathering [attendeeKey=" + inviteKey + "]");
        return getInvite(inviteKey).getGathering();
    }

    @POST
    @Produces("application/json")
    @Path("vote/{key}.json")
    @Transactional
    public Gathering vote(@PathParam("key") final String inviteKey, @FormParam("p") final String placeKey) {
        logger.info("Placing vote [inviteKey=" + inviteKey + ",placeKey=" + placeKey + "]");
        final Invite invite = getInvite(inviteKey);
        invite.setVote(placeKey);
        return invite.getGathering();
    }

    private Invite getInvite(final String inviteKey) {
        return persist.find(Invite.class, inviteKey);
    }
}
