package fr.uge.jee.web.service.reddIGM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories("fr.uge.jee.web.service.reddIGM.repositories")
public class ReddIgmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReddIgmApplication.class, args);
    }

}
