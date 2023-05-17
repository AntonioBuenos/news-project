package by.smirnov.newsproject.service;

import by.smirnov.newsproject.domain.Comment;
import by.smirnov.newsproject.exception.NoSuchEntityException;
import by.smirnov.newsproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    public Comment findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(NoSuchEntityException::new);
    }

    @Override
    public List<Comment> findAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Transactional
    @Override
    public Comment create(Comment object) {
        return repository.save(object);
    }

    @Transactional
    @Override
    public Comment update(Comment toBeUpdated) {
        return repository.save(toBeUpdated);
    }

    @Transactional
    @Override
    public Comment delete(Long id) {
        Comment toBeDeleted = repository
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
