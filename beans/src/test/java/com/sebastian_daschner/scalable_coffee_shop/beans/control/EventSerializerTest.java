package com.sebastian_daschner.scalable_coffee_shop.beans.control;

import com.sebastian_daschner.scalable_coffee_shop.events.control.EventSerializer;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

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
