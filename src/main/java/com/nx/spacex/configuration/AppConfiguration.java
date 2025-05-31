package com.nx.spacex.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static com.nx.spacex.configuration.HibernateConfig.getRestTemplate;

public class AppConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return getRestTemplate();
    }
}
