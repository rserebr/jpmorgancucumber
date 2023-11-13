package environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LocalEnvironment implements Environment {

    private static final String ENVIRONMENT_PROPERTIES = "environment.properties";

    private Properties properties;

    public LocalEnvironment() throws IOException {
        this.properties = new Properties();
        BufferedReader reader = new BufferedReader(new FileReader(ENVIRONMENT_PROPERTIES));
        properties.load(reader);
    }

    @Override
    public String getBaseURI() {
        return properties.getProperty("base.uri");
    }

}
