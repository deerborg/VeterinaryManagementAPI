package art.dborg.vetapp;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Veterinary API",version = "1.0",description = "VetApp"))
public class VetAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetAppApplication.class, args);
	}

}
