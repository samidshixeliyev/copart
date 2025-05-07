package az.code.copart.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityResponse implements Serializable {
    private Long id;
    private StateResponse state;
    private String name;
}

