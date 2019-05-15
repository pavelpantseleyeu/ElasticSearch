package org.elastic.task.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {
    public void onStart(ISuite iSuite) {
        System.out.println("{SUITE STARTED} " + iSuite.getName());
    }

    public void onFinish(ISuite iSuite) {
        System.out.println("{SUITE FINISHED} " + iSuite.getName());
    }
}
