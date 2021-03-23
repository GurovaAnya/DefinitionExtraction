package def.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected final ResponseEntity<Object> handleNotFoundExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    protected final ResponseEntity<Object> handleBadRequestExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAthenticatedException.class)
    protected final ResponseEntity<Object> handleUnauthorizedExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }
}
