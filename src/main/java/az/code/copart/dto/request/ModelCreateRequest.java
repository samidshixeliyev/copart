package az.code.copart.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ModelCreateRequest implements Serializable {
    @NotBlank
    private String name;
    @NotNull
    private Long makerId;
}
