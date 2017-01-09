package com.sebastian_daschner.scalable_coffee_shop.beans.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("beans")
public class BeansResource {

    @Inject
    BeanService beanService;

    @POST
    public void storeBeans(JsonObject object) {
        final String beanOrigin = object.getString("beanOrigin", null);
        final int amount = object.getInt("amount", 0);

        if (beanOrigin == null || amount == 0)
            throw new BadRequestException();

        beanService.storeBeans(beanOrigin, amount);
    }

}
