package by.mmkle.telegram.processors;

import by.mmkle.telegram.interfaces.Processor;
import org.springframework.stereotype.Service;

@Service
public class SettingProcessor implements Processor {

    @Override
    public String run() {
        return "Nothing you can set now, sorry...";
    }
}