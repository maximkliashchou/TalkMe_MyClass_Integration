package by.mmkle.proxy;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="AMO-SERVICE-PROXY", url="https://fvantop.amocrm.ru")
public interface AmoServiceProxy {

    @GetMapping(value = "/api/v4/account", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getInfo(@RequestHeader("Bearer") String token);

    @GetMapping("/api/v4/contacts")
    JSONObject getAllContacts(@RequestHeader("x-access-token") String token);

    @PostMapping(value = "/api/v4/contacts", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject addContact(@RequestHeader("x-access-token") String token, @RequestBody String body);

    @PostMapping(value = "/oauth2/access_token", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getAccessToken(@RequestBody String body);

    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjIzOWNkMWM4ZjFiZWZmYTNlNTcxMWM1NGMzYjRmM2M2NjhmMGUyZTdlYzc0YTUzZTNhZjExYzczODdhNWU5MjY0ZmIyNDczMTk3NDI3ODk1In0.eyJhdWQiOiI2MWZkYjdhYS01YmNjLTQ0YTgtYjY5Ni1jZDVhMDJlNDRiOWQiLCJqdGkiOiIyMzljZDFjOGYxYmVmZmEzZTU3MTFjNTRjM2I0ZjNjNjY4ZjBlMmU3ZWM3NGE1M2UzYWYxMWM3Mzg3YTVlOTI2NGZiMjQ3MzE5NzQyNzg5NSIsImlhdCI6MTU5NjgxODI2NSwibmJmIjoxNTk2ODE4MjY1LCJleHAiOjE1OTY5MDQ2NjUsInN1YiI6IjYyMTQzNTQiLCJhY2NvdW50X2lkIjoyODk4NTI4Nywic2NvcGVzIjpbInB1c2hfbm90aWZpY2F0aW9ucyIsImNybSIsIm5vdGlmaWNhdGlvbnMiXX0.gClBmMNk5rJaGqlIFrhAR83XyyTSlrkKU0wnPt_ehnWQklX7-h3nlsc5xkO9a9wRG7sZOPPjRh55bXnaWtUyV2ffnsjKp_dfLo3bynNwlS8vDcRUvsfwM5glot5xdVmmgAaKh6SK7V8QQjbYmmeso9QiUS3V6gyuCDXQq5nDc4zizephhPpT1UC-WM8CMTN1to52va5RidG4xL2PTVtFla8v5XZNxb8AChbEDp2vNJYk8dxWbQ8QIid5CPCjoynsMjzNuRWB0t8LcooG18KltQiqQYM2L-r8679Q22DB6HCfBXMvGCMoZ93Q-7Yab5fagXLMgqUZ6Mx2j5ZF25QwDA";
}
