package com.github.iamhi.hizone.blog.external.authentication;

import reactor.core.publisher.Mono;

interface AuthenticationStateService {

    Mono<String> getToken();

    void setToken(String token);
}
