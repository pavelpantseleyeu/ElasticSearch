package org.elastic.task.runner

import org.elastic.task.listener.SuiteListener
import org.elastic.task.listener.TestListener
import org.testng.TestNG

class TestRunner {

     static TestNG createTestNG() {
        TestNG testNG = new TestNG();
        testNG.setTestSuites(Collections.singletonList("./src/main/resources/ElasticTest.xml"));
        return testNG;
    }

     static void main(String[] args) {
        createTestNG().run();
    }
}

