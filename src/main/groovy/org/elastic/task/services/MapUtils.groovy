package org.elastic.task.services

import org.elastic.task.logger.Log

/**
 * Util for get all values from {@link org.apache.commons.collections4.MultiValuedMap}
 * @author Aliaksandr Mikhaleu
 */
class MapUtils {
    /**
     * Method of obtaining all values or objects from {@link org.apache.commons.collections4.MultiValuedMap}
     * @param key contains sought-for key in JSON
     * @return values or objects by key
     */
    def getValuesByKey(String key) {
        ResponseParser.valueMap.get(key)
    }
}
