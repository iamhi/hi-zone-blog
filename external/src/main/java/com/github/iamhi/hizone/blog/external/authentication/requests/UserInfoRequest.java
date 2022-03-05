package com.github.iamhi.hizone.blog.external.authentication.requests;

public record UserInfoRequest (
    String token,
    String userToken
) {
}
