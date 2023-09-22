package configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static FileInputStream fileInputStream;
    public ConfigReader() {
        try {
            fileInputStream = new FileInputStream("D:\\Java\\BingNewsORM\\src\\main\\java\\configuration\\config.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getConnectionString() throws Exception {
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return properties.getProperty("ConnectionString");
    }
}
