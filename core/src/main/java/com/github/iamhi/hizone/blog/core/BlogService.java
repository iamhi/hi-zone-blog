package com.github.iamhi.hizone.blog.core;

import com.github.iamhi.hizone.blog.core.models.BlogDTO;
import com.github.iamhi.hizone.blog.core.models.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlogService {

    Mono<BlogDTO> createBlog(
        String title,
        String content,
        String mediaUrl,
        String mediaType,
        UserDTO userDTO);

    Flux<BlogDTO> readBlogs();

    Mono<BlogDTO> readBlog(String uuid);

    Mono<BlogDTO> updateBlog(BlogDTO blogDTO);

    Mono<BlogDTO> deleteBlog(String uuid);
}
