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

    @GetMapping("/usersTalkMe")
    public List<Result> getAllUsersFromTalkMe() throws IOException, ParseException {
        return talkMeService.getAllUsersFromTalkMe();
    }
}
