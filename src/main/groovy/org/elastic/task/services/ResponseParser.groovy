package org.elastic.task.services


import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import org.apache.commons.collections4.MultiValuedMap
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap
import org.elastic.task.logger.Log

import static groovyx.net.http.ContentType.TEXT


/**
 * Json Response parser from ElasticSearch
 * @author Aliaksandr Mikhaleu
 * @see org.elastic.task.services.Configuration
 */

class ResponseParser extends Configuration {

    /**
     * {@link MultiValuedMap} Map contains all values and objects from JSON
     */
    static MultiValuedMap<String, Object> valueMap = new ArrayListValuedHashMap<>();

    /**
     * Method take Json response from ElasticSearch and parse it to map
     */
    def getResponse() {
        def url = new URL(ELASTIC_URL + INDEX + MODE + PARAMS)
        Log.info("ElasticSearch request:" + url)
        def elasticResponse = new HTTPBuilder(url)
        elasticResponse.setHeaders(Accept: 'application/json')
        def json = elasticResponse.get(contentType: TEXT)
        def response = new JsonSlurper().parse(json)
        getResponseValues(response)
    }

    /**
     * Method recursively takes all keys and values from Json and puts them in {@link MultiValuedMap}
     * @param response Json response from server in {@link LazyMap}
     */
    def getResponseValues(response) {
        if (response instanceof LazyMap) {
            def keys = response.keySet()
            Log.info("Response keys: " + keys.toString())
            if (keys != null) {
                keys.each { key -> valueMap.put(key, response.get(key)) }
                keys.each { key -> getResponseValues(response.get(key)) }
            }
        }
        if (response instanceof List) {
            response.each { key -> getResponseValues(key) }
        }
    }
}