package az.code.copart.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelResponse implements Serializable {
    private Long id;
    private String name;
    private MakerResponse maker;
}
