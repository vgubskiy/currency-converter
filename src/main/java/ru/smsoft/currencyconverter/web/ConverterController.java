package ru.smsoft.currencyconverter.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.smsoft.currencyconverter.web.binding.ConversionForm;
import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.model.Currency;
import ru.smsoft.currencyconverter.web.binding.SearchForm;
import ru.smsoft.currencyconverter.service.ConversionHistoryService;
import ru.smsoft.currencyconverter.service.Converter;
import ru.smsoft.currencyconverter.service.CurrencyService;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ConverterController {
    private Converter converter;
    private ConversionHistoryService conversionHistoryService;
    private CurrencyService currencyService;

    public ConverterController(Converter converter, ConversionHistoryService conversionHistoryService,
                               CurrencyService currencyService) {
        this.converter = converter;
        this.conversionHistoryService = conversionHistoryService;
        this.currencyService = currencyService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/"})
    public String main(Model model) {
        model.addAttribute("conversionForm", new ConversionForm());
        return "main";
    }

    @PostMapping("/")
    public String convert(@RequestParam(name = "fromCurrencyNumCode") Long fromCurrencyNumCode,
                          @RequestParam (name = "value") BigDecimal value,
                          @RequestParam (name = "toCurrencyNumCode") Long toCurrencyNumCode,
                          @Valid ConversionForm conversionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "main";
        }
        conversionForm.setResult(converter.convert(fromCurrencyNumCode, toCurrencyNumCode, value));
        return "main";
    }

    @GetMapping("/history")
    public String history(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        List<ConversionHistory> conversionHistoryList = conversionHistoryService.getAllConvHist();
        model.addAttribute("conversionHistoryList", conversionHistoryList);
        return "history";
    }

    @PostMapping("/history")
    public String search(@RequestParam(name = "fromCurrNumCode") Long fromCurrNumCode,
                         @RequestParam (name = "conversionDate") String date,
                         @RequestParam (name = "toCurrNumCode") Long toCurrNumCode,
                         @ModelAttribute("searchForm") SearchForm searchForm,
                         Model model) {

        List<ConversionHistory> conversionHistoryList;

        if (date.length() > 0 && fromCurrNumCode != null && toCurrNumCode != null) {
            conversionHistoryList =
                    conversionHistoryService.findConvHistFromToByDate(LocalDate.parse(date), fromCurrNumCode, toCurrNumCode);
        } else if (date.length() > 0 && fromCurrNumCode != null) {
            conversionHistoryList =
                    conversionHistoryService.findConvHistFromByDate(LocalDate.parse(date), fromCurrNumCode);
        } else if (date.length() > 0 && toCurrNumCode != null) {
            conversionHistoryList =
                    conversionHistoryService.findConvHistToByDate(LocalDate.parse(date), toCurrNumCode);
        } else if (date.length() > 0) {
            conversionHistoryList = conversionHistoryService.findConvHistByDate(LocalDate.parse(date));
        } else if (fromCurrNumCode != null && toCurrNumCode != null) {
            conversionHistoryList =
                    conversionHistoryService.findConvHistFromTo(fromCurrNumCode, toCurrNumCode);
        } else if (fromCurrNumCode != null) {
            conversionHistoryList =
                    conversionHistoryService.findConvHistFrom(fromCurrNumCode);
        } else if (toCurrNumCode != null) {
            conversionHistoryList =
                    conversionHistoryService.findConvHistTo(toCurrNumCode);
        } else {
            conversionHistoryList = conversionHistoryService.getAllConvHist();
        }

        model.addAttribute("conversionHistoryList", conversionHistoryList);
        return "history";
    }

    @ModelAttribute("currencyList")
    public Map<Long, String> getCurrencyList() {
        List<Currency> currencies = currencyService.getAllCurrencies().stream()
                                            .sorted(Comparator.comparing(Currency::getCharCode))
                                            .collect(Collectors.toList());
        Map<Long, String> currencyList = new LinkedHashMap<>();
        currencyList.put(null, null);
        currencies.forEach(currency -> {
            currencyList.put(currency.getNumCode(), currency.getCharCode() + " (" + currency.getName() + ")");
        });
        return currencyList;
    }
}
