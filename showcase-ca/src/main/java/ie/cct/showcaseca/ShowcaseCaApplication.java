package ie.cct.showcaseca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ie.cct.showcaseca")
public class ShowcaseCaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowcaseCaApplication.class, args);
	}

}
