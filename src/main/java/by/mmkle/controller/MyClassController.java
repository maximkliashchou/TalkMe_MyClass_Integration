package by.mmkle.controller;

import by.mmkle.bean.Result;
import by.mmkle.bean.User;
import by.mmkle.proxy.MyClassServiceProxy;
import by.mmkle.service.MyClassService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray userList = (JSONArray) jsonObject.get("users");

        Iterator<JSONObject> iterator = userList.iterator();
        List<User> userList2 = new ArrayList<>();
        while (iterator.hasNext()) {
            iterator.forEachRemaining(el -> {
                String phoneNumber = String.valueOf(el.get("phone"));
                String name = String.valueOf(el.get("name"));
                String email = String.valueOf(el.get("email"));
                userList2.add(User.builder()
                        .email(email)
                        .name(name)
                        .phone(phoneNumber)
                        .build());

            });
        }
        return userList2;
    }
}
