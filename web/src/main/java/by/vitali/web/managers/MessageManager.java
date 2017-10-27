package by.it_academy.agency.managers;

import by.it_academy.agency.constants.PagePathConstants;

import java.util.ResourceBundle;

public enum MessageManager implements Manager {

    INSTANCE;

    private final ResourceBundle bundle = ResourceBundle.getBundle(PagePathConstants.MESSAGES_SOURCE);

    @Override
    public String getProperty(String key) {
        return bundle.getString(key);
    }
}
