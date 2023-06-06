package by.smirnov.newsproject.dto;

import by.smirnov.newsproject.domain.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentConverter {

    Comment convert(CommentRequest request);
    CommentResponse convert(Comment object);
}
