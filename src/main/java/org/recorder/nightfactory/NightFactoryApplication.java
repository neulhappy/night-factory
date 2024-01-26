package org.recorder.nightfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NightFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(NightFactoryApplication.class, args);
    }

}


