package com.wgthr.persist.jdo;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.wgthr.persist.Persist;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

@Singleton
public class JdoPersistImpl implements Persist {

    @Inject
    private PersistenceManagerFactory pmf;

    @Override
    public <T> T persist(final T object) {
        final PersistenceManager pm = pmf.getPersistenceManager();
        final T returner;
        try {
            returner = pm.makePersistent(object);
        } catch (final Exception e) {
            pm.close();
            throw new RuntimeException(e);
        }
        return returner;
    }

    @Override
    public <T> T find(final Class<T> clazz, final String keyStr) {
        final Key key = KeyFactory.stringToKey(keyStr);
        final PersistenceManager pm = pmf.getPersistenceManager();
        try {
            return pm.getObjectById(clazz, key);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            pm.close();
        }
    }
}
