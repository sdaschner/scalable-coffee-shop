package com.sebastian_daschner.scalable_coffee_shop.orders.boundary;

import com.sebastian_daschner.scalable_coffee_shop.orders.control.CoffeeOrders;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.CoffeeOrder;

import javax.inject.Inject;
import java.util.UUID;

public class OrderQueryService {

    @Inject
    CoffeeOrders coffeeOrders;

    public CoffeeOrder getOrder(final UUID orderId) {
        return coffeeOrders.get(orderId);
    }

}
