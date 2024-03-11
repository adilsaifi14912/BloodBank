package BloodBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "BloodBank")
public class VartikaJainBloodBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(VartikaJainBloodBankApplication.class, args);
	}

}
