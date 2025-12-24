// src/main/java/com/example/demo/TestResultListener.java
package com.example.demo;

import org.testng.*;

public class TestResultListener implements ITestListener {
    // Keep minimal and non-intrusive; tests only need the class present
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestFailure(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
