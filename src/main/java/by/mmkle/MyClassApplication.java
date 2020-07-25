package by.mmkle;

import by.mmkle.bean.User;
import by.mmkle.service.InitializationService;
import by.mmkle.service.MyClassService;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
@EnableFeignClients
public class MyClassApplication{

	public static void main(String[] args) throws ParseException, IOException, InterruptedException {
		ApplicationContext ctx = SpringApplication.run(MyClassApplication.class, args);
		InitializationService service = ctx.getBean(InitializationService.class);
		service.initialize();
	}

}
