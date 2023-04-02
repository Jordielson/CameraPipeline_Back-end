package com.camerapipeline.camera_pipeline.provider.utils.app;

import java.io.InputStream;
import java.util.Properties;

public final class AppUtils {
    private static final Properties properties;

    static {
        properties = new Properties();

        try {
            ClassLoader classLoader = AppUtils.class.getClassLoader();
            String[] resources = {"config/env.properties","config/application.properties"};
            for (String resource : resources) {
                InputStream applicationPropertiesStream = classLoader.getResourceAsStream(resource);
                properties.load(applicationPropertiesStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
