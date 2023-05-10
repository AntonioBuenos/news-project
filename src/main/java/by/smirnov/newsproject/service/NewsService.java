package by.smirnov.newsproject.service;

import by.smirnov.newsproject.domain.News;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {

    News findById(Long id);

    List<News> findAll(Pageable pageable);

    News create(News object);

    News update(News toBeUpdated);

    News delete(Long id);

    void hardDelete(Long id);
}
