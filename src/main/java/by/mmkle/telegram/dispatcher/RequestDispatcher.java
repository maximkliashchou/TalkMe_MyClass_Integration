package by.mmkle.telegram.dispatcher;

import by.mmkle.telegram.bot.BotCommand;
import by.mmkle.telegram.processors.HelpProcessor;
import by.mmkle.telegram.processors.SettingProcessor;
import by.mmkle.telegram.processors.StartProcessor;
import by.mmkle.telegram.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.mmkle.telegram.bot.BotCommand.*;

@Service
public class RequestDispatcher {
    public static Long chatId = 0L;

    @Autowired
    private MessageService messageService;

    @Autowired
    private HelpProcessor helpProcessor;

    @Autowired
    private SettingProcessor settingProcessor;

    @Autowired
    private StartProcessor startProcessor;

    public void dispatch(Update update) {
        chatId = update.getMessage().getChatId();
        switch (getCommand(update)) {
            case HELP:
                messageService.sendMessage(chatId, helpProcessor.run());
                break;
            case START:
                messageService.sendMessage(chatId, startProcessor.run());
                break;
            case SETTING:
                messageService.sendMessage(chatId, settingProcessor.run());
                break;
        }
    }

    private BotCommand getCommand(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                String msgText = message.getText();
                if (msgText.startsWith(HELP.getCommand())) {
                    return HELP;
                } else if (msgText.startsWith(START.getCommand())) {
                    return START;
                } else if (msgText.startsWith(SETTING.getCommand())) {
                    return SETTING;
                }
            }
        }
        return NONE;
    }
}