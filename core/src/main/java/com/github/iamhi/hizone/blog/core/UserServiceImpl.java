package com.github.iamhi.hizone.blog.core;

import com.github.iamhi.hizone.blog.core.exceptions.UserIsNotAdminThrowable;
import com.github.iamhi.hizone.blog.core.models.UserDTO;
import com.github.iamhi.hizone.blog.external.authentication.AuthenticationClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
record UserServiceImpl(
    AuthenticationClient authenticationClient
) implements UserService {

    private static final String ADMIN_USER_ROLE = "ADMIN";

    @Override
    public Mono<UserDTO> isAdmin(String userToken) {
        return authenticationClient.fetchUserInfo(userToken)
            .map(UserDTO::fromUserInfoResponse)
            .flatMap(this::hasAdminRole);
    }

    Mono<UserDTO> hasAdminRole(UserDTO userDTO) {
        return userDTO.roles().contains(ADMIN_USER_ROLE) ? Mono.just(userDTO) : Mono.error(new UserIsNotAdminThrowable());
    }
}
