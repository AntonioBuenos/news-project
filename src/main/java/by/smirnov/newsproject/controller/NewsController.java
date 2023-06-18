package by.smirnov.newsproject.controller;

import by.smirnov.newsproject.domain.News;
import by.smirnov.newsproject.dto.NewsConverter;
import by.smirnov.newsproject.dto.NewsRequest;
import by.smirnov.newsproject.dto.NewsResponse;
import by.smirnov.newsproject.exception.BadRequestException;
import by.smirnov.newsproject.exception.ErrorContainer;
import by.smirnov.newsproject.exception.NoSuchEntityException;
import by.smirnov.newsproject.service.NewsService;
import by.smirnov.newsproject.validation.ValidationErrorConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static by.smirnov.newsproject.controller.CommonControllerConstants.PAGE_SIZE;
import static by.smirnov.newsproject.controller.CommonControllerConstants.PAGE_SORT;
import static by.smirnov.newsproject.controller.ControllerConstants.DELETED;
import static by.smirnov.newsproject.controller.ControllerConstants.ID;
import static by.smirnov.newsproject.controller.ControllerConstants.MAPPING_ID;
import static by.smirnov.newsproject.controller.ControllerConstants.MAPPING_NEWS;
import static by.smirnov.newsproject.controller.ControllerConstants.NEWS;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAPPING_NEWS)
@Tag(
        name = "News controller",
        description = "This controller is responsible for the CRUD operations with news"
)
public class NewsController {

    private final NewsService service;

    private final NewsConverter converter;

    @GetMapping
    public ResponseEntity<Map<String, List<NewsResponse>>> index(@PageableDefault(sort = PAGE_SORT, size = PAGE_SIZE)
                                                                     Pageable pageable) {
        List<NewsResponse> responses = service.findAll(pageable)
                .stream()
                .map(converter::convert)
                .toList();
        return new ResponseEntity<>(Collections.singletonMap(NEWS, responses), HttpStatus.OK);
    }

    @Operation(
            method = "GET",
            summary = "Finding a news unit by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Request"),
                    @ApiResponse(responseCode = "400", description = "Bad Request. " +
                            "All page numbers must be integers, separated by comas", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ErrorContainer.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "Unexpected Internal Server Error", content =
                    @Content)
            },
            description = "This method gets news unit by id"
    )
    @GetMapping(MAPPING_ID)
    public ResponseEntity<NewsResponse> show(@PathVariable(ID) long id) {

        News tag = service.findById(id);
        NewsResponse response = converter.convert(tag);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid NewsRequest request,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ValidationErrorConverter.getErrors(bindingResult).toString());
        }

        News tag = converter.convert(request);
        News created = service.create(tag);
        NewsResponse response = converter.convert(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(MAPPING_ID)
    public ResponseEntity<NewsResponse> update(@PathVariable(name = ID) Long id,
                                              @RequestBody @Valid NewsRequest request,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ValidationErrorConverter.getErrors(bindingResult).toString());
        }

        News tag = converter.convert(request);
        News changed = service.update(tag);
        NewsResponse response = converter.convert(changed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(MAPPING_ID)
    public ResponseEntity<Map<String, Long>> delete(@PathVariable(ID) long id) {

        if (Objects.isNull(service.findById(id))) throw new NoSuchEntityException();
        service.delete(id);
        return new ResponseEntity<>(Map.of(DELETED, id), HttpStatus.OK);
    }
}
