package com.github.iamhi.hizone.blog.gateway.shared;

import com.github.iamhi.hizone.blog.config.AuthConfig;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

@Service
public record CookieHandlerImpl(
    AuthConfig authConfig
) implements CookieHandler {

    @Override
    public Mono<String> getUserToken(MultiValueMap<String, HttpCookie> cookies) {
        return Mono.justOrEmpty(cookies.getFirst(authConfig.getAccessTokenName()))
            .map(HttpCookie::getValue);
    }
}
