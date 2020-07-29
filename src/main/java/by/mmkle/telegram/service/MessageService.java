package by.mmkle.telegram.service;

import by.mmkle.bean.Result;
import by.mmkle.bean.User;
import by.mmkle.telegram.bot.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.format.DateTimeFormatter;

@Service
public class MessageService {

    @Autowired
    private Bot bot;

    private static DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

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

    public String getMessageWhereCreateNewUser(Result result){
        return "СОЗДАН КЛИЕНТ\nВ систему 'Мой класс' был добавлен новый пользователь\n\n" + "Имя: " +
                result.getName() + "\nEmail: " + result.getEmail() + "\nТелефон: " + result.getPhone()
                + "\nПоследнее сообщение: " + result.getMessage()
                + "\nВремя: " + parser.format(result.getTime());
    }

    public String getMessageWhereUpdateUser(Result result){
        return "ПОВТОРНОЕ ОБРАЩЕНИЕ\nВ системе 'Мой класс' был обновлен пользователь\n\n" + "Имя: " +
                result.getName() + "\nEmail: " + result.getEmail() + "\nТелефон: " + result.getPhone()
                + "\nПоследнее сообщение: " + result.getMessage()
                + "\nВремя: " + parser.format(result.getTime());
    }
}
