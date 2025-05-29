package az.code.copart.client;

import az.code.copart.client.response.auth.UserResponse;
import az.code.copart.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "ms-auth", url = "${client.ms-auth.endpoint}",configuration = FeignClientInterceptorConfig.class)
public interface AuthClient {
    @GetMapping("/user/all")
    List<UserResponse> getUsers();

    @GetMapping("/user/{userId}")
    UserResponse getUserById(@PathVariable(name = "userId") Long userId);

    @PostMapping("/auth/access")
    Void checkAccess(@RequestHeader(name="access-token") String accessToken);
}
