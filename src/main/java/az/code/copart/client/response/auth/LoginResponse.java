package az.code.copart.client.response.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
}
