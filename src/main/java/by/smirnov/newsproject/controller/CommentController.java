package by.smirnov.newsproject.controller;

import by.smirnov.newsproject.domain.Comment;
import by.smirnov.newsproject.dto.CommentConverter;
import by.smirnov.newsproject.dto.CommentRequest;
import by.smirnov.newsproject.dto.CommentResponse;
import by.smirnov.newsproject.exception.BadRequestException;
import by.smirnov.newsproject.exception.ErrorContainer;
import by.smirnov.newsproject.exception.NoSuchEntityException;
import by.smirnov.newsproject.service.CommentService;
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
import org.springframework.validation.annotation.Validated;
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
import static by.smirnov.newsproject.controller.ControllerConstants.MAPPING_COMMENTARIES;
import static by.smirnov.newsproject.controller.ControllerConstants.MAPPING_ID;
import static by.smirnov.newsproject.controller.ControllerConstants.NEWS;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAPPING_COMMENTARIES)
@Validated
@Tag(
        name = "Comment controller",
        description = "This controller is responsible for the CRUD operations with news commentaries"
)
public class CommentController {

    private final CommentService service;

    private final CommentConverter converter;

    @Operation(
            method = "GET",
            summary = "Returns a list of all comments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Request"),
                    @ApiResponse(responseCode = "400", description = "Bad Request. ", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ErrorContainer.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "Unexpected Internal Server Error", content =
                    @Content)
            },
            description = "This method gets list of all comments"
    )
    @GetMapping
    public ResponseEntity<Map<String, List<CommentResponse>>> index(@PageableDefault(sort = PAGE_SORT, size = PAGE_SIZE)
                                                                 Pageable pageable) {
        List<CommentResponse> responses = service.findAll(pageable)
                .stream()
                .map(converter::convert)
                .toList();
        return new ResponseEntity<>(Collections.singletonMap(NEWS, responses), HttpStatus.OK);
    }

    @Operation(
            method = "GET",
            summary = "Finding a commentary by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Request"),
                    @ApiResponse(responseCode = "400", description = "Bad Request. " ,
                            content = {
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
    public ResponseEntity<CommentResponse> show(@PathVariable(ID) long id) {

        Comment tag = service.findById(id);
        CommentResponse response = converter.convert(tag);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            method = "POST",
            summary = "Creates a new comment",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Entity created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request. ", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ErrorContainer.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "Unexpected Internal Server Error", content =
                    @Content)
            },
            description = "This method creates a new comment"
    )
    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid CommentRequest request,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ValidationErrorConverter.getErrors(bindingResult).toString());
        }

        Comment tag = converter.convert(request);
        Comment created = service.create(tag);
        CommentResponse response = converter.convert(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            method = "PUT",
            summary = "Changes a commentary",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Entity created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request. ", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ErrorContainer.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "Unexpected Internal Server Error", content =
                    @Content)
            },
            description = "This method changes a commentary"
    )
    @PutMapping(MAPPING_ID)
    public ResponseEntity<CommentResponse> update(@PathVariable(name = ID) Long id,
                                               @RequestBody @Valid CommentRequest request,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ValidationErrorConverter.getErrors(bindingResult).toString());
        }

        Comment tag = converter.convert(request);
        Comment changed = service.update(tag);
        CommentResponse response = converter.convert(changed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(MAPPING_ID)
    public ResponseEntity<Map<String, Long>> delete(@PathVariable(ID) long id) {

        if (Objects.isNull(service.findById(id))) throw new NoSuchEntityException();
        service.delete(id);
        return new ResponseEntity<>(Map.of(DELETED, id), HttpStatus.OK);
    }
}
