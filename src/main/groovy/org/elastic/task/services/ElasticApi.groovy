package org.elastic.task.services


import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap
import org.apache.commons.collections4.MultiSet
import org.apache.commons.collections4.MultiValuedMap
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap
import org.elastic.task.object.KeyObject


class ElasticApi extends Configuration {


    MultiValuedMap<String, Number> valueMap = new ArrayListValuedHashMap<>();
    MultiValuedMap<String, Object> objectMap = new ArrayListValuedHashMap<>();

    def getValuesByKey(String key) {
        MultiSet<String> keys = valueMap.keys()
        keys.each { n -> println(n) }
        (Collection<Integer>) valueMap.get("_score")
    }

    def getObjectsByKey() {
        MultiSet<String> keys = objectMap.keys()
        keys.each { n -> println(n) }
        (Collection<Object>) objectMap.get("Salary")
    }


    def getResponse() {
        URL url = new URL(ELASTIC_URL + "/" + INDEX + "/" + MODE)
        def response = new JsonSlurper().parse(url)
        getAllKeys(response)
        //getObjects(response)
        List<Integer> values = getValuesByKey()
        println(values)

        // List<Object> map = getObjectsByKey()
        //println(map)

    }


    def getAllKeys(Object response) {
        if (response instanceof LazyMap) {
            Set<String> keys = response.keySet()
            println(response.keySet())
            if (keys != null) {
                keys.each { n -> getValues(n, response) }
                keys.each { n -> getAllKeys(response.get(n)) }
            }
        }
        if (response instanceof List) {
            for (int i = 0; i < response.size(); i++) {
                getAllKeys(response.get(i))
            }
        }


    }

    def getValues(String key, Object response) {
        if (response.get(key) instanceof Number) {
            valueMap.put(key, response.get(key))
        }
    }
}



