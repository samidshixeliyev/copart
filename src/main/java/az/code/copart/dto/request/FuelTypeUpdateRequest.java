package az.code.copart.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class FuelTypeUpdateRequest implements Serializable {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
}
