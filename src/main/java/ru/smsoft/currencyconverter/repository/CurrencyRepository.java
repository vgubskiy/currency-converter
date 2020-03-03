package ru.smsoft.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smsoft.currencyconverter.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
