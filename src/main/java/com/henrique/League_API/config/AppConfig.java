package com.henrique.League_API.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

<<<<<<< HEAD
    }
=======
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
