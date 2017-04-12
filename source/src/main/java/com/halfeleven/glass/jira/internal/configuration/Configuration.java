package com.halfeleven.glass.jira.internal.configuration;


import com.atlassian.core.util.ClassLoaderUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;


/**
 *
 * Application level configuration
 *
 * Loads the configuration file automatically from src/main/resources
 *
 */
@Component
public class Configuration {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);

    private static final Properties EMPTY_PROPERTIES = new Properties();

    /** loaded (cached) configuration properties */
    private Properties configuration;


    public void injectConfiguraiton(Properties configuraiton) {
        this.configuration = configuraiton;
    }


    public boolean isDevelopment() {
        return getBoolean("development", false);
    }

    public boolean isProduciton() {
        return !isDevelopment();
    }



    private Properties getConfiguration() {
        if (configuration != null) {
            return configuration;
        }

        // first looks for development configuration
        // than falls back to production
        configuration = loadProperties("config.dev.properties");
        if (configuration != null) {
            return configuration;
        }
        
        configuration = loadProperties("config.properties");
        if (configuration != null) {
            return configuration;
        }

        return EMPTY_PROPERTIES;
    }

    private Properties loadProperties(final String configurationName) {
        try {
            InputStream iStream = ClassLoaderUtils.getResourceAsStream(configurationName, this.getClass());
            if (iStream == null) {
                return null;
            }
            Properties p = new Properties();
            p.load(iStream);
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean getBoolean(final String key, final boolean defaultValue) {
        try {
            String value = getConfiguration().getProperty(key);
            if (StringUtils.isEmpty(value)) {
                return defaultValue;
            }
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
