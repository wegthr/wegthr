package com.wgthr.persist.jdo;

import javax.inject.Provider;
import javax.inject.Singleton;
import javax.jdo.PersistenceManager;

@Singleton
public class JdoPersistenceManagerProvider implements Provider<PersistenceManager> {

    private static final ThreadLocal<PersistenceManager> PERSISTENCE_MANAGERS =
            new ThreadLocal<PersistenceManager>();

    @Override
    public PersistenceManager get() {
        return PERSISTENCE_MANAGERS.get();
    }

    public void set(final PersistenceManager pm) {
        if (pm == null) {
            PERSISTENCE_MANAGERS.remove();
        } else {
            PERSISTENCE_MANAGERS.set(pm);
        }
    }
}
