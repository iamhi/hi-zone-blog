package com.github.iamhi.hizone.blog.gateway.status;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class StatusRouter {

    private static final String ROUTER_PREFIX = "/status";

    @Bean
    public RouterFunction<ServerResponse> composePingRoute() {
        return route(GET(ROUTER_PREFIX + "/ping"), serverRequest ->
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Map.of("ping", "Pong from blog service")), Map.class));
    }

    @Bean
    public RouterFunction<ServerResponse> composePing2Route(WebClient.Builder builder) {
        return route(GET(ROUTER_PREFIX + "/ping2"), serverRequest -> {
            WebClient client;

            client = builder.baseUrl(
                "http://localhost:8081/hi-zone-api"
            ).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

            Mono<Map> response = client.get().uri("/authentication/status/ping").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Map.class);

            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(response, Map.class);
        });
    }
}

