// src/main/java/com/example/demo/TestResultListener.java
package com.example.demo;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[TEST START] " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[TEST PASSED] " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[TEST FAILED] " + result.getName() +
                " | Exception: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[TEST SKIPPED] " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("[TEST PARTIAL SUCCESS] " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("[TEST CONTEXT START] " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("[TEST CONTEXT FINISH] " + context.getName());
    }
}
