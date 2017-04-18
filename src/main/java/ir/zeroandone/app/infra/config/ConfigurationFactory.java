package ir.zeroandone.app.infra.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigurationFactory {
    public static final Configuration config;

    static {
        Configuration c = null;
        try {
            PropertiesConfiguration configs = null;
            configs = new PropertiesConfiguration("app.config.properties");
            //configs.setReloadingStrategy(new FileChangedReloadingStrategy());
            c = configs;
        } catch (ConfigurationException e) {
            e.printStackTrace();
            c = null;
        }
        config = c;
    }
}
