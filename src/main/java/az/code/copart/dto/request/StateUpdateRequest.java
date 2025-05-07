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
public class StateUpdateRequest implements Serializable {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
}
