package com.wgthr.persist;

import com.wgthr.model.Gathering;

public interface Persist {

    public <T> T persist(final T object);
    
    public <T> T find(final Class<T> clazz, final String key);
}
