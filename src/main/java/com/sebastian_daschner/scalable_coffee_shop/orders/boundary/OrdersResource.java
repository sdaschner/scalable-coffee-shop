package com.sebastian_daschner.scalable_coffee_shop.orders.boundary;

import com.sebastian_daschner.scalable_coffee_shop.orders.entity.CoffeeType;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.OrderInfo;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("orders")
public class OrdersResource {

    @Inject
    OrderService orderService;

    @POST
    public Response orderCoffee(JsonObject order) {
        final String beanOrigin = order.getString("beanOrigin", null);
        final CoffeeType coffeeType = CoffeeType.fromString(order.getString("coffeeType", null));

        if (beanOrigin == null || coffeeType == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        orderService.placeOrder(new OrderInfo(UUID.randomUUID(), coffeeType, beanOrigin));

        return Response.accepted().build();
    }

}
