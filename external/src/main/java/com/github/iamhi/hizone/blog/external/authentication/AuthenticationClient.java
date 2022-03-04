package com.github.iamhi.hizone.blog.external.authentication;

import reactor.core.publisher.Mono;

public interface AuthenticationClient {

    Mono<Boolean> connect();

    Mono<Boolean> isConnected();

    Mono<UserInfoResponse> fetchUserInfo(String token);
}
