package by.mmkle.controller;

import by.mmkle.bean.Result;
import by.mmkle.bean.User;
import by.mmkle.proxy.TalkMeServiceProxy;
import by.mmkle.service.TalkMeService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
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
        JSONObject jo = (JSONObject) obj;
        List<Result> resultList = (List<Result>) jo.get("result");
        List<Result> resultList2 = new ArrayList<>();
        for (int i = 0; i < resultList.size(); i++) {
           /* resultList2.add(Result.builder()
                    .email(String.valueOf(resultList.get(i).getEmail()))
                    .name(String.valueOf(resultList.get(i).getName()))
                    .phone(String.valueOf(resultList.get(i).getPhone()))
                    .build());*/
        }
        return resultList2;
    }
}
