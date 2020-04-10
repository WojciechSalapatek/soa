package com.wpate.books.service;

import com.google.common.collect.ImmutableMap;
import com.wpate.books.model.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CurrencyRecalculationService {

    private static final Map<Currency, Double> RATES = ImmutableMap.of(
            Currency.PLN, 1.0,
            Currency.USD, 4.153,
            Currency.EUR, 4.548
    );

    public static double recalculatePrice(double originalPrice, Currency originalCurrency, Currency toCurrency){
        if(originalCurrency == toCurrency) return originalPrice;
        return new BigDecimal(originalPrice*(RATES.get(originalCurrency)/RATES.get(toCurrency))).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }


}
