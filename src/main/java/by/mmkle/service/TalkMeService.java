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
import java.util.concurrent.atomic.AtomicReference;

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


    public List<Result> getAllUsersFromTalkMe() throws IOException, ParseException {
        JSONObject result = talkMeServiceProxy.getUsers(getBody(), getToken());
        Object obj = new JSONParser().parse(result.toJSONString());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray resultList = (JSONArray) jsonObject.get("result");
        //JSONArray messagesList = (JSONArray)((JSONObject) jsonObject.get("result")).get("messages");

        Iterator<JSONObject> iterator = resultList.iterator();
        List<Result> resultList2 = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (iterator.hasNext()) {
            iterator.forEachRemaining(el -> {
                final String[] time = new String[1];
                JSONArray messageList = (JSONArray) el.get("messages");
                Iterator<JSONObject> messageIterator = messageList.iterator();
                messageIterator.forEachRemaining(message -> {
                    time[0] = ((String) message.get("dateTime"));
                });
                resultList2.add(Result.builder()
                        .email(String.valueOf(el.get("email")))
                        .name(String.valueOf(el.get("name")))
                        .phone(String.valueOf(el.get("phone")))
                        .time(LocalDateTime.parse(time[0], formatter))
                        .build());
            });
        }
        return resultList2;
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
