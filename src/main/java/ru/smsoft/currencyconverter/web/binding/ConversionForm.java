package ru.smsoft.currencyconverter.web.binding;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ConversionForm {
    @NotNull(message = "Выберете исходную валюту")
    private Long fromCurrencyNumCode;

    @NotNull(message = "Выберете целевую валюту")
    private Long toCurrencyNumCode;

    @NotNull(message = "Укажите значение")
    @DecimalMin("1.0")
    private BigDecimal value;
    private BigDecimal result;

    public Long getFromCurrencyNumCode() {
        return fromCurrencyNumCode;
    }

    public void setFromCurrencyNumCode(Long fromCurrencyNumCode) {
        this.fromCurrencyNumCode = fromCurrencyNumCode;
    }

    public Long getToCurrencyNumCode() {
        return toCurrencyNumCode;
    }

    public void setToCurrencyNumCode(Long toCurrencyNumCode) {
        this.toCurrencyNumCode = toCurrencyNumCode;
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
}
