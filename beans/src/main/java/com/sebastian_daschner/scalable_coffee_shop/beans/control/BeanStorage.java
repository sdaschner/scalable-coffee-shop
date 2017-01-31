package com.sebastian_daschner.scalable_coffee_shop.beans.control;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.BeansFetched;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.BeansStored;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class BeanStorage {

    private Map<String, Integer> beanOrigins = new ConcurrentHashMap<>();

    public Map<String, Integer> getStoredBeans() {
        return Collections.unmodifiableMap(beanOrigins);
    }

    public int getRemainingAmount(final String beanOrigin) {
        return beanOrigins.getOrDefault(beanOrigin, 0);
    }

    public void apply(@Observes BeansStored beansStored) {
        beanOrigins.merge(beansStored.getBeanOrigin(), beansStored.getAmount(), Math::addExact);
    }

    public void apply(@Observes BeansFetched beansFetched) {
        beanOrigins.merge(beansFetched.getBeanOrigin(), 0, (i1, i2) -> i1 - 1);
    }

}
