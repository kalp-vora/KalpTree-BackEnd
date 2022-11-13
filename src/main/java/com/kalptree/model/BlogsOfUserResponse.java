package com.kalptree.model;

import java.time.LocalDateTime;

public record BlogsOfUserResponse(Long blogId, Long userId,
                                  String title, String content, Integer categoryId, String categoryName,
                                  String penName, Long likeCount, Long funnyCount, Long insightfulCount,
                                  LocalDateTime creationDateTime, LocalDateTime writtenDateTime,
                                  LocalDateTime publishedDateTime, LocalDateTime modifiedDateTime) {
}
