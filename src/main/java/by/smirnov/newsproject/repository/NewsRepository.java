package by.smirnov.newsproject.repository;

import by.smirnov.newsproject.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends
        CrudRepository<News, Long>,
        JpaRepository<News, Long> {
}
