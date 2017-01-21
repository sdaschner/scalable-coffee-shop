package com.sebastian_daschner.scalable_coffee_shop.events.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.AbstractEvent;
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
    public AbstractEvent expected;

    private EventDeserializer cut = new EventDeserializer();

    @Test
    public void test() {
        final AbstractEvent actual = cut.deserialize(null, data.getBytes(StandardCharsets.UTF_8));
        assertEventEquals(actual, expected);
    }

    private void assertEventEquals(final AbstractEvent actual, final AbstractEvent expected) {
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return TestData.eventTestData();
    }

}
