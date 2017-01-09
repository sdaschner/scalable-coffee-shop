package com.sebastian_daschner.scalable_coffee_shop.events;

import javax.enterprise.event.Observes;

public class EventLogger {

    private void handle(@Observes Object object) {
        if (object.getClass().getName().contains("daschner"))
            System.out.println("event: " + object.getClass().getSimpleName());
    }

}
