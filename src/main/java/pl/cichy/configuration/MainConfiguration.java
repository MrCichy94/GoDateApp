package pl.cichy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MainConfiguration {

    @Bean
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}
