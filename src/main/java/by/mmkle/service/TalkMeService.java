package by.mmkle.service;

import by.mmkle.bean.User;
import by.mmkle.proxy.TalkMeServiceProxy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TalkMeService {

    @Autowired
    private TalkMeServiceProxy talkMeServiceProxy;

    public String getAllMessages() throws ParseException {
        JSONObject result = talkMeServiceProxy.getUsers(getBody(), getToken());
        Object obj = new JSONParser().parse(result.toJSONString());
        JSONObject jo = (JSONObject)obj;
        return String.valueOf(jo.get("result"));
    }

    public String getToken(){
        return "4e48gxhw1rq8mzsrl0ylfnup1uuj3x43qvq2c7oc3q4i8bn0udic525ruirmuyao";
    }

    public static String getNewDay(){
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    }

    public String getBody(){
        return "{\n" +
                "  \"dateRange\": {\n" +
                "    \"start\": \"" + getNewDay() + "\",\n" +
                "    \"stop\": \"" + getNewDay() + "\"\n" +
                "  }\n" +
                "}";
    }


}
