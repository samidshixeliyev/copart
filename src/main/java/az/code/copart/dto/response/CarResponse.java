package az.code.copart.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
public class CarResponse {
    private Long id;
    private MakerResponse maker;
    private ModelResponse model;
    private CarTypeResponse carType;
    private FuelTypeResponse fuelType;
    private CityResponse city;
    private UserResponse user;
    private Object carFeature;
    private Integer year;
    private Double price;
    private String color;
    private String vin;
    private Double mileage;
    private String address;
    private String description;
    private List<FileResponse> fileResponses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
