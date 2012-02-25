package com.wgthr.guice;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.wgthr.inject.PersistenceManagerFactoryProvider;
import com.wgthr.notify.Notifier;
import com.wgthr.notify.mail.MailNotifier;
import com.wgthr.persist.Persist;
import com.wgthr.persist.jdo.JdoPersistImpl;
import com.wgthr.rest.GatheringService;
import java.util.HashMap;
import java.util.Map;
import javax.jdo.PersistenceManagerFactory;

public class GuiceModule extends ServletModule {

    @Override
    protected void configureServlets() {
        install(persistence());
        install(notifications());
        install(restServices());
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

    private AbstractModule persistence() {
        return new AbstractModule() {

            @Override
            protected void configure() {
                bind(PersistenceManagerFactory.class).toProvider(PersistenceManagerFactoryProvider.class);
                bind(Persist.class).to(JdoPersistImpl.class);
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
