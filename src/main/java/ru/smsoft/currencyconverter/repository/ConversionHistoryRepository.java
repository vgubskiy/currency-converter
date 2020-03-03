package ru.smsoft.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.model.Currency;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {
    List<ConversionHistory> findAllByDateAndFromCurrAndToCurr(LocalDate date, Currency from, Currency to);
    List<ConversionHistory> findAllByDateAndFromCurr(LocalDate date, Currency from);
    List<ConversionHistory> findAllByDateAndToCurr(LocalDate date, Currency to);
    List<ConversionHistory> findAllByDate(LocalDate date);
    List<ConversionHistory> findAllByFromCurrAndToCurr(Currency from, Currency to);
    List<ConversionHistory> findAllByFromCurr(Currency from);
    List<ConversionHistory> findAllByToCurr(Currency to);
}
