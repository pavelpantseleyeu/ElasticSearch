package org.elastic.task.logger

import org.apache.log4j.Logger


class Log {

    static def logger = Logger.getLogger("MyLogger");

    static info(String message) { logger.info(message); }

    static debug(String message) {
        logger.debug(message);
    }

    static error(String message) {
        logger.error(message);
    }
}
