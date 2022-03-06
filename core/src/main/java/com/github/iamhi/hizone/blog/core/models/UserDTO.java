package com.github.iamhi.hizone.blog.core.models;

import com.github.iamhi.hizone.blog.external.authentication.responses.UserInfoResponse;

import java.util.List;

public record UserDTO(
    String uuid,
    String username,
    List<String> roles
) {
    public static UserDTO fromUserInfoResponse(UserInfoResponse userInfoResponse) {
        return new UserDTO(
            userInfoResponse.uuid(),
            userInfoResponse.username(),
            userInfoResponse.roles()
        );
    }
}
