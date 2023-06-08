package by.smirnov.newsproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentRequest {

    @PastOrPresent
    private Timestamp time;

    @NotBlank
    private String text;

    @NotBlank
    private String username;

}
