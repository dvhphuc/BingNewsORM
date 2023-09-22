package configuration;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class ConfigReaderTest {

    @Test
    void getConnectionString() throws Exception {
        var fileInputStream = new FileInputStream("src/main/java/configuration/config.properties");
        var configReader = new ConfigReader(fileInputStream);
        var connectionString = configReader.getConnectionString();

        assert connectionString != null;
    }
}