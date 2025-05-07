package az.code.copart.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T>{
    private String uuid;
    private String message;
    private T data;
    private int status;
}
