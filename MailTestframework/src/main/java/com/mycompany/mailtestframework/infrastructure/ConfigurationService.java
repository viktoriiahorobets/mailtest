package com.mycompany.mailtestframework.infrastructure;

public class ConfigurationService implements IConfigurationService{

	@Override
	public String getUser() {
		return "mail.project2020@gmail.com";
	}
	
	@Override
	public String getPassword() {
		return "Mailproject11111";
	}
	
	@Override
	public String getReportsOutputDirectory() {
		return "C:\\Users\\vhorobet\\Documents\\NetBeansProjects\\MailTestframework\\target\\";
	}

	
	@Override
	public String getChromeDriverVersion() {
		return "80.0.3987.16";
	}
	}
