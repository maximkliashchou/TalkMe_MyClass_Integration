package by.mmkle.telegram.dispatcher;

import by.mmkle.telegram.bot.BotCommand;
import by.mmkle.telegram.processors.*;
import by.mmkle.telegram.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.mmkle.telegram.bot.BotCommand.*;

@Service
public class RequestDispatcher {
    public static Long chatId = 0L;
    public static String ApiTalkMe = new String();
    public static String ApiMyClass = new String();


    @Autowired
    private MessageService messageService;

    @Autowired
    private HelpProcessor helpProcessor;

    @Autowired
    private SettingProcessor settingProcessor;

    @Autowired
    private StartProcessor startProcessor;

    @Autowired
    private TalkMeProcessor talkMeProcessor;

    @Autowired
    private MyClassProcessor myClassProcessor;

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
            case TALKME:
                messageService.sendMessage(chatId, talkMeProcessor.run());
                break;
            case MYCLASS:
                messageService.sendMessage(chatId, myClassProcessor.run());
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
                } else if (msgText.startsWith(TALKME.getCommand())) {
                    ApiTalkMe = msgText.substring(8, msgText.length());
                    //System.out.println("апи для talk-me " + ApiTalkMe);
                    return TALKME;
                }else if (msgText.startsWith(MYCLASS.getCommand())) {
                    ApiMyClass= msgText.substring(9, msgText.length());
                    //System.out.println("апи для мой класс " + ApiMyClass);
                    return MYCLASS;
                }
            }
        }
        return NONE;
    }
}