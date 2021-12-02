package edu.xcdq.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager configManager = null;
    private Properties properties = null ;
    // 私有化构造方法
    private ConfigManager() {
        properties = new Properties();
        // 加载配置文件
        InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 提供一个对外访问的方法
    public static ConfigManager getInstance() {
        if ( configManager == null ) {
            configManager = new ConfigManager();
        }
        return configManager;
    }
    public String getString(String key) {
        return properties.getProperty(key);
    }


}
