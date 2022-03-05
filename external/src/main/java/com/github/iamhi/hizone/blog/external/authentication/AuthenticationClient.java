package com.github.iamhi.hizone.blog.external.authentication;

import com.github.iamhi.hizone.blog.external.authentication.responses.UserInfoResponse;
import reactor.core.publisher.Mono;

public interface AuthenticationClient {

    Mono<Boolean> connect();

    Mono<Boolean> isConnected();

    Mono<Boolean> hasAccessibility();

    Mono<UserInfoResponse> fetchUserInfo(String token);
}
