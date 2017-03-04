package com.slabadniak.web.configuration;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * To get message in user's language.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class LanguageManager {
    private final static String RUSSIAN = "ru_RU";
    private final static String ENGLISH = "en_US";
    private final static String FILE = "def";


    private LanguageManager() {
    }

    public static String getProperty(String message, String local) {
        ResourceBundle resourceBundle;
        if (local != null) {
            switch (local) {
                case RUSSIAN:
                    Locale locale = new Locale("ru", "RU");
                    resourceBundle = ResourceBundle.getBundle(FILE, locale);
                    break;
                case ENGLISH:
                    locale = new Locale("en", "US");
                    resourceBundle = ResourceBundle.getBundle(FILE, locale);
                    break;
                default:
                    resourceBundle = ResourceBundle.getBundle(FILE);
            }
        } else {
            resourceBundle = ResourceBundle.getBundle(FILE);
        }
        return resourceBundle.getString(message);
    }
}
