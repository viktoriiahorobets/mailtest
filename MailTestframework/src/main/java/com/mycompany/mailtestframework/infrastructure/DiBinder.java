package com.mycompany.mailtestframework.infrastructure;

import javax.inject.*;
import org.glassfish.hk2.utilities.binding.*;

public class DiBinder extends AbstractBinder{
	
	@Override
	protected void configure() {
		bind(ConfigurationService.class).to(IConfigurationService.class).in(Singleton.class);
		bind(MailService.class).to(IMailService.class).in(Singleton.class);
	}
}
