package com.github.iamhi.hizone.blog.gateway.shared;

import org.springframework.http.HttpCookie;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

public interface CookieHandler {

    Mono<String> getUserToken(MultiValueMap<String, HttpCookie> cookies);
}
