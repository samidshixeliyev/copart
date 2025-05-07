package az.code.copart.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class MakerCreateRequest implements Serializable {
    @NotBlank
    private String name;
}
