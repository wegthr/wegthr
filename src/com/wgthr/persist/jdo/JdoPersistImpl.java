package com.wgthr.persist.jdo;

import com.wgthr.model.Gathering;
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
    public void persist(final Gathering gathering) {
        final PersistenceManager pm = pmf.getPersistenceManager();
        pm.makePersistent(gathering);
        pm.close();
    }
}
