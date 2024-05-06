package com.adel.camelrestapi;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Adel.Albediwy
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Value("${server.port}")
    private String port;

    private final List<NotifierRoutes> notifierRoutes;

    public CamelRouter(List<NotifierRoutes> notifierRoutes) {
        this.notifierRoutes = notifierRoutes;
    }

    @Override
    public void configure() {
        restConfiguration()
                .component("servlet")
                .port(6778)
                .enableCORS(true)
                .dataFormatProperty("prettyPrint", "true")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Ajax-Notifier API")
                .apiProperty("api.version", "1.0.0")
                .bindingMode(RestBindingMode.json);

        notifierRoutes.forEach(n -> n.registerRoute(this));
    }
}
