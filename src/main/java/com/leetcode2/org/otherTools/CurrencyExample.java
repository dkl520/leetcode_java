package com.leetcode2.org.otherTools;

import java.util.Currency;
import java.util.Locale;

public class CurrencyExample {
    public static void main(String[] args) {
        Currency defaultCurrency = Currency.getInstance(Locale.getDefault());
//        System.out.println("Default Currency Code: " +defaultCurrency.getCurrencyCode());
//        for (Currency currency: Currency.getAvailableCurrencies()) {
//            System.out.println(currency.getCurrencyCode()+"-"+currency.getDisplayName());
//        }
        Locale defaultLocale = Locale.getDefault();
        System.out.println(defaultLocale.getCountry());
        System.out.println(defaultLocale.getDisplayName());
        Currency usdCurrency = Currency.getInstance("USD");
        Currency jarpan = Currency.getInstance("JPY");
        System.out.println(usdCurrency.getCurrencyCode() + usdCurrency.getDisplayName());
        System.out.println(jarpan.getCurrencyCode() + jarpan.getDisplayName());

        System.out.println(jarpan.toString());

    }
}
