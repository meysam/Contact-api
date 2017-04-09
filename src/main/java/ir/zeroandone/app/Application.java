package ir.zeroandone.app;

import ir.zeroandone.app.domain.Person;
import ir.zeroandone.app.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application  {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

  /*  @Override
    public void run(String... strings) throws Exception {
        for (int i = 0; i < 5; i++) {
            repository.save(new Person("Meysam" + (i+1)));
        }

}*/
}