package by.mmkle.service;

import by.mmkle.bean.Result;
import by.mmkle.bean.User;
import by.mmkle.telegram.dispatcher.RequestDispatcher;
import by.mmkle.telegram.service.MessageService;
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

    @Autowired
    private MessageService messageService;

    public void initialize() throws IOException, ParseException, InterruptedException {
        List<User> allUsersFromMyClass = myClassService.getAllUser();
        while(true) {
            List<Result> allUsersFromTalkMe = talkMeService.getAllUsersFromTalkMe();

            for (Result userTalkMe : allUsersFromTalkMe) {
                boolean flag = false;
                for (User userMyClass : allUsersFromMyClass) {
                    if (userTalkMe.getPhone().equals(userMyClass.getPhone())) {
                        try {
                            if (userTalkMe.getTime().isAfter(userMyClass.getTime()) || userMyClass.getTime() == null) {
                                userMyClass.setTime(userTalkMe.getTime());
                                messageService.sendMessage(RequestDispatcher.chatId, messageService.getMessageWhereUpdateUser(userTalkMe));
                            }
                        } catch (Exception ex) {

                        }
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    User user = User.builder()
                            .name(userTalkMe.getName())
                            .phone(userTalkMe.getPhone())
                            .email("email@gmail.com")
                            //.email(userTalkMe.getEmail())
                            .time(userTalkMe.getTime())
                            .build();
                    myClassService.createUser(user);
                    allUsersFromMyClass.add(user);
                    messageService.sendMessage(RequestDispatcher.chatId, messageService.getMessageWhereCreateNewUser(userTalkMe));
                }
            }
            Thread.sleep(3600);
        }
    }
}
