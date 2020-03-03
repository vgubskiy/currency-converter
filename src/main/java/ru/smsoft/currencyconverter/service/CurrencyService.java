package ru.smsoft.currencyconverter.service;

import org.springframework.stereotype.Service;
import ru.smsoft.currencyconverter.model.Currency;
import ru.smsoft.currencyconverter.repository.CurrencyRepository;
import java.util.List;

@Service
public class CurrencyService {
    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency findByNumCode(Long numCode) {
        return currencyRepository.findById(numCode).orElse(null);
    }

    public void saveAll(List<Currency> currencies) {
        currencyRepository.saveAll(currencies);
    }
}
