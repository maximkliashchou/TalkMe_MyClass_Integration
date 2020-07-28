package by.mmkle.telegram.service;

import by.mmkle.bean.User;
import by.mmkle.telegram.bot.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessageService {

    @Autowired
    private Bot bot;


    public synchronized void sendMessage(Long chatId, String text) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId.toString());
            sendMessage.setText(text);
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.toString());
        }
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
