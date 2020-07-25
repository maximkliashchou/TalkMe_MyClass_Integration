package by.mmkle.proxy;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name="MY-CLASS-SERVICE-PROXY", url="https://api.moyklass.com")
public interface MyClassServiceProxy {
    @GetMapping("/v1/company/users")
    JSONObject getAllUser(@RequestHeader("x-access-token") String token);

    @PostMapping(value = "/v1/company/auth/getToken", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getToken(@RequestBody String body);

    @PostMapping(value = "/v1/company/auth/revokeToken", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject revokeToken(@RequestHeader("x-access-token") String token, @RequestBody String body);

    @PostMapping(value = "/v1/company/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject createUser(@RequestHeader("x-access-token") String token, @RequestBody String body);

    @PostMapping(value = "/v1/company/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject updateUser(@RequestHeader("x-access-token") String token,
                          @RequestBody String body,
                          @RequestParam("userId") Long id);
}
