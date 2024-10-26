package dio.api.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "dio.api.ecommerce.model.mysql.repository")
@ComponentScan(basePackages = "dio.api.ecommerce.model.mysql.service")
@ComponentScan(basePackages = "dio.api.ecommerce.controller")
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
