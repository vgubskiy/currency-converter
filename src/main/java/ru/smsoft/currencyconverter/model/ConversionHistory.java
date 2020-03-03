package ru.smsoft.currencyconverter.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@javax.persistence.Entity
public class ConversionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="from_curr_numCode")
    @NotNull
    private Currency fromCurr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="to_curr_numCode")
    @NotNull
    private Currency toCurr;

    @NotNull
    private BigDecimal value;

    @NotNull
    private BigDecimal result;

    @NotNull
    private LocalDate date;

    public ConversionHistory() {
    }

    public ConversionHistory(Currency fromCurr, Currency toCurr, BigDecimal value, BigDecimal result, LocalDate date) {
        this.fromCurr = fromCurr;
        this.toCurr = toCurr;
        this.value = value;
        this.result = result;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        ConversionHistory that = (ConversionHistory) o;
        return getFromCurr().equals(that.getFromCurr())
                       && getToCurr().equals(that.getToCurr())
                       && getValue().equals(that.getValue())
                       && getResult().equals(that.getResult())
                       && getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFromCurr(), getToCurr(), getValue(), getResult(), getDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getFromCurr() {
        return fromCurr;
    }

    public void setFromCurr(Currency fromCurr) {
        this.fromCurr = fromCurr;
    }

    public Currency getToCurr() {
        return toCurr;
    }

    public void setToCurr(Currency toCurr) {
        this.toCurr = toCurr;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
