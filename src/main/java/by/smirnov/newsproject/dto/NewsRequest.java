package by.smirnov.newsproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class NewsRequest {

    @PastOrPresent
    private Timestamp time;

    @NotBlank
    private String title;

    @NotBlank
    private String text;
}
