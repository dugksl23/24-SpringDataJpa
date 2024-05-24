package study.springdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import study.springdatajpa.auditor.UserAuditorAware;

import java.util.Optional;
import java.util.UUID;


@EnableJpaAuditing
@SpringBootApplication
public class SpringdatajpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaApplication.class, args);
    }


    @Bean
    public AuditorAware<String> auditorProvider() {
        return new UserAuditorAware();
    }

}
