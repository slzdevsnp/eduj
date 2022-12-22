package org.slzdevsnp.restclients.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class EnvironmentConfig {
    public static String fileName = "config";
    public static String environment = "local";


    public static Map<String, Object> loadAsMap() {
        return loadAsMap(null);
    }

    public static Map<String, Object> loadAsMap(String context) {
        Map<String, Object> params = new HashMap<>();
        Properties p = load(context);
        for (Object o : p.keySet()) {
            String k = (String) o;
            params.put(k, p.getProperty(k));
        }
        return params;
    }

    public static Properties load() {
        return load(null);
    }
    public static Properties load(String context) {
        Properties defaultProps = new Properties();
        try {
            defaultProps.load(EnvironmentConfig.class.getResourceAsStream("/" + fileName + ".properties"));
        } catch (NullPointerException npe) {
            throw new IllegalStateException("Could not load application.properties file from classpath");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        if (StringUtils.isNotBlank(environment)) {
            String f = "/" + fileName + "-" + environment + ".properties";
            try {
                Properties envProperties = new Properties();
                envProperties.load(EnvironmentConfig.class.getResourceAsStream(f));

                for (Object o : envProperties.keySet()) {
                    defaultProps.setProperty(o.toString(), envProperties.getProperty(o.toString()));
                }
            } catch (Exception e) {
                log.info("Could not find properties file '{}'", f);
            }
        }

        if (context != null) {
            Properties ctx = new Properties();
            for (Object o : defaultProps.keySet()) {
                String key = (String) o;
                if (StringUtils.startsWith(key, context + ".")) {
                    ctx.setProperty(key.substring((context + ".").length()), defaultProps.getProperty(key));
                }
            }
            return ctx;
        }

        return defaultProps;
    }
}
