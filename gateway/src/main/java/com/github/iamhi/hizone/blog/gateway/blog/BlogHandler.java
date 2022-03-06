package com.github.iamhi.hizone.blog.gateway.blog;

import com.github.iamhi.hizone.blog.api.requests.CreateBlogRequest;
import com.github.iamhi.hizone.blog.api.requests.UpdateBlogRequest;
import com.github.iamhi.hizone.blog.core.BlogService;
import com.github.iamhi.hizone.blog.core.UserService;
import com.github.iamhi.hizone.blog.core.models.BlogDTO;
import com.github.iamhi.hizone.blog.gateway.shared.CookieHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record BlogHandler(
    BlogService blogService,
    UserService userService,
    CookieHandler cookieHandler
) {

    Mono<ServerResponse> fetchBlogs(ServerRequest serverRequest) {
        return ServerResponse.ok().body(blogService.readBlogs(), BlogDTO.class);
    }

    Mono<ServerResponse> createBlog(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
            Mono.zip(
                cookieHandler.getUserToken(serverRequest.cookies()).flatMap(userService::isAdmin),
                serverRequest.bodyToMono(CreateBlogRequest.class)
            ).flatMap(tuple2 ->
                blogService.createBlog(
                    tuple2.getT2().title(),
                    tuple2.getT2().content(),
                    tuple2.getT2().mediaUrl(),
                    tuple2.getT2().mediaType(),
                    tuple2.getT1()
                )
            ),
            BlogDTO.class
        );
    }

    Mono<ServerResponse> updateBlog(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
            Mono.zip(
                cookieHandler.getUserToken(serverRequest.cookies()).flatMap(userService::isAdmin),
                serverRequest.bodyToMono(UpdateBlogRequest.class)
            ).flatMap(tuple2 ->
                blogService.updateBlog(new BlogDTO(
                        tuple2.getT2().uuid(),
                        tuple2.getT2().title(),
                        tuple2.getT2().content(),
                        tuple2.getT2().mediaUrl(),
                        tuple2.getT2().mediaType()
                    )
                )
            ),
            BlogDTO.class
        );
    }

    Mono<ServerResponse> deleteBlog(ServerRequest serverRequest) {
        return ServerResponse.ok().body(Mono.zip(
            cookieHandler.getUserToken(serverRequest.cookies()).flatMap(userService::isAdmin),
            Mono.just(serverRequest.pathVariable(BlogRouter.UUID_PATH))
        ).flatMap(tuple2 -> blogService.deleteBlog(tuple2.getT2())), BlogDTO.class);
    }
}
