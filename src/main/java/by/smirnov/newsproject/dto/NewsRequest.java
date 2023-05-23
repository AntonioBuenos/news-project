package by.smirnov.newsproject.dto;

import by.smirnov.newsproject.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class NewsRequest {

    private long id;
    private Timestamp time;
    private String title;
    private String text;
    private List<Comment> comments;
}
