package az.code.copart.dto.request.filter;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModelCriteria implements Serializable {
    private String name;
    private String makerName;
    private Long makerId;
}
