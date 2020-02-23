package com.mycompany.mailtestframework.infrastructure;

import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.*;

public class Services {

		private static IConfigurationService configurationService;
		private static IMailService mailService;
		private static ServiceLocator serviceLocator;

		static {
			try {
				DiBinder diBinder = new DiBinder();
				serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
				ServiceLocatorUtilities.bind(serviceLocator, diBinder);

				IConfigurationService configuration = serviceLocator.getService(IConfigurationService.class);
				configurationService = configuration;
				IMailService sender = new MailService (configurationService);
				mailService = sender;

			} catch (Exception ex) {
			}
		}
		
		public static IConfigurationService getConfigurationService() {
			return configurationService;
		}
		
		public static IMailService getMailService(){
			return mailService;
		}
	
}
