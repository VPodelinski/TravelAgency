package by.vitali.web.managers;


import by.vitali.web.constants.PagePathConstants;

import java.util.ResourceBundle;

/**
 *
 */
public enum ConfigurationManager implements Manager {

    INSTANCE;

    private final ResourceBundle bundle = ResourceBundle.getBundle(PagePathConstants.CONFIG_SOURCE);

    /**
     * Returns property by key.
     *
     * @param key
     * @return
     */
    @Override
    public String getProperty(final String key) {
        return bundle.getString(key);
    }
}
