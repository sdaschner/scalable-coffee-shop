package com.sebastian_daschner.scalable_coffee_shop.orders.control;

import com.sebastian_daschner.scalable_coffee_shop.events.control.EventDeserializer;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EventDeserializerTest {

    @Parameterized.Parameter
    public String data;

    @Parameterized.Parameter(1)
    public CoffeeEvent expected;

    private EventDeserializer cut = new EventDeserializer();

    @Test
    public void test() {
        final CoffeeEvent actual = cut.deserialize(null, data.getBytes(StandardCharsets.UTF_8));
        assertEventEquals(actual, expected);
    }

    private void assertEventEquals(final CoffeeEvent actual, final CoffeeEvent expected) {
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return TestData.eventTestData();
    }

}
