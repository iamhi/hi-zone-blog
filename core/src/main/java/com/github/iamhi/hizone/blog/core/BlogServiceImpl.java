package com.github.iamhi.hizone.blog.core;

import com.github.iamhi.hizone.blog.core.models.BlogDTO;
import com.github.iamhi.hizone.blog.core.models.UserDTO;
import com.github.iamhi.hizone.blog.data.BlogEntity;
import com.github.iamhi.hizone.blog.data.BlogRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
record BlogServiceImpl(
    BlogRepository blogRepository
) implements BlogService {
    @Override
    public Mono<BlogDTO> createBlog(String title, String content, String mediaUrl, String mediaType, UserDTO userDTO) {
        return blogRepository.save(
            new BlogEntity(
                UUID.randomUUID().toString(),
                title,
                content,
                mediaUrl,
                mediaType,
                Instant.now(),
                Instant.now(),
                userDTO.uuid()
            )
        ).map(BlogDTO::fromEntity);
    }

    @Override
    public Flux<BlogDTO> readBlogs(int page, int size) {
        return blogRepository
            .findByUuidNotNull(
                PageRequest.of(
                    page,
                    size,
                    Sort.by(Sort.Direction.DESC, "createdAt")))
            .map(BlogDTO::fromEntity);
    }

    @Override
    public Mono<BlogDTO> readBlog(String uuid) {
        return blogRepository.findById(uuid).map(BlogDTO::fromEntity);
    }

    @Override
    public Mono<BlogDTO> updateBlog(BlogDTO blogDTO) {
        return blogRepository.findById(blogDTO.uuid())
            .map(
                blogEntity -> new BlogEntity(
                    blogDTO.uuid(),
                    StringUtils.isBlank(blogDTO.title()) ? blogEntity.title() : blogDTO.title(),
                    StringUtils.isBlank(blogDTO.content()) ? blogEntity.content() : blogDTO.content(),
                    StringUtils.isBlank(blogDTO.mediaUrl()) ? blogEntity.mediaUrl() : blogDTO.mediaUrl(),
                    StringUtils.isBlank(blogDTO.mediaType()) ? blogEntity.mediaType() : blogDTO.mediaType(),
                    blogEntity.createdAt(),
                    Instant.now(),
                    blogDTO.createdBy()
                ))
            .flatMap(blogRepository::save)
            .map(BlogDTO::fromEntity);
    }

    @Override
    public Mono<BlogDTO> deleteBlog(String uuid) {
        return blogRepository.findById(uuid).map(BlogDTO::fromEntity)
            .flatMap(
                blogDTO -> Mono.zip(
                    Mono.just(blogDTO),
                    blogRepository.deleteById(uuid)).then(Mono.just(blogDTO))
            );
    }
}
