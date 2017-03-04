package com.slabadniak.web.configuration;

import java.util.ResourceBundle;


/**
 * Content path to pages.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    private ConfigurationManager() { }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
