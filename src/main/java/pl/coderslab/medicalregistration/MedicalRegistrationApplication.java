package pl.coderslab.medicalregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.annotation.WebFilter;

@SpringBootApplication
public class MedicalRegistrationApplication implements WebMvcConfigurer {



	public static void main(String[] args) {
		SpringApplication.run(MedicalRegistrationApplication.class, args);
	}

}
