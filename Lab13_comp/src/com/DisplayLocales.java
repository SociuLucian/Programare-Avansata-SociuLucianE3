package com;


import java.util.Locale;

public class DisplayLocales {
    public static void displayAvailableLocales() {
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            System.out.println(locale.getDisplayName() + " " + locale.getLanguage());
        }
    }
}

