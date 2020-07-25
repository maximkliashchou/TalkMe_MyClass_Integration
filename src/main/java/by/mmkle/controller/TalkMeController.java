package by.mmkle.controller;

import by.mmkle.bean.Result;
import by.mmkle.bean.User;
import by.mmkle.proxy.TalkMeServiceProxy;
import by.mmkle.service.TalkMeService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class TalkMeController {

    @Autowired
    private TalkMeService talkMeService;

    @Autowired
    private TalkMeServiceProxy talkMeServiceProxy;

    @GetMapping("/usersTalkMe")
    public List<Result> getAllUsersFromTalkMe() throws IOException, ParseException {
        JSONObject result = talkMeServiceProxy.getUsers(talkMeService.getBody(), talkMeService.getToken());
        Object obj = new JSONParser().parse(result.toJSONString());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray resultList = (JSONArray) jsonObject.get("result");

        Iterator<JSONObject> iterator = resultList.iterator();
        List<Result> resultList2 = new ArrayList<>();
        while (iterator.hasNext()) {
            iterator.forEachRemaining(el -> {
                String phoneNumber = String.valueOf(el.get("phone"));
                String name = String.valueOf(el.get("name"));
                String email = String.valueOf(el.get("email"));
                resultList2.add(Result.builder()
                    .email(email)
                    .name(name)
                    .phone(phoneNumber)
                    .build());
//                if (checkForNullPhoneNumber(phoneNumber)) {
//                    collectionFromTalkMe.put(email, name);
//                } else {
//                    collectionFromTalkMe.put(phoneNumber, name);
//                }
            });
        }
        return resultList2;
    }
}
