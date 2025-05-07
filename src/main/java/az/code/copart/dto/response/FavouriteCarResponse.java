package az.code.copart.dto.response;

import az.code.copart.entity.FavouriteCar;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavouriteCarResponse implements Serializable {
    private Long id;
    private CarResponse car;
    private UserResponse user;
}
