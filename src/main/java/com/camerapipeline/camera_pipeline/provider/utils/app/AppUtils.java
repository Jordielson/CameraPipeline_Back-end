package com.camerapipeline.camera_pipeline.provider.utils.app;

import java.io.InputStream;
import java.util.Properties;

public final class AppUtils {
    private static final Properties properties;

    static {
        properties = new Properties();

        try {
            ClassLoader classLoader = AppUtils.class.getClassLoader();
            InputStream applicationPropertiesStream = classLoader.getResourceAsStream("config/application.properties");
            properties.load(applicationPropertiesStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
