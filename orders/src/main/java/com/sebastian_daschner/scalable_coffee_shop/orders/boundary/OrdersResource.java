package com.sebastian_daschner.scalable_coffee_shop.orders.boundary;

import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeType;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderInfo;
import com.sebastian_daschner.scalable_coffee_shop.orders.entity.CoffeeOrder;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.UUID;

@Path("orders")
public class OrdersResource {

    @Inject
    OrderCommandService commandService;

    @Inject
    OrderQueryService queryService;

    @Context
    UriInfo uriInfo;

    @POST
    public Response orderCoffee(JsonObject order) {
        final String beanOrigin = order.getString("beanOrigin", null);
        final CoffeeType coffeeType = CoffeeType.fromString(order.getString("coffeeType", null));

        if (beanOrigin == null || coffeeType == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        final UUID orderId = UUID.randomUUID();
        commandService.placeOrder(new OrderInfo(orderId, coffeeType, beanOrigin));

        final URI uri = uriInfo.getRequestUriBuilder().path(OrdersResource.class, "getOrder").build(orderId);
        return Response.accepted().header(HttpHeaders.LOCATION, uri).build();
    }

    @GET
    @Path("{id}")
    public JsonObject getOrder(@PathParam("id") UUID orderId) {
        final CoffeeOrder order = queryService.getOrder(orderId);

        if (order == null)
            throw new NotFoundException();

        return Json.createObjectBuilder()
                .add("status", order.getState().name().toLowerCase())
                .add("type", order.getOrderInfo().getType().name().toLowerCase())
                .add("beanOrigin", order.getOrderInfo().getBeanOrigin())
                .build();
    }

}
