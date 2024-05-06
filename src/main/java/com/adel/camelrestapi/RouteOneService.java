package com.adel.camelrestapi;

import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

/**
 * @author Adel.Albediwy
 */
@Component
public class RouteOneService implements NotifierRoutes {

    private static final String ROUTE = "direct:some-service";

    @Override
    public void registerRoute(final CamelRouter camelRouter) {
        camelRouter
                .rest()
                .description("SomeService service")
                .consumes("application/json")
                .produces("application/json")

                .post("/send-count/{messageCount}")
                .description("Detect trades")
                .param().name("messageCount").type(RestParamType.path).dataType("integer").endParam()
                .responseMessage().code(200).message("tracked").endResponseMessage()
                .to(ROUTE);

        camelRouter.from(ROUTE)
                .bean(SomeService.class,"onSend(${header.messageCount})");

    }
}
