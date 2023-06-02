package by.smirnov.newsproject.dto;

import by.smirnov.newsproject.domain.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsConverter {

    News convert(NewsRequest request);
    NewsResponse convert(News object);
}
