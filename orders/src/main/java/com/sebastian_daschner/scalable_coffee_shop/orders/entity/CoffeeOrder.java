package com.sebastian_daschner.scalable_coffee_shop.orders.entity;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderInfo;

/**
 * Aggregate. Represents a coffee order with the state.
 */
public class CoffeeOrder {

    private CoffeeOrderState state;
    private OrderInfo orderInfo;

    public void place(final OrderInfo orderInfo) {
        state = CoffeeOrderState.PLACED;
        this.orderInfo = orderInfo;
    }

    public void accept() {
        state = CoffeeOrderState.ACCEPTED;
    }

    public void cancel() {
        state = CoffeeOrderState.CANCELLED;
    }

    public void start() {
        state = CoffeeOrderState.STARTED;
    }

    public void finish() {
        state = CoffeeOrderState.FINISHED;
    }

    public void deliver() {
        state = CoffeeOrderState.DELIVERED;
    }

    public CoffeeOrderState getState() {
        return state;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public enum CoffeeOrderState {
        PLACED,
        ACCEPTED,
        STARTED,
        FINISHED,
        DELIVERED,
        CANCELLED
    }

}
