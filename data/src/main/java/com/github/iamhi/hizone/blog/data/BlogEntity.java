package com.github.iamhi.hizone.blog.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public record BlogEntity(

    @Id
    String uuid,

    String title,

    String content,

    String mediaUrl,

    String mediaType,

    Instant createdAt,

    Instant updatedAt,

    String createdBy
) {
}
