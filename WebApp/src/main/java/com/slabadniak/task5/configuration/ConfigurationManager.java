package com.slabadniak.task5.configuration;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
