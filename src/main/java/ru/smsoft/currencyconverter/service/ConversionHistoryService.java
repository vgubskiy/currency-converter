package ru.smsoft.currencyconverter.service;

import org.springframework.stereotype.Service;
import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.model.Currency;
import ru.smsoft.currencyconverter.repository.ConversionHistoryRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ConversionHistoryService {
    private ConversionHistoryRepository conversionHistoryRepository;
    private CurrencyService currencyService;

    public ConversionHistoryService(ConversionHistoryRepository conversionHistoryRepository, CurrencyService currencyService) {
        this.conversionHistoryRepository = conversionHistoryRepository;
        this.currencyService = currencyService;
    }

    public void saveConversionHistory(Currency fromCurr, Currency toCurr, BigDecimal value, BigDecimal result) {
        conversionHistoryRepository.save(new ConversionHistory(fromCurr, toCurr, value, result, LocalDate.now()));
    }

    public List<ConversionHistory> getAllConvHist() {
        return conversionHistoryRepository.findAll();
    }

    public List<ConversionHistory> findConvHistFromToByDate(LocalDate date, Long from, Long to) {
        return conversionHistoryRepository.findAllByDateAndFromCurrAndToCurr(date, getCurrency(from), getCurrency(to));
    }

    public List<ConversionHistory> findConvHistFromTo(Long from, Long to) {
        return conversionHistoryRepository.findAllByFromCurrAndToCurr(getCurrency(from), getCurrency(to));
    }

    public List<ConversionHistory> findConvHistFromByDate(LocalDate date, Long from) {
        return conversionHistoryRepository.findAllByDateAndFromCurr(date, getCurrency(from));
    }

    public List<ConversionHistory> findConvHistToByDate(LocalDate date, Long to) {
        return conversionHistoryRepository.findAllByDateAndToCurr(date, getCurrency(to));
    }

    public List<ConversionHistory> findConvHistByDate(LocalDate date) {
        return conversionHistoryRepository.findAllByDate(date);
    }

    public List<ConversionHistory> findConvHistFrom(Long from) {
        return conversionHistoryRepository.findAllByFromCurr(getCurrency(from));
    }

    public List<ConversionHistory> findConvHistTo(Long to) {
        return conversionHistoryRepository.findAllByToCurr(getCurrency(to));
    }

    private Currency getCurrency(Long numCode) {
        return currencyService.findByNumCode(numCode);
    }
}
