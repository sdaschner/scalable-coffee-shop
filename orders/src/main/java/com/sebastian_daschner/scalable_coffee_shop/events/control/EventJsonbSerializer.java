package com.sebastian_daschner.scalable_coffee_shop.events.control;


import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class EventJsonbSerializer implements JsonbSerializer<CoffeeEvent> {

    @Override
    public void serialize(final CoffeeEvent event, final JsonGenerator generator, final SerializationContext ctx) {
        generator.writeStartObject();
        generator.write("class", event.getClass().getCanonicalName());
        ctx.serialize("data", event, generator);
        generator.writeEnd();
        generator.close();
    }

}
