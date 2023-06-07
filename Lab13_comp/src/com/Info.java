package com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import static java.util.Currency.getInstance;

public class Info {
    public static void displayLocaleInfo(String languageTag) {
        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales) {
            if (locale.getLanguage().equals(Locale.forLanguageTag(languageTag).getLanguage())) {
                System.out.println("Locale: " + locale.toLanguageTag());
                System.out.println("Country: " + locale.getDisplayCountry(locale));
                System.out.println("Language: " + locale.getDisplayLanguage(locale));

                try {
                    Currency currency = Currency.getInstance(locale);
                    System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName(locale) + ")");
                } catch (IllegalArgumentException e) {
                    System.out.println("Currency: N/A");
                }

                DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
                System.out.println("Week Days: " + String.join(", ", dateFormatSymbols.getWeekdays()));
                System.out.println("Months: " + String.join(", ", dateFormatSymbols.getMonths()));
                System.out.println("Today: " + DateFormat.getDateInstance(DateFormat.LONG, locale).format(new Date()));
                System.out.println();
            }
        }
    }
}

