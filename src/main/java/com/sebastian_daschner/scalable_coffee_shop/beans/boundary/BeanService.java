package com.sebastian_daschner.scalable_coffee_shop.beans.boundary;

import com.sebastian_daschner.scalable_coffee_shop.events.control.EventProducer;
import com.sebastian_daschner.scalable_coffee_shop.barista.entity.BeansFetched;
import com.sebastian_daschner.scalable_coffee_shop.beans.control.BeanStorage;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.BeansStored;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderBeansValidated;
import com.sebastian_daschner.scalable_coffee_shop.beans.entity.OrderFailedBeansNotAvailable;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class BeanService {

    @Inject
    EventProducer eventProducer;

    @Inject
    BeanStorage beanStorage;

    public Map<String, Integer> getStoredBeans() {
        return beanStorage.getStoredBeans();
    }

    public void storeBeans(final String beanOrigin, final int amount) {
        eventProducer.publish(new BeansStored(beanOrigin, amount));
    }

    void validateBeans(final String beanOrigin, final UUID orderId) {
        if (beanStorage.getRemainingAmount(beanOrigin) > 0)
            eventProducer.publish(new OrderBeansValidated(orderId));
        else
            eventProducer.publish(new OrderFailedBeansNotAvailable(orderId));
    }

    void fetchBeans(final String beanOrigin) {
        eventProducer.publish(new BeansFetched(beanOrigin));
    }

}
