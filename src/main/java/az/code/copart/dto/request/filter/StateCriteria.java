package az.code.copart.dto.request.filter;

import lombok.Data;

import java.io.Serializable;

@Data
public class StateCriteria implements Serializable {
    private String name;
}
