package com.wgthr.persist;

import com.wgthr.model.Gathering;

public interface Persist {

    public void persist(final Gathering gathering);
    
    public <T> T find(final Class<T> clazz, final Object key);
}
