package by.smirnov.newsproject.dto;

import by.smirnov.newsproject.domain.News;

public class CommentResponse extends CommentRequest{

    private long id;
    private News newsItem;
}
