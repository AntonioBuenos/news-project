package by.smirnov.newsproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class NewsRequest {

    private Timestamp time;
    private String title;
    private String text;
}
