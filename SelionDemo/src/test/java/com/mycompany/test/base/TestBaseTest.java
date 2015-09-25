package com.mycompany.test.base;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.gmail.GmailPage;
import com.mycompany.login.LoginPage;
import com.mycompany.messageStrings.MessageStrings;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestBaseTest {
	
	//@Test
	public void testInitPages(){
		ApplicationContext context = mock(ApplicationContext.class);
		//Test Data
		LoginPage loginPage = new LoginPage();
		GmailPage gmailPage = new GmailPage();
		//MessageStrings messageStrings = new MessageStrings();
		
		loginPage.setEmailTextFieldValue("sample");
		gmailPage.setEmailSearchTextFieldValue("sample");
		
		when(context.getBean("logInPage")).thenReturn(loginPage);
		when(context.getBean("gmailPage")).thenReturn(gmailPage);
	}

}
