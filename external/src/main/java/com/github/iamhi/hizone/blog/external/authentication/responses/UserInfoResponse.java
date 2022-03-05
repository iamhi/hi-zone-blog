package com.github.iamhi.hizone.blog.external.authentication.responses;

import java.util.List;

public record UserInfoResponse(
    String uuid,

    String username,

    String email,

    List<String> roles
) {
}
