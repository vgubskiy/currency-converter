package ru.smsoft.currencyconverter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

}
