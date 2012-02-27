package com.wgthr.persist.objectivy;

import com.googlecode.objectify.Objectify;
import com.wgthr.model.Gathering;
import com.wgthr.persist.Persist;
import javax.inject.Inject;

public class ObjectifyPersistImpl implements Persist {

    @Inject private Objectify objectify;

    @Override
    public void persist(final Gathering gathering) {
        objectify.put(gathering);
    }
    
}
