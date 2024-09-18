package pl.dziadkouskaya.search_server.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;
import static pl.dziadkouskaya.search_server.utils.Constants.*;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<RestErrorResponse> handleApplicationException(ApplicationException ex, WebRequest request) {
        return handleInternal(ex, INTERNAL_SERVER_ERROR, ERROR_SERVER_INTERNAL);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<RestErrorResponse> handleBindException(BindException ex, WebRequest request) {
        return handleInternal(ex, BAD_REQUEST, getValidationMessages(ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<RestErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        return handleInternal(ex, BAD_REQUEST, getValidationMessages(ex.getBindingResult()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return handleInternal(ex, HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(ResourceExistedException.class)
    public ResponseEntity<RestErrorResponse> handleResourceNotFoundException(ResourceExistedException ex, WebRequest request) {
        return handleInternal(ex, FOUND, RESOURCE_EXISTED);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return handleInternal(ex, FORBIDDEN, ERROR_ACCESS_DENIED);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        return handleInternal(ex, HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<RestErrorResponse> handleBadArgumentRequest(RuntimeException ex, WebRequest request) {
        return handleInternal(ex, BAD_REQUEST, ex.getMessage());
    }

    private List<String> getValidationMessages(BindingResult ex) {
        return ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(toList());
    }

    private ResponseEntity<RestErrorResponse> handleInternal(Exception ex, HttpStatus status, String error) {
        log.error(error, ex);
        return new ResponseEntity<>(new RestErrorResponse(error), new HttpHeaders(), status);
    }

    private ResponseEntity<RestErrorResponse> handleInternal(HttpStatus status, String error) {
        log.error(error);
        return new ResponseEntity<>(new RestErrorResponse(error), new HttpHeaders(), status);
    }

    private ResponseEntity<RestErrorResponse> handleInternal(HttpStatus status, List<String> errors) {
        log.error(String.join(",", errors));
        return new ResponseEntity<>(new RestErrorResponse(errors), new HttpHeaders(), status);
    }

    private ResponseEntity<RestErrorResponse>
    handleInternal(Exception ex, HttpStatus status, List<String> errors) {
        log.error(String.join(",", errors), ex);
        return new ResponseEntity<>(new RestErrorResponse(errors), new HttpHeaders(), status);
    }

}
