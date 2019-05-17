package org.elastic.task.tests

import org.elastic.task.logger.Log
import org.elastic.task.services.MapUtils
import org.elastic.task.services.ResponseParser
import org.testng.Assert
import org.testng.annotations.BeforeTest
import org.testng.annotations.DataProvider
import org.testng.annotations.Listeners
import org.testng.annotations.Test


class ElasticTest {

    def mapUtils
    def responseParser

    @BeforeTest
    void setUpServices() {
        mapUtils = new MapUtils()
        responseParser = new ResponseParser()
        responseParser.getResponse()
    }

    @Test(dataProvider = "valuesdata",
            description = "verify values for keys")
    void valuesTest(expected, key) {
        def actual = mapUtils.getValuesByKey(key)
        assert actual == expected
    }

    @DataProvider(name = "valuesdata")
    Object[][] createListOfValues() {
        [
                [[62, 154000, 45, 110000, 63, 123000, 3], "value"],
                [[0.9423279, 0.9423279, 0.9423279], "_score"],
        ]
    }

    @Test(dataProvider = "stringsdata",
            description = "Verify strings for key ")
    void stringsTest(expected, key) {
        def actual = mapUtils.getValuesByKey(key)
        assert actual == expected
    }

    @DataProvider(name = "stringsdata")
    Object[][] createListOfStrings() {
        [
                [["ELVA", "JENNEFER", "LAUREN"], "FirstName"],
                [["RECHKEMMER", "WENIG", "RIDENS"], "LastName"],
                [["Female", "Female", "Female"], "Gender"],
                [["Unmarried", "Unmarried", "Married"], "MaritalStatus"]
        ]
    }

    @Test(dataProvider = "objectsdata",
            description = "Verify objects for keys")
    void objectTest(expected, key) {
        def actual = mapUtils.getValuesByKey(key)
        assert actual == expected
    }

    @DataProvider(name = "objectsdata")
    Object[][] createObjects() {
        [
                [[[value: 154000], [value: 110000], [value: 123000]], "Salary"],
                [[[value: 62], [value: 45], [value: 63]], "Age"],
        ]
    }
}
