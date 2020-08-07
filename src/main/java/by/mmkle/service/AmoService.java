package by.mmkle.service;

import by.mmkle.bean.User;
import by.mmkle.proxy.AmoServiceProxy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AmoService {

    @Autowired
    private AmoServiceProxy amoServiceProxy;

    public List<User> getAllContactsFromAmo() throws ParseException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(amoServiceProxy.getAllContacts(AmoServiceProxy.token).toJSONString());
        JSONObject embedded = (JSONObject) jsonObject.get("embedded");
        JSONArray contacts = (JSONArray) embedded.get("contacts");
        Iterator<JSONObject> iteratorForUserListFromAmo = contacts.iterator();
        List<User> newUserListAfterRefactoring = new ArrayList<>();
        while (iteratorForUserListFromAmo.hasNext()) {
            iteratorForUserListFromAmo.forEachRemaining(el -> {
                newUserListAfterRefactoring.add(User.builder()
                        .id(Integer.valueOf(String.valueOf(el.get("id"))))
                        .name(String.valueOf(el.get("name")))
                        .phone(String.valueOf(el.get("phone")))
                        .email(String.valueOf(el.get("email")))
                        .build());
            });
        }
        return newUserListAfterRefactoring;
    }

    public String getInfoAboutAccount() {
        return amoServiceProxy.getInfo(AmoServiceProxy.token).toString();
    }

    public String createBodyForNewUser(User user) {
        return "[{\n" +
                "            \"first_name\": \"" + getDefaultEmail(user) + "\",\n" +
               // "                    \"last_name\": \"Смирнов\",\n" +
                "                    \"custom_fields_values\": [\n" +
                "            {\n" +
                "                \"field_id\": 44201,\n" +
                "                    \"field_name\": \"Телефон\",\n" +
                "                    \"field_code\": \"PHONE\",\n" +
                "                    \"field_type\": \"multitext\",\n" +
                "                    \"values\": [\n" +
                "                {\n" +
                "                    \"value\": \""+ getDefaultPhone(user) + "\",\n" +
                "                        \"enum_id\": 64021,\n" +
                "                        \"enum_code\": \"WORK\"\n" +
                "                }\n" +
                "                        ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"field_id\": 44203,\n" +
                "                    \"field_name\": \"Email\",\n" +
                "                    \"field_code\": \"EMAIL\",\n" +
                "                    \"field_type\": \"multitext\",\n" +
                "                    \"values\": [\n" +
                "                {\n" +
                "                    \"value\": \""+ getDefaultName(user) +"\",\n" +
                "                        \"enum_id\": 64033,\n" +
                "                        \"enum_code\": \"WORK\"\n" +
                "                }\n" +
                "                        ]\n" +
                "            }\n" +
                "                ]\n" +
                "\n" +
                "        }]\n" +
                "    }\n" +
                "}]";
    }

    public String getDefaultEmail(User user){
       return (user.getEmail() == "") ? "" : user.getEmail();
    }

    public String getDefaultPhone(User user){
        return (user.getPhone() == "") ? "" : user.getPhone();
    }

    public String getDefaultName(User user){
        return (user.getName() == "") ? "" : user.getName();
    }

}
