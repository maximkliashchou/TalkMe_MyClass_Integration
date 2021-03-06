package by.mmkle.controller;

import by.mmkle.bean.Client;
import by.mmkle.bean.User;
import by.mmkle.service.InitializationService;
import by.mmkle.service.MyClassService;
import by.mmkle.telegram.dao.service.TelegramService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MyClassController {

    @Autowired
    private MyClassService myClassService;

    @Autowired
    private TelegramService telegramService;

    @GetMapping("/users")
    public List<User> getAllUser() throws IOException, ParseException {
        return myClassService.getAllUser();
    }

    @GetMapping("/clients")
    public List<Client> getAllClients(){
        return telegramService.clientList();
    }
}
