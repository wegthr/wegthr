package com.wgthr.json;

import com.google.appengine.api.datastore.Key;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class KeyDeserializer extends JsonDeserializer<Key> {

    @Override
    public Key deserialize(final JsonParser jp, final DeserializationContext dc) throws IOException, JsonProcessingException {
        return null;
    }

    
    
}
