package com.sebastian_daschner.scalable_coffee_shop.orders.boundary;

import com.sebastian_daschner.scalable_coffee_shop.events.control.EventProducer;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.*;
import com.sebastian_daschner.scalable_coffee_shop.orders.control.CoffeeOrders;

import javax.inject.Inject;
import java.util.UUID;

public class OrderCommandService {

    @Inject
    EventProducer eventProducer;

    @Inject
    CoffeeOrders coffeeOrders;

    public void placeOrder(final OrderInfo orderInfo) {
        eventProducer.publish(new OrderPlaced(orderInfo));
    }

    void acceptOrder(final UUID orderId) {
        final OrderInfo orderInfo = coffeeOrders.get(orderId).getOrderInfo();
        eventProducer.publish(new OrderAccepted(orderInfo));
    }

    void cancelOrder(final UUID orderId, final String reason) {
        eventProducer.publish(new OrderCancelled(orderId, reason));
    }

    void startOrder(final UUID orderId) {
        eventProducer.publish(new OrderStarted(orderId));
    }

    void finishOrder(final UUID orderId) {
        eventProducer.publish(new OrderFinished(orderId));
    }

    void deliverOrder(final UUID orderId) {
        eventProducer.publish(new OrderDelivered(orderId));
    }

}
