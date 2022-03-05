package com.github.iamhi.hizone.blog.external.authentication.requests;

public record LoginRequest(
    String username,
    String password
) {
}
