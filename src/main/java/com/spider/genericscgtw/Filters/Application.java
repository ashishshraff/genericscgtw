package com.spider.genericscgtw.Filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Application {

    @Value("${request.scg.path}")
    private String path;

    @Value("${reroute.service.uri}")
    private String uri;

    @Value("${reroute.service.toPath}")
    private String reroutePath;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder){
        log.info("---Inside SCG----");
        return routeLocatorBuilder.routes().
                route(p->p
                        .path(path)
                        .filters(f -> f.rewritePath(path, reroutePath))
                        .uri(uri))
                .build();
    }
}
