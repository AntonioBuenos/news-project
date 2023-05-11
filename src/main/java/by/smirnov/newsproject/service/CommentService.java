package by.smirnov.newsproject.service;

import by.smirnov.newsproject.domain.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    Comment findById(Long id);

    List<Comment> findAll(Pageable pageable);

    Comment create(Comment object);

    Comment update(Comment toBeUpdated);

    Comment delete(Long id);

    void hardDelete(Long id);
}
