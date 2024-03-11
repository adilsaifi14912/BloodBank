package BloodBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestVartikaJainBloodBankApplication {

	public static void main(String[] args) {
		SpringApplication.from(VartikaJainBloodBankApplication::main).with(TestVartikaJainBloodBankApplication.class).run(args);
	}

}
