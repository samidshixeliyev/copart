package az.code.copart.client.response.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RoleResponse implements Serializable {
    private Long id;
    private String name;
}
