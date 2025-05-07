package az.code.copart.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateRequest implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
//    @Pattern(regexp = "a-z") burada sizin regexiniz ola bilerdi
    private String password;
    @NotBlank
    @Email
    private String email;
}
