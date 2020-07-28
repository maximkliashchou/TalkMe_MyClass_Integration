package by.mmkle;

import by.mmkle.service.InitializationService;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;

import java.io.IOException;

@SpringBootApplication
@EnableFeignClients
public class MyClassApplication{

	public static void main(String[] args) throws ParseException, IOException, InterruptedException {
		ApiContextInitializer.init();
		ApplicationContext ctx = SpringApplication.run(MyClassApplication.class, args);
		InitializationService service = ctx.getBean(InitializationService.class);
		service.initialize();
	}

}
