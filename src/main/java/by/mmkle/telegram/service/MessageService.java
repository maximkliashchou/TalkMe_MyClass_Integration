package by.mmkle.telegram.service;

import by.mmkle.bean.User;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public String getAnswerForStart(){
        return "Привет, для начала работы нам нужно API от Talk-Me и 'Мой класс'";
    }

    public String getMessageWhereCreateNewUser(User user){
        return "СОЗДАН КЛИЕНТ\nВ систему 'Мой класс' был добавлен новый пользователь\n" + "Имя: " +
                user.getName() + "\nEmail: " + user.getEmail() + "\nТелефон: " + user.getPhone();
    }

    public String getMessageWhereUpdateUser(User user){
        return "КЛИЕНТ ОБНОВЛЕН\nВ систему 'Мой класс' был обновлен пользователь\n" + "Имя: " +
                user.getName() + "\nEmail: " + user.getEmail() + "\nТелефон: " + user.getPhone();
    }
}
