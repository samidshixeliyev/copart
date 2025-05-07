package az.code.copart.dto.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.checkerframework.checker.index.qual.Positive;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link az.code.copart.entity.Car}
 */

@Getter
@Setter
@Builder
public class CarCreateRequest implements Serializable {
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
    @Length(max = 4000)
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
