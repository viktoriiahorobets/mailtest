/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mailtestframework.infrastructure;

public interface IConfigurationService {
	String getUser();
	String getPassword();
	String getReportsOutputDirectory();
	String getChromeDriverVersion();
	
}
