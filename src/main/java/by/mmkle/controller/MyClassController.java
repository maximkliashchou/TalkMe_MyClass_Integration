package by.mmkle.controller;

import by.mmkle.bean.User;
import by.mmkle.proxy.MyClassServiceProxy;
import by.mmkle.service.MyClassService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MyClassController {
    @Autowired
    private MyClassServiceProxy myClassServiceProxy;

    @Autowired
    private MyClassService myClassService;

    @GetMapping("/users")
    public List<User> getAllUser() throws IOException, ParseException {
        String token = myClassService.getToken();
        JSONObject result = myClassServiceProxy.getAllUser(token);
        Object obj = new JSONParser().parse(result.toJSONString());
        JSONObject jo = (JSONObject)obj;
        return (List<User>) jo.get("users");
    }
}
