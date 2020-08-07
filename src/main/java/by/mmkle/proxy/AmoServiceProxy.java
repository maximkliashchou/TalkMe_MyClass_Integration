package by.mmkle.proxy;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="AMO-SERVICE-PROXY", url="https://fvantop.amocrm.ru")
public interface AmoServiceProxy {

    @GetMapping("/api/v4/contacts")
    JSONObject getAllContacts();

    @GetMapping("/api/v4/account")
    JSONObject getInfo();

}
