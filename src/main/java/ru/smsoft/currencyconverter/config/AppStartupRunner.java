package ru.smsoft.currencyconverter.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.smsoft.currencyconverter.service.Converter;


@Component
public class AppStartupRunner implements ApplicationRunner {

    private Converter converter;

    public AppStartupRunner(Converter converter) {
        this.converter = converter;
    }

    @Override
    public void run(ApplicationArguments args) {
        converter.actualizeCurrencyData();
    }
}
