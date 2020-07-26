package by.mmkle.service;

import by.mmkle.bean.User;
import by.mmkle.proxy.MyClassServiceProxy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MyClassService {
    @Autowired
    private MyClassServiceProxy myClassServiceProxy;

    public String getToken() throws ParseException {
        String bodyForNewToken = "{\"apiKey\": \"avJLB4NIa3o6fEaTrJp48E06cFApHtDRODKDioz9u8RgodO8Hr\"}";
        Object obj = new JSONParser().parse(myClassServiceProxy.getToken(bodyForNewToken).toJSONString());
        JSONObject jo = (JSONObject)obj;
        System.out.println((String) jo.get("accessToken"));
        return (String) jo.get("accessToken");
    }

    private void revokeToken(String token, String body) {
        myClassServiceProxy.revokeToken(token, body);
    }

    public void createUser(User user) throws ParseException {
        String body = "{\n" +
                "  \"name\": \""+user.getName()+"\",\n" +
                "  \"email\": \""+user.getEmail()+"\",\n" +
                "  \"clientStateId\": \""+ 62148 +"\",\n" +
                "  \"phone\": \""+user.getPhone()+"\"\n" +
                "}";
        myClassServiceProxy.createUser(getToken(), body);
    }

    public void updateUser(User user) throws ParseException {
        String body = "{\"name\": \""+ user.getName() +"\",\"email\": \""+user.getEmail()+"\"}";
        myClassServiceProxy.updateUser(getToken(), body, user.getId());
    }

    public List<User> getAllUser() throws IOException, ParseException {
        JSONObject result = myClassServiceProxy.getAllUser(getToken());

        Object obj = new JSONParser().parse(result.toJSONString());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray userList = (JSONArray) jsonObject.get("users");
        Iterator<JSONObject> iteratorForUserListFromMyClass = userList.iterator();
        List<User> newUserListAfterRefactoring = new ArrayList<>();
        while (iteratorForUserListFromMyClass.hasNext()) {
            iteratorForUserListFromMyClass.forEachRemaining(el -> {
                newUserListAfterRefactoring.add(User.builder()
                        .email(String.valueOf(el.get("email")))
                        .name(String.valueOf(el.get("name")))
                        .phone(String.valueOf(el.get("phone")))
                        .id(Long.valueOf(String.valueOf(el.get("id"))))
                        .build());
            });
        }
        return newUserListAfterRefactoring;
    }

    public void updateUserStatus(User user) throws ParseException {
        String body = "{\"statusId\": "+ 62149 +",\"statusChangeReasonId\": "+ 22 +"}";
        myClassServiceProxy.updateUserStatus(getToken(), body, user.getId());
    }

}
