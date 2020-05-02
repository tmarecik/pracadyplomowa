package pl.edu.wszib.pracadyplomowa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:db.properties")
public class PracadyplomowaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracadyplomowaApplication.class, args);
    }

}
