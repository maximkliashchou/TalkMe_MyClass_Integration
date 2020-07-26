package by.mmkle.controller;

import by.mmkle.bean.User;
import by.mmkle.service.InitializationService;
import by.mmkle.service.MyClassService;
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

    @GetMapping("/users")
    public List<User> getAllUser() throws IOException, ParseException {
        return myClassService.getAllUser();
    }
}
