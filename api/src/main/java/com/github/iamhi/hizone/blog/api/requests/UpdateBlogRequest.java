package com.github.iamhi.hizone.blog.api.requests;

public record UpdateBlogRequest(
    String uuid,
    String title,
    String content,
    String mediaUrl,
    String mediaType
) {
}
