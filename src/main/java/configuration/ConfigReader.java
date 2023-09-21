package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {
    public ConfigReader() {
    }
    public static String getConnectionString() throws Exception {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("D:\\Java\\BingNewsORM\\src\\main\\java\\configuration\\config.properties");
        properties.load(fileInputStream);
        return properties.getProperty("ConnectionString");
    }

    public static String getTempDirectory() throws Exception {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("D:\\Java\\BingNewsORM\\src\\main\\java\\configuration\\config.properties");
        properties.load(fileInputStream);
        return properties.getProperty("TempDirectory");
    }
}
