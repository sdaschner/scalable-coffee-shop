package com.sebastian_daschner.scalable_coffee_shop.events.control;

import javax.json.bind.adapter.JsonbAdapter;
import java.util.UUID;

public class UUIDAdapter implements JsonbAdapter<UUID, String> {

    @Override
    public UUID adaptToJson(final String string) throws Exception {
        return UUID.fromString(string);
    }

    @Override
    public String adaptFromJson(final UUID uuid) throws Exception {
        return uuid.toString();
    }

}
