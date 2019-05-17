package org.elastic.task.services

/**
 * Configuration class with HTTP parameters for connect to ElasticSearch
 */
class Configuration {
 static final String ELASTIC_URL = "http://192.168.99.100:9400/"
 static final String INDEX = "employees/"
 static final String MODE = "_search"
 static final String PARAMS = "?q=Designation:CEO"
}
