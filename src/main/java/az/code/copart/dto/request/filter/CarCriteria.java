package az.code.copart.dto.request.filter;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarCriteria implements Serializable {
    private String maker;
    private String model;
    private String fuelType;
    private String carType;
    private String city;
    private String state;
    private Long userId;
    private Integer fromYear;
    private Integer toYear;
    private Double fromMileage;
    private Double toMileage;
    private Double price;
    private String color;
}
