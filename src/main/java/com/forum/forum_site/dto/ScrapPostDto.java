package com.forum.forum_site.dto;

import java.time.LocalDateTime;

public record ScrapPostDto (
        Integer postId,
        String title,
        String content,
        LocalDateTime createdAt,
        Integer likesCount
) {}