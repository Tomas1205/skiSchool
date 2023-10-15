package sk.backend.skiSchool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class SkiSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkiSchoolApplication.class, args);
		System.out.println("Hello world!");
	}	
}
