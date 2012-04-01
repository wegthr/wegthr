package com.wgthr.inject;

import com.google.inject.Provider;
import javax.inject.Singleton;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

@Singleton
public class PersistenceManagerFactoryProvider implements Provider<PersistenceManagerFactory> {

    @Override
    @Singleton
    public PersistenceManagerFactory get() {
        return JDOHelper.getPersistenceManagerFactory("pmf");
    }

}
