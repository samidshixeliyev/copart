package az.code.copart.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CarUpdateRequest implements Serializable {
    @NotNull(message = "must be send id")
    private Long id;
    @NotNull
    @Positive
    private Integer year;
    @NotNull
    @Positive
    private Double price;
    private String color;
    private String vin;
    private Double mileage;
    private String address;
    @Max(value = 4000)
    private String description;
    private Object carFeature;
    private Boolean deleted;
    @NotNull
    private Long carTypeId;
    @NotNull
    private Long fuelTypeId;
    @NotNull
    private Long userId;
    @NotNull
    private Long cityId;
    @NotNull
    private Long modelId;
    @NotNull
    private Long makerId;
}
