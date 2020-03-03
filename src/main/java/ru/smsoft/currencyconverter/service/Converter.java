package ru.smsoft.currencyconverter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.smsoft.currencyconverter.model.Currency;
import ru.smsoft.currencyconverter.model.CurrencyDataList;
import ru.smsoft.currencyconverter.rest.CurrencyRest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Converter {
    private CurrencyService currencyService;
    private ConversionHistoryService conversionHistoryService;
    private CurrencyRest currencyRest;

    @Value("${cbr.date.format}")
    private String cbrDateFormat;

    public Converter(CurrencyService currencyService,
                     ConversionHistoryService conversionHistoryService,
                     CurrencyRest currencyRest) {
        this.currencyService = currencyService;
        this.conversionHistoryService = conversionHistoryService;
        this.currencyRest = currencyRest;
    }

    public BigDecimal convert(Long fromCurrencyNumCode, Long toCurrencyNumCode, BigDecimal value) {
        Currency fromCurrency = currencyService.findByNumCode(fromCurrencyNumCode);
        Currency toCurrency = currencyService.findByNumCode(toCurrencyNumCode);
        validateActualCurrencyData(fromCurrency);
        validateActualCurrencyData(toCurrency);
        BigDecimal fromCurrencyValue = new BigDecimal(fromCurrency.getValue().replace(",", "."));
        BigDecimal toCurrencyValue = new BigDecimal(toCurrency.getValue().replace(",", "."));
        BigDecimal result = value.multiply(fromCurrencyValue)
                                    .divide(toCurrencyValue, 2, RoundingMode.HALF_UP);
        conversionHistoryService.saveConversionHistory(fromCurrency, toCurrency, value, result);
        return result;
    }

    private void validateActualCurrencyData(Currency currency) {
        if (currency.getDate().isBefore(LocalDate.now())) {
            actualizeCurrencyData();
        }
    }

    public void actualizeCurrencyData() {
        CurrencyDataList currencyDataList = currencyRest.getCurrencyData();
        LocalDate currencyDataDate = LocalDate.parse(currencyDataList.getDate(),
                DateTimeFormatter.ofPattern(cbrDateFormat));
        List<Currency> currencyList = currencyDataList.getCurrencyList()
                               .parallelStream()
                               .peek(c -> c.setDate(currencyDataDate))
                               .collect(Collectors.toList());
        currencyList.add(new Currency("", 643L, "RUB", BigDecimal.ONE, "Российский рубль", "1", LocalDate.now()));
        currencyService.saveAll(currencyList);
    }
}
