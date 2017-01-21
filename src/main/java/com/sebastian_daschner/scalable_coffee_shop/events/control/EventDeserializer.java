package com.sebastian_daschner.scalable_coffee_shop.events.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.ByteArrayInputStream;
import java.util.Map;

public class EventDeserializer implements Deserializer<AbstractEvent> {

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        // nothing to configure
    }

    @Override
    public AbstractEvent deserialize(final String topic, final byte[] data) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(data)) {
            final JsonObject jsonObject = Json.createReader(input).readObject();
            final Class<? extends AbstractEvent> eventClass = (Class<? extends AbstractEvent>) Class.forName(jsonObject.getString("class"));
            return eventClass.getConstructor(JsonObject.class).newInstance(jsonObject.getJsonObject("data"));
        } catch (Exception e) {
            throw new SerializationException("Could not deserialize event", e);
        }
    }

//    @Override
//    public AbstractEvent deserialize(final String topic, final byte[] data) {
//        final JsonbConfig config = new JsonbConfig()
//                .withAdapters(new UUIDAdapter())
//                .withDeserializers(new EventJsonbDeserializer());

//        final Jsonb jsonb = JsonbBuilder.create(config);

//        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data)) {
//            return jsonb.fromJson(inputStream, AbstractEvent.class);
//        } catch (Exception e) {
//            throw new SerializationException(e);
//        }
//    }

    @Override
    public void close() {
        // nothing to do
    }

}
