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
        //String bodyForNewToken = "{\"apiKey\": \"avJLB4NIa3o6fEaTrJp48E06cFApHtDRODKDioz9u8RgodO8Hr\"}";
        String bodyForNewToken = "{\"apiKey\": \"YNPbbSyOeITHC3dsEmDgWIn3kldlLbGHvu9X7uNL75xmK9Z94u\"}";
        JSONObject jo = (JSONObject)new JSONParser().parse(myClassServiceProxy.getToken(bodyForNewToken).toJSONString());
        return (String) jo.get("accessToken");
    }

    public void createUser(User user) throws ParseException {
        myClassServiceProxy.createUser(getToken(), generateBodyForCreatingNewUser(user));
    }

    public List<User> getAllUser() throws IOException, ParseException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(myClassServiceProxy.getAllUser(getToken()).toJSONString());
        JSONArray userList = (JSONArray) jsonObject.get("users");
        Iterator<JSONObject> iteratorForUserListFromMyClass = userList.iterator();
        List<User> newUserListAfterRefactoring = new ArrayList<>();
        while (iteratorForUserListFromMyClass.hasNext()) {
            iteratorForUserListFromMyClass.forEachRemaining(el -> {
                newUserListAfterRefactoring.add(User.builder()
                        .email(String.valueOf(el.get("email")))
                        .name(String.valueOf(el.get("name")))
                        .phone(String.valueOf(el.get("phone")))
                        .id(Integer.valueOf(String.valueOf(el.get("id"))))
                        .build());
            });
        }
        return newUserListAfterRefactoring;
    }

    public void updateUserStatus(User user) throws ParseException {
        myClassServiceProxy.updateUserStatus(getToken(), generateBodyForUpdateUserStatus(user), user.getId());
    }

    public String generateBodyForCreatingNewUser(User user){
        return "{\n" +
                "  \"name\": \""+user.getName()+"\",\n" +
                getDefaultEmail(user.getEmail()) +
                "  \"clientStateId\": null,\n" +
                getDefaultPhone(user.getPhone()) + "}";

    }

    public String generateBodyForUpdateUserStatus(User user){
        return "{\"statusId\": "+ 62149 +",\"statusChangeReasonId\": "+ 22 +"}";
    }

    public String getDefaultEmail(String email){
        return (email == "null") ? "" : "  \"email\": \""+email+"\",\n";
    }

    public String getDefaultPhone(String phone){
        return  (phone == "null") ? "" :  "  \"phone\": \""+phone+"\"\n";
    }

}
