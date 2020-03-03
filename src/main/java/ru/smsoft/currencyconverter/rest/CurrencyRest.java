package ru.smsoft.currencyconverter.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import ru.smsoft.currencyconverter.model.CurrencyDataList;

@RestController
public class CurrencyRest {

    private WebClient.Builder webClientBuilder;

    @Value("${cbr.url}")
    private String cbrUrl;

    public CurrencyRest(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public CurrencyDataList getCurrencyData() {
        return webClientBuilder.build()
                              .get()
                              .uri(cbrUrl)
                              .retrieve()
                              .bodyToMono(CurrencyDataList.class)
                              .block();
    }
}
