package com.github.iamhi.hizone.blog.gateway.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BlogRouter {

    public static final String UUID_PATH = "uuid";

    private static final String ROUTER_PREFIX = "";
    private static final String DELETE_BLOG_ROUTE = ROUTER_PREFIX + "/{" + UUID_PATH + "}";

    @Bean
    public RouterFunction<ServerResponse> userRouterCompose(BlogHandler blogHandler) {
        return route(GET(ROUTER_PREFIX), blogHandler::fetchBlogs)
            .andRoute(POST(ROUTER_PREFIX), blogHandler::createBlog)
            .andRoute(PUT(ROUTER_PREFIX), blogHandler::updateBlog)
            .andRoute(DELETE(DELETE_BLOG_ROUTE), blogHandler::deleteBlog);
    }
}
