package ru.smsoft.currencyconverter.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
@javax.persistence.Entity
public class Currency {

    @XmlAttribute(name="Cb_Id")
    private String id;

    @Id
    @XmlElement(name = "NumCode")
    private Long numCode;

    @XmlElement(name = "CharCode")
    private String charCode;

    @XmlElement(name = "Nominal")
    private BigDecimal nominal;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Value")
    private String value;

    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromCurr", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConversionHistory> convertFromHistoryList = new LinkedList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toCurr", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConversionHistory> convertToHistoryList = new LinkedList<>();

    public Currency() {
    }

    public Currency(String id,
                    Long numCode,
                    String charCode,
                    BigDecimal nominal,
                    String name,
                    String value,
                    LocalDate date) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public void addConversionFromHistory(ConversionHistory conversionHistory) {
        convertFromHistoryList.add(conversionHistory);
        conversionHistory.setFromCurr(this);
    }

    public void addConversionToHistory(ConversionHistory conversionHistory) {
        convertToHistoryList.add(conversionHistory);
        conversionHistory.setToCurr(this);
    }

    public void removeConversionFromHistory(ConversionHistory conversionHistory) {
        convertFromHistoryList.remove(conversionHistory);
        conversionHistory.setFromCurr(null);
    }

    public void removeConversionToHistory(ConversionHistory conversionHistory) {
        convertToHistoryList.remove(conversionHistory);
        conversionHistory.setToCurr(null);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Currency currency = (Currency) o;
        return getNumCode().equals(currency.getNumCode())
                       && getCharCode().equals(currency.getCharCode())
                       && getNominal().equals(currency.getNominal())
                       && getName().equals(currency.getName())
                       && getValue().equals(currency.getValue())
                       && getDate().equals(currency.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumCode(), getCharCode(), getNominal(), getName(), getValue(), getDate());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNumCode() {
        return numCode;
    }

    public void setNumCode(Long numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ConversionHistory> getConvertFromHistoryList() {
        return convertFromHistoryList;
    }

    public void setConvertFromHistoryList(List<ConversionHistory> convertFromHistoryList) {
        this.convertFromHistoryList = convertFromHistoryList;
    }

    public List<ConversionHistory> getConvertToHistoryList() {
        return convertToHistoryList;
    }

    public void setConvertToHistoryList(List<ConversionHistory> convertToHistoryList) {
        this.convertToHistoryList = convertToHistoryList;
    }
}
