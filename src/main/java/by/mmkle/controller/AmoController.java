package by.mmkle.controller;

import by.mmkle.service.AmoService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmoController {

    @Autowired
    private AmoService amoService;

    @GetMapping("/amo/contacts")
    public String getContactsFroAmo() throws ParseException {
        return amoService.getAllContactsFromAmo().toString();
    }

    @GetMapping("/amo/info")
    public String getInfoAboutAccount(){
        return amoService.getInfoAboutAccount();
    }
}
