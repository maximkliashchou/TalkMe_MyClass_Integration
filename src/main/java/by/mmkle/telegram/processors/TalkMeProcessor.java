package by.mmkle.telegram.processors;

import by.mmkle.telegram.interfaces.Processor;
import org.springframework.stereotype.Service;

@Service
public class TalkMeProcessor implements Processor {
    @Override
    public String run() {
        return "Успешно сохранено!";
    }
}
