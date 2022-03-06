package com.github.iamhi.hizone.blog.core.models;

import com.github.iamhi.hizone.blog.data.BlogEntity;

import java.time.Instant;

public record BlogDTO(
    String uuid,
    String title,
    String content,
    String mediaUrl,
    String mediaType,
    Instant createdAt,
    Instant updatedAt,
    String createdBy
) {
    public BlogDTO(
        String uuid,
        String title,
        String content,
        String mediaUrl,
        String mediaType
    ) {
        this(
            uuid,
            title,
            content,
            mediaUrl,
            mediaType,
            null,
            null,
            ""
        );
    }

    public static BlogDTO fromEntity(BlogEntity blogEntity) {
        return new BlogDTO(
            blogEntity.uuid(),
            blogEntity.title(),
            blogEntity.content(),
            blogEntity.mediaUrl(),
            blogEntity.mediaType(),
            blogEntity.createdAt(),
            blogEntity.updatedAt(),
            blogEntity.createdBy()
        );
    }
}
