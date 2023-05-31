package com.mastercard.gpse.processing.managers;

import com.mastercard.gpse.processing.configuration.ConfigFileReader;

public class FileReaderManager {

    private static final FileReaderManager fileReaderManager = new FileReaderManager();
    private static ConfigFileReader configFileReader;

    private FileReaderManager() {}

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public ConfigFileReader getConfigFileReader() {
        return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
    }
}
