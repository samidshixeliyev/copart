package az.code.copart.config;

import az.code.copart.handler.CustomException;
import az.code.copart.handler.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
@Component
@RequiredArgsConstructor
public class RetreiveMessageErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorResponse message = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ErrorResponse.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        return switch (response.status()) {
            case 401 -> new CustomException(message.getMessage() != null ? message.getMessage() : "Unauthorized", 401);
            case 403 -> new CustomException(message.getMessage() != null ? message.getMessage() : "Forbidden", 403);
            case 400 -> new CustomException(message.getMessage() != null ? message.getMessage() : "Bad Request");
            case 404 -> new CustomException(message.getMessage() != null ? message.getMessage() : "Not found");
            case 500 -> new CustomException(message.getMessage() != null ? message.getMessage() : "Internal Server Error", 500);
            default ->  new CustomException(message.getMessage() != null ? message.getMessage() : "Unknown Error", response.status());
        };
    }
}
