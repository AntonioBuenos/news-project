package by.smirnov.newsproject.dto;

import by.smirnov.newsproject.domain.News;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentRequest {

    private long id;
    private Timestamp time;
    private String text;
    private String username;
    private News newsItem;
}
