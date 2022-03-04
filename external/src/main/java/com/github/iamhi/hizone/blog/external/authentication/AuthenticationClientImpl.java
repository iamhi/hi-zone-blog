package com.github.iamhi.hizone.blog.external.authentication;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public record AuthenticationClientImpl(
    WebClient.Builder builder
) implements AuthenticationClient {

    @Override
    public Mono<Boolean> connect() {
        return null;
    }

    @Override
    public Mono<Boolean> isConnected() {
        return null;
    }

    @Override
    public Mono<UserInfoResponse> fetchUserInfo(String token) {
        return null;
    }

    private WebClient getClient() {
        return WebClient.builder().baseUrl("").build();
    }

}
