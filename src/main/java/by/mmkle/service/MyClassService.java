package by.mmkle.service;

import by.mmkle.bean.User;
import by.mmkle.proxy.MyClassServiceProxy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyClassService {
    @Autowired
    private MyClassServiceProxy myClassServiceProxy;

    public String getToken() throws ParseException {
        String body = "{\"apiKey\": \"avJLB4NIa3o6fEaTrJp48E06cFApHtDRODKDioz9u8RgodO8Hr\"}";
        JSONObject result = myClassServiceProxy.getToken(body);
        Object obj = new JSONParser().parse(result.toJSONString());
        JSONObject jo = (JSONObject)obj;
        return (String) jo.get("accessToken");
    }

    private void revokeToken(String token, String body) {
        myClassServiceProxy.revokeToken(token, body);
    }

    public void createUser(User user) throws ParseException {
        String body = "{\"name\": \"" + user.getName() + "\"}";
        String token = getToken();
        JSONObject result = myClassServiceProxy.createUser(token, body);
        Object obj = new JSONParser().parse(result.toJSONString());
        JSONObject jo = (JSONObject)obj;
        user.setId((Long) jo.get("id"));
        System.out.println(user);
    }

    public void updateUser(User user) throws ParseException {
        String body = "{\"name\": \""+ user.getName() +"\",\"email\": \""+user.getEmail()+"\"}";
        String token = getToken();
        myClassServiceProxy.updateUser(token, body, user.getId());
    }
}
