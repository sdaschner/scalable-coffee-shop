package com.sebastian_daschner.scalable_coffee_shop.beans.boundary;

import com.sebastian_daschner.scalable_coffee_shop.beans.control.BeanStorage;

import javax.inject.Inject;
import java.util.Map;

public class BeanQueryService {

    @Inject
    BeanStorage beanStorage;

    public Map<String, Integer> getStoredBeans() {
        return beanStorage.getStoredBeans();
    }

}
