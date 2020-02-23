package com.mycompany.mailtestframework.infrastructure;

import org.testng.*;

public class TestSuiteListener implements ISuiteListener {
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("TestNG suite default output directory = " + suite.getOutputDirectory());
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("TestNG invoked methods = " + suite.getAllInvokedMethods());
	}

}
