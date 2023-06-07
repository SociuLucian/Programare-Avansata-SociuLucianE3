package app;

import com.DisplayLocales;
import com.SetLocale;
import com.Info;

import java.util.Locale;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Input command:");
            String command = scanner.nextLine();

            switch (command) {
                case "locales":
                    DisplayLocales.displayAvailableLocales();
                    break;
                case "locale.set":
                    System.out.println("Enter the language tag:");
                    String languageTag = scanner.nextLine();
                    SetLocale.setCurrentLocale(languageTag);
                    break;
                case "info":
                    System.out.println("Enter the language tag (or 'current' for the current locale):");
                    String input = scanner.nextLine();
                    if (input.equals("current")) {
                        Info.displayLocaleInfo(Locale.getDefault().toLanguageTag());
                    } else {
                        Info.displayLocaleInfo(input);
                    }
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }

        scanner.close();
    }
}
