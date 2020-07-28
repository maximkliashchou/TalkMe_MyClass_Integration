package by.mmkle.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@PropertySource("application.properties")
@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;

    @Autowired
    private BotService botService;

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        botService.sendMessage(update.getMessage(), message);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}