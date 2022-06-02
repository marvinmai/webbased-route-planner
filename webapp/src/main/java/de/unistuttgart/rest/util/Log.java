package de.unistuttgart.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private static Logger logger = LoggerFactory.getLogger(Log.class);

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logErr(String message) {
        logger.error(message);
    }
}
