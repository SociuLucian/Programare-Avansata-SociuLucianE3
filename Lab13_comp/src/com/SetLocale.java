package com;

import java.util.Locale;

public class SetLocale {
    public static void setCurrentLocale(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        Locale.setDefault(locale);
        System.out.println("Current locale set to " + locale.getDisplayName());
    }
}
