package az.code.copart.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class FavouriteCarUpdateRequest implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long carId;
}
