package by.mmkle.telegram.processors;

import by.mmkle.telegram.interfaces.Processor;
import org.springframework.stereotype.Service;

@Service
public class StartProcessor implements Processor {

    @Override
    public String run() {
        return "Привет, для правильной работы мне нужно API сервиса Talk-Me и Мой класс.\n";
    }
}
