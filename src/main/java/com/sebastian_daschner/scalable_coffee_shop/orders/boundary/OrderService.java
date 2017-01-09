package com.sebastian_daschner.scalable_coffee_shop.orders.boundary;

import com.sebastian_daschner.scalable_coffee_shop.orders.control.OrderEventStore;
import com.sebastian_daschner.scalable_coffee_shop.orders.control.CoffeeOrders;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.*;

import javax.inject.Inject;
import java.util.UUID;

public class OrderService {

    @Inject
    OrderEventStore eventStore;

    @Inject
    CoffeeOrders coffeeOrders;

    public void placeOrder(final OrderInfo orderInfo) {
        eventStore.addAndFire(new OrderPlaced(orderInfo));
    }

    void acceptOrder(final UUID orderId) {
        final OrderInfo orderInfo = coffeeOrders.get(orderId).getOrderInfo();
        eventStore.addAndFire(new OrderAccepted(orderInfo));
    }

    void cancelOrder(final UUID orderId, final String reason) {
        eventStore.addAndFire(new OrderCancelled(orderId, reason));
    }

    void startOrder(final UUID orderId) {
        eventStore.addAndFire(new OrderStarted(orderId));
    }

    void finishOrder(final UUID orderId) {
        eventStore.addAndFire(new OrderFinished(orderId));
    }

    void deliverOrder(final UUID orderId) {
        eventStore.addAndFire(new OrderDelivered(orderId));
    }

}
