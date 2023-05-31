package com.mastercard.gpse.processing.fileoperations;

import com.mastercard.gpse.processing.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class TestProperties {
    private static final Logger logger = LogManager.getLogger(TestProperties.class);
    private static TestProperties INSTANCE;
    private Properties envProperties;
    private Properties userProperties;
    private Properties apiProperties;
    private Properties apiendpointsProperties;
    private Properties dbProperties;

    public static TestProperties getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }

    public String getEnvProperty(String propertyKey) {
        envProperties = initProperties("properties/environment.properties", envProperties);
        return envProperties.getProperty(propertyKey);
    }

    public String getUserProperty(String propertyKey) {
        userProperties = initProperties("properties/user.properties", userProperties);
        return userProperties.getProperty(propertyKey);
    }

    public String getApiEndPointProperty(String propertyKey) {
        apiendpointsProperties = initProperties("properties/apiendpoints.properties", apiendpointsProperties);
        return apiendpointsProperties.getProperty(propertyKey);
    }

    public String getApiProperty(String propertyKey) {
        apiProperties = initProperties("properties/api.properties", apiProperties);
        return apiProperties.getProperty(propertyKey);
    }

    public String getDBProperty(String propertyKey) {
        dbProperties = initProperties("properties/db.properties", dbProperties);
        return dbProperties.getProperty(propertyKey);
    }

    private Properties initProperties(String filePath, Properties testProperties) {
        if (Objects.isNull(testProperties)) {
            String fileName = Constants.USER_DIR + Constants.PATH_SEPARATOR
                    + Constants.CONFIG_DIR_PATH + Constants.PATH_SEPARATOR
                    + Constants.RUNTIME_ENVIRONMENT + Constants.PATH_SEPARATOR
                    + filePath;
            logger.info("FilePath:"+fileName);
            testProperties = new Properties();
            tryLoadPropertiesFromFile(fileName, testProperties);
            }
        return testProperties;
    }

    private void tryLoadPropertiesFromFile(String propertiesFilePath, Properties properties) {
        try {
            properties.load(new FileInputStream(propertiesFilePath));
        } catch (IOException e) {
            logger.error("Problem with a properties file",e);

        }
    }

    private TestProperties(){
    }
}
