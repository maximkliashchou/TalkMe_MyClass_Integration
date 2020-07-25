package by.mmkle;

import by.mmkle.bean.User;
import by.mmkle.service.MyClassService;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MyClassApplication{

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(MyClassApplication.class, args);
	}

}
