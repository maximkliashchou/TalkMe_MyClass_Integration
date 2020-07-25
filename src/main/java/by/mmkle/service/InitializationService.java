package by.mmkle.service;

import by.mmkle.bean.Result;
import by.mmkle.bean.User;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class InitializationService {

    @Autowired
    private MyClassService myClassService;

    @Autowired
    private TalkMeService talkMeService;

    public void initialize() throws IOException, ParseException, InterruptedException {
        while(true){
            List<Result> resultList = talkMeService.getAllUsersFromTalkMe();

            System.out.println(resultList);
            for (int i = 0; i < resultList.size(); i++){
                LocalDateTime messageTime = resultList.get(i).getTime();
                LocalDateTime timeNow = LocalDateTime.now();
                Duration duration = Duration.between(messageTime,timeNow);
                if (duration.toMinutes() <= 6){
                    myClassService.createUser(User.builder()
                            .name(resultList.get(i).getName())
                            .email(resultList.get(i).getEmail())
                            .phone(resultList.get(i).getPhone())
                            .build()
                    );
                }
            }
            Thread.sleep(300000);
        }
    }
}
