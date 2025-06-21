package az.code.copart.dto.request.filter;

import lombok.Data;

import java.io.Serializable;


import java.io.Serializable;
@Data
public class FavouriteCarCriteria implements Serializable {
    private Long name;
    private Long stateId;
    private String stateName;
}