package com.sebastian_daschner.scalable_coffee_shop.events.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;

import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.lang.reflect.Type;

public class EventJsonbDeserializer implements JsonbDeserializer<AbstractEvent> {

    // FIXME not used atm

    @Override
    public AbstractEvent deserialize(final JsonParser parser, final DeserializationContext ctx, final Type rtType) {
//        try {
//            parser.next();
//
//            // class will be first
//            final JsonParser.Event event = parser.next();
//            if (event != JsonParser.Event.KEY_NAME || !parser.getString().equals("class"))
//                throw new JsonbException("Serialized event has to start with class property");
//
//            parser.next();
//            final Class<? extends AbstractEvent> eventClass = (Class<? extends AbstractEvent>) Class.forName(parser.getString());
//
//            if (parser.next() != JsonParser.Event.KEY_NAME || !parser.getString().equals("data"))
//                throw new JsonbException("Serialized event has to contain data property");
//
//            final JsonObject abstractEvent = ctx.deserialize(JsonObject.class, parser);
//            System.out.println("abstractEvent = " + abstractEvent);
//            parser.next();
//
        return null;
//        } catch (Exception e) {
//            throw new JsonbException("Could not deserialize event ", e);
//        }
    }

}
