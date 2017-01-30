package com.sebastian_daschner.scalable_coffee_shop.barista.boundary;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class BaristaTimer {

    @Inject
    BaristaCommandService baristaService;

    @Schedule(second = "7/7", minute = "*", hour = "*", persistent = false)
    void checkCoffee() {
        baristaService.checkCoffee();
    }

    @Schedule(second = "8/8", minute = "*", hour = "*", persistent = false)
    void checkCustomerDelivery() {
        baristaService.checkCustomerDelivery();
    }

}
