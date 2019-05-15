package org.elastic.task.services

import org.elastic.task.services.ElasticApi

class ElasticService {
     static void main(String[] args) {
        println("hello");
         def elasticApi = new ElasticApi();
         elasticApi.getResponse()
    }
}
