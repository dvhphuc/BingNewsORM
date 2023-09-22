package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static FileInputStream fileInputStream;
    public ConfigReader() throws FileNotFoundException {
        fileInputStream = new FileInputStream("src/main/resources/application.properties");
    }
    public static String getConnectionString() throws Exception {
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return properties.getProperty("ConnectionString");
    }
}
