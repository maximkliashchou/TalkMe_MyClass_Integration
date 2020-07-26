package by.mmkle.service;

import by.mmkle.bean.Result;
import by.mmkle.proxy.TalkMeServiceProxy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TalkMeService {

    @Autowired
    private TalkMeServiceProxy talkMeServiceProxy;

    public List<Result> getAllUsersFromTalkMe() throws IOException, ParseException {
        Object obj = new JSONParser().parse(talkMeServiceProxy.getUsers(getBodyForNewDay(), getToken()).toJSONString());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray resultList = (JSONArray) jsonObject.get("result");

        Iterator<JSONObject> iterator = resultList.iterator();
        List<Result> results = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (iterator.hasNext()) {
            iterator.forEachRemaining(el -> {
                final String[] time = new String[1];
                JSONArray messageList = (JSONArray) el.get("messages");
                Iterator<JSONObject> messageIterator = messageList.iterator();
                messageIterator.forEachRemaining(message -> {
                    time[0] = ((String) message.get("dateTime"));
                });
                results.add(Result.builder()
                        .email(String.valueOf(el.get("email")))
                        .name(String.valueOf(el.get("name")))
                        .phone(String.valueOf(el.get("phone")))
                        .time(LocalDateTime.parse(time[0], formatter))
                        .build());
            });
        }
        return results;
    }

    public String getToken(){
        return "4e48gxhw1rq8mzsrl0ylfnup1uuj3x43qvq2c7oc3q4i8bn0udic525ruirmuyao";
    }

    public static String getNewDay(){
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    }

    public String getBodyForNewDay(){
        return "{\n" +
                "  \"dateRange\": {\n" +
                "    \"start\": \"" + getNewDay() + "\",\n" +
                "    \"stop\": \"" + getNewDay() + "\"\n" +
                "  }\n" +
                "}";
    }


}
