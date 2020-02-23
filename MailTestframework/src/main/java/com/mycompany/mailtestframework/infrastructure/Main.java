package com.mycompany.mailtestframework.infrastructure;

import com.beust.testng.*;
import com.mycompany.mailtestframework.mailtest.*;

public class Main {
	private static IConfigurationService configurationService;


	public static void main(String args[]) {
		configurationService = Services.getConfigurationService();
		TestNG testSuite = new TestNG();
		testSuite.setTestClasses(new Class[]{EmailTest.class});
		testSuite.addListener(new TestSuiteListener());
		testSuite.setDefaultSuiteName("MailTestFramework Suite");
		testSuite.setDefaultTestName("MailTestFramework");
		testSuite.setOutputDirectory(configurationService.getReportsOutputDirectory());
		testSuite.run();
}	
}
	
