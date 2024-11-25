package mk.ukim.finki.mendo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MendoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MendoApplication.class, args);
    }

}
