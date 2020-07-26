package by.mmkle.service;

import by.mmkle.bean.Result;
import by.mmkle.bean.User;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class InitializationService {

    @Autowired
    private MyClassService myClassService;

    @Autowired
    private TalkMeService talkMeService;

    public void initialize() throws IOException, ParseException, InterruptedException {
        while(true) {
            List<Result> allUsersFromTalkMe = talkMeService.getAllUsersFromTalkMe();
            List<User> allUsersFromMyClass = myClassService.getAllUser();

            for (Result userTalkMe : allUsersFromTalkMe) {
                boolean flag = false;
                for (User userMyClass : allUsersFromMyClass) {
                    if (userTalkMe.getPhone().equals(userMyClass.getPhone())) {
                        try {
                            myClassService.updateUserStatus(userMyClass.getId());
                        } catch (Exception ex) {
                            System.out.println("Ne bei Max");
                        }
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    User user = User.builder()
                            .name(userTalkMe.getName())
                            .phone(userTalkMe.getPhone())
                            .email(userTalkMe.getEmail())
                            .build();
                    myClassService.createUser(user);
                }
            }
            Thread.sleep(3600);
        }
    }
}
