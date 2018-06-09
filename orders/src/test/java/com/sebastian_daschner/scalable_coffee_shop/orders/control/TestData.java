package com.sebastian_daschner.scalable_coffee_shop.orders.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class TestData {

    private TestData() {
    }

    public static List<Object[]> eventTestData() {
        return Arrays.asList(
                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeBrewFinished\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new CoffeeBrewFinished(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeBrewStarted\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new CoffeeBrewStarted(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderAccepted\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new OrderAccepted(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderBeansReserved\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderBeansReserved(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderCancelled\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"reason\":\"reason\"}}",
                        new OrderCancelled(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), "reason", Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderDelivered\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderDelivered(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderFailedBeansNotAvailable\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderFailedBeansNotAvailable(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderFinished\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderFinished(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderPlaced\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new OrderPlaced(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{" +
                        "\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeDelivered\"," +
                        "\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\"," +
                        "\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new CoffeeDelivered(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{" +
                        "\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeDelivered\"," +
                        "\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\"," +
                        "\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new CoffeeDelivered(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeBrewStarted\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new CoffeeBrewStarted(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderBeansReserved\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderBeansReserved(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderFailedBeansNotAvailable\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderFailedBeansNotAvailable(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderPlaced\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new OrderPlaced(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.BeansFetched\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"beanOrigin\":\"Origin\"}}",
                        new BeansFetched("Origin", Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeBrewFinished\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new CoffeeBrewFinished(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeBrewStarted\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new CoffeeBrewStarted(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderAccepted\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new OrderAccepted(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{" +
                        "\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeDelivered\"," +
                        "\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\"," +
                        "\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new CoffeeDelivered(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{" +
                        "\"class\":\"com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeDelivered\"," +
                        "\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\"," +
                        "\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new CoffeeDelivered(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))}
        );
    }

}
