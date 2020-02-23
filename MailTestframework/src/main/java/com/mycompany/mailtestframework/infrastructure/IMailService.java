package com.mycompany.mailtestframework.infrastructure;

import javax.mail.*;

public interface IMailService {
	void sendMail();
	void clearInbox() throws MessagingException;
}
