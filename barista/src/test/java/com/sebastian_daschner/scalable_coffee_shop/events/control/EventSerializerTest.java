package com.sebastian_daschner.scalable_coffee_shop.events.control;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class EventSerializerTest {

    @Parameterized.Parameter
    public String expected;

    @Parameterized.Parameter(1)
    public CoffeeEvent event;

    private EventSerializer cut = new EventSerializer();

    @Test
    public void test() {
        final String actual = new String(cut.serialize(null, event), StandardCharsets.UTF_8);
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return TestData.eventTestData();
    }

}
