package com.wgthr.persist.jdo;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.wgthr.persist.Persist;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jdo.PersistenceManager;

@Singleton
public class JdoPersistImpl implements Persist {

    @Inject
    private JdoPersistenceManagerProvider pmProvider;

    @Override
    public <T> T persist(final T object) {
        return persistenceManager().makePersistent(object);
    }

    @Override
    public <T> T find(final Class<T> clazz, final String keyStr) {
        final Key key = KeyFactory.stringToKey(keyStr);
        final PersistenceManager pm = pmProvider.get();
        return persistenceManager().getObjectById(clazz, key);
    }

    private PersistenceManager persistenceManager() {
        return pmProvider.get();
    }
}
