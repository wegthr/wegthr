package com.wgthr.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.wgthr.model.Invite;
import com.wgthr.model.Gathering;
import com.wgthr.model.Place;
import com.wgthr.notify.Notifier;
import com.wgthr.notify.mail.MailNotifier;
import com.wgthr.persist.Persist;
import com.wgthr.persist.jdo.JdoPersistImpl;
import com.wgthr.persist.jdo.JdoPersistenceManagerOpeningFilter;
import com.wgthr.persist.objectivy.ObjectifyPersistImpl;
import com.wgthr.rest.GatheringService;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GuiceModule extends ServletModule {

    @Override
    protected void configureServlets() {
        install(settings());
        install(jdoPersistence());
        install(notifications());
        install(restServices());
    }

    private AbstractModule settings() {
        return new AbstractModule() {
            
            private static final String SETTINGS = "/com/wgthr/settings.properties";

            @Override
            protected void configure() {
                final InputStream propsStream = GuiceModule.class.getResourceAsStream(SETTINGS);
                final Properties props = new Properties();
                try {
                    props.load(propsStream);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Names.bindProperties(binder(), props);
            }
        };
    }

    private ServletModule restServices() {
        return new JerseyServletModule() {

            @Override
            protected void configureServlets() {
                bind(GatheringService.class);

                final Map<String, String> params = new HashMap<String, String>();
                params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");

                serve("/rest/*").with(GuiceContainer.class, params);
            }
        };
    }

    private AbstractModule jdoPersistence() {
        return new ServletModule() {

            @Override
            protected void configureServlets() {
                bind(Persist.class).to(JdoPersistImpl.class);
                filter("/rest/*").through(JdoPersistenceManagerOpeningFilter.class);
            }
        };
    }

    private AbstractModule objectifyPersistence() {
        return new AbstractModule() {

            @Override
            protected void configure() {
                ObjectifyService.register(Gathering.class);
                ObjectifyService.register(Invite.class);
                ObjectifyService.register(Place.class);
                final Objectify objectify = ObjectifyService.begin();
                bind(Objectify.class).toInstance(objectify);
                bind(Persist.class).to(ObjectifyPersistImpl.class);
            }
        };
    }

    private AbstractModule notifications() {
        return new AbstractModule() {

            @Override
            protected void configure() {
                bind(Notifier.class).to(MailNotifier.class);
            }
        };
    }
}
