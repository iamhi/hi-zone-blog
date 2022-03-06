package com.github.iamhi.hizone.blog.core;

import com.github.iamhi.hizone.blog.core.models.UserDTO;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDTO> isAdmin(String userToken);
}
