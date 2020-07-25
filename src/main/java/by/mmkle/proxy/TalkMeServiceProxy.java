package by.mmkle.proxy;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="TALK-ME-SERVICE-PROXY", url="https://lcab.talk-me.ru/json/v1.0")
public interface TalkMeServiceProxy {

    @PostMapping(value = "/chat/message/getList", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getUsers(@RequestBody String body, @RequestHeader("X-Token") String token);

}
