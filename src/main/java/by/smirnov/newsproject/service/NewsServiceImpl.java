package by.smirnov.newsproject.service;

import by.smirnov.newsproject.domain.News;
import by.smirnov.newsproject.exception.NoSuchEntityException;
import by.smirnov.newsproject.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository repository;

    @Override
    public News findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(NoSuchEntityException::new);
    }

    @Override
    public List<News> findAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Transactional
    @Override
    public News create(News object) {
        return repository.save(object);
    }

    @Transactional
    @Override
    public News update(News toBeUpdated) {
        return repository.save(toBeUpdated);
    }

    @Transactional
    @Override
    public News delete(Long id) {
        News toBeDeleted = repository
                .findById(id)
                .orElseThrow(NoSuchEntityException::new);
       return repository.save(toBeDeleted);
    }

    @Transactional
    @Override
    public void hardDelete(Long id){
        repository.deleteById(id);
    }
}
