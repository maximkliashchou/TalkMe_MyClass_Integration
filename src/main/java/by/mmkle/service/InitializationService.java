package by.mmkle.service;

import by.mmkle.bean.Result;
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

    public void initialize() throws IOException, ParseException {
        while(true){
            List<Result> resultList = talkMeService.getAllUsersFromTalkMe();
            resultList.stream().forEach(el ->{

            });

        }
    }
}
