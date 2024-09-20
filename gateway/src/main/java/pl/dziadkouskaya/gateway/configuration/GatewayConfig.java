package pl.dziadkouskaya.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // product_service
            .route("product_route", r -> r.path("/product/**")
                .filters(f -> f.rewritePath("/product/(?<segment>.*)", "/productService/${segment}"))
                .uri("http://localhost:8080"))
            // search_service
            .route("search_route", r -> r.path("/find/**")
                .filters(f -> f.rewritePath("/find/(?<segment>.*)", "/search-server/${segment}"))
                .uri("http://localhost:8081"))

            .build();
    }
}
