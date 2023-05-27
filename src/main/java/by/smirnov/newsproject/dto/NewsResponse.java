package by.smirnov.newsproject.dto;

import by.smirnov.newsproject.domain.Comment;

import java.sql.Timestamp;
import java.util.List;

public class NewsResponse extends NewsRequest{

    private long id;
    private List<Comment> comments;
}
