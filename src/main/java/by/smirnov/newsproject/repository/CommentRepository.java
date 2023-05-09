package by.smirnov.newsproject.repository;

import by.smirnov.newsproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends
        CrudRepository<Comment, Long>,
        JpaRepository<Comment, Long> {
}
