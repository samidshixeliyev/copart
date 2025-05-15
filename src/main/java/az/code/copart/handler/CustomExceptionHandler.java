package az.code.copart.handler;


import az.code.copart.dto.response.BaseResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@RestControllerAdvice
@Hidden
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        ex.printStackTrace();
        return ResponseEntity.status(ex.getCode())
                .body(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .status(ex.getCode())
                        .message(ex.getMessage()+" bu ise her zaman custom exceptiondir")
                        .build());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage() + " \n Burada yazilan umumi exception")
                        .build());
    }
}
