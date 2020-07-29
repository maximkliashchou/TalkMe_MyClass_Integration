package by.mmkle.telegram.processors;

import by.mmkle.telegram.interfaces.Processor;
import org.springframework.stereotype.Service;

@Service
public class HelpProcessor implements Processor {

    @Override
    public String run() {
        return "Сам себе помоги";
    }
}
