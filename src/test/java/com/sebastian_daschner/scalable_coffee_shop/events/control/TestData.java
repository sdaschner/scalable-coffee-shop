package com.sebastian_daschner.scalable_coffee_shop.events.control;

import com.sebastian_daschner.scalable_coffee_shop.barista.entity.BeansFetched;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewFinished;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeDelivered;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.BeansStored;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderBeansValidated;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderFailedBeansNotAvailable;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class TestData {

    private TestData() {
    }

    public static List<Object[]> eventTestData() {
        return Arrays.asList(
                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.barista.entity.BeansFetched\",\"data\":{\"beanOrigin\":\"Origin\",\"instant\":\"2017-01-18T08:11:21.589Z\"}}",
                        new BeansFetched("Origin", Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.beans.entity.BeansStored\",\"data\":{\"amount\":2,\"beanOrigin\":\"Origin\",\"instant\":\"2017-01-18T08:11:21.589Z\"}}",
                        new BeansStored("Origin", 2, Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewFinished\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new CoffeeBrewFinished(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeBrewStarted\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new CoffeeBrewStarted(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderAccepted\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new OrderAccepted(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderBeansValidated\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderBeansValidated(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderCancelled\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"reason\":\"reason\"}}",
                        new OrderCancelled(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), "reason", Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderDelivered\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderDelivered(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderFailedBeansNotAvailable\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderFailedBeansNotAvailable(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderFinished\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new OrderFinished(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{\"class\":\"com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderPlaced\",\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\",\"orderInfo\":{\"beanOrigin\":\"Origin\",\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\",\"type\":\"ESPRESSO\"}}}",
                        new OrderPlaced(new OrderInfo(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), CoffeeType.ESPRESSO, "Origin"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{" +
                        "\"class\":\"com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeDelivered\"," +
                        "\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\"," +
                        "\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new CoffeeDelivered(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))},

                new Object[]{"{" +
                        "\"class\":\"com.sebastian_daschner.scalable_coffee_shop.barista.entity.CoffeeDelivered\"," +
                        "\"data\":{\"instant\":\"2017-01-18T08:11:21.589Z\"," +
                        "\"orderId\":\"e4d284f0-2545-4368-ae80-8278c33edf16\"}}",
                        new CoffeeDelivered(UUID.fromString("e4d284f0-2545-4368-ae80-8278c33edf16"), Instant.parse("2017-01-18T08:11:21.589Z"))}
        );
    }

}
