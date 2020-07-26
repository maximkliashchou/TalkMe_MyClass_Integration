package by.mmkle.service;

import by.mmkle.bean.Result;
import by.mmkle.bean.User;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitializationService {

    @Autowired
    private MyClassService myClassService;

    @Autowired
    private TalkMeService talkMeService;

    public void initialize() throws IOException, ParseException, InterruptedException {
        while(true){
            List<Result> allUsersFromTalkMe = talkMeService.getAllUsersFromTalkMe();
            List<User> allUsersFromMyClass = myClassService.getAllUser();
            List<User> possibleUser = new ArrayList<>();
            for (int i = 0; i < allUsersFromTalkMe.size(); i++){
                LocalDateTime messageTime = allUsersFromTalkMe.get(i).getTime();
                LocalDateTime timeNow = LocalDateTime.now();
                Duration duration = Duration.between(messageTime,timeNow);
                if (duration.toMinutes() >= 5) {
                    possibleUser.add(User.builder()
                            .name(allUsersFromTalkMe.get(i).getName())
                            .email(allUsersFromTalkMe.get(i).getEmail())
                            .phone(allUsersFromTalkMe.get(i).getPhone())
                            .build()
                    );
                }
            }
            for (int i = 0; i < possibleUser.size(); i++) {
                boolean flag = false;
                for (int j = 0; j < allUsersFromMyClass.size(); j++) {
                    if (possibleUser.get(i).getPhone().equals(allUsersFromMyClass.get(j).getPhone())
                            || possibleUser.get(i).getEmail().equals(allUsersFromMyClass.get(j).getEmail())){
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    myClassService.createUser(possibleUser.get(i));
                } else {
                    myClassService.updateUserStatus(possibleUser.get(i));
                }
            }
            Thread.sleep(30000);
        }
    }
}
