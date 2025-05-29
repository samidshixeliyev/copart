package az.code.copart.client.response.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
public class UserResponse implements Serializable {
    private Long id;
    private String username;
    private String email;
    private Set<RoleResponse> roles;
}
