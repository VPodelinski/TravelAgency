package by.vitali.web.managers;



import by.vitali.web.constants.PagePathConstants;

import java.util.ResourceBundle;

/**
 *
 */
public enum ConfigurationManager implements Manager {

    INSTANCE;

    private final ResourceBundle bundle = ResourceBundle.getBundle(PagePathConstants.CONFIG_SOURCE);

    @Override
    public String getProperty(String key) {
        return bundle.getString(key);
    }
}
