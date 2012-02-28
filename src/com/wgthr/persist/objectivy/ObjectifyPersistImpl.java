package com.wgthr.persist.objectivy;

import com.googlecode.objectify.Objectify;
import com.wgthr.persist.Persist;
import javax.inject.Inject;

public class ObjectifyPersistImpl implements Persist {

    @Inject private Objectify objectify;

    @Override
    public <T> T persist(final T object) {
        objectify.put(object);
        return object;
    }

    @Override
    public <T> T find(Class<T> clazz, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
