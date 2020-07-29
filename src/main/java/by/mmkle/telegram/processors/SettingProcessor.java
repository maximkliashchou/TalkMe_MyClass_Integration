package by.mmkle.telegram.processors;

import by.mmkle.telegram.interfaces.Processor;
import org.springframework.stereotype.Service;

@Service
public class SettingProcessor implements Processor {

    @Override
    public String run() {
        return "Здесь вы можете изменить настройки API, а так же информацию которую хотите" +
                "видеть при получении сообщения о создании или обновлении. Подробнее /moresettings";
    }
}