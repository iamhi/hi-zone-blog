package com.github.iamhi.hizone.blog.api.requests;

public record CreateBlogRequest (
    String title,
    String content,
    String mediaUrl,
    String mediaType
) {
}
