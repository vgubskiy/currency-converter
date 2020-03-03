package ru.smsoft.currencyconverter.web.binding;

public class SearchForm {
    private String conversionDate;
    private Long fromCurrNumCode;
    private Long toCurrNumCode;

    public String getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(String conversionDate) {
        this.conversionDate = conversionDate;
    }

    public Long getFromCurrNumCode() {
        return fromCurrNumCode;
    }

    public void setFromCurrNumCode(Long fromCurrNumCode) {
        this.fromCurrNumCode = fromCurrNumCode;
    }

    public Long getToCurrNumCode() {
        return toCurrNumCode;
    }

    public void setToCurrNumCode(Long toCurrNumCode) {
        this.toCurrNumCode = toCurrNumCode;
    }
}
