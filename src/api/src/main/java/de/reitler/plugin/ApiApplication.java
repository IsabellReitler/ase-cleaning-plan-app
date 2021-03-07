package de.reitler.plugin;

import de.reitler.plugin.household.HouseholdController;
import de.reitler.plugin.login.LoginController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"de.reitler.application.**", "de.reitler.plugin.**"})
@EntityScan(basePackages = {"de.reitler.domain.entities"})
@EnableJpaRepositories(basePackages = {"de.reitler.domain.repositories"})
//@EnableJpaRepositories
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	/**@Bean
	ApplicationRunner init(HouseholdRepository householdRepository){
		return args -> {
			householdRepository.save(new Household("default"));
		};
	}**/

}
