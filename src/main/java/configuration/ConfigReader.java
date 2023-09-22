package configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static FileInputStream fileInputStream;
    public ConfigReader(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }
    public static String getConnectionString() throws Exception {
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return properties.getProperty("ConnectionString");
    }
}
