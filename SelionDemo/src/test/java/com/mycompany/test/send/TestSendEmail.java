package com.mycompany.test.send;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import static org.mockito.Matchers.any;

import com.mycompany.gmail.GmailPage;
import com.paypal.selion.platform.html.Button;
import com.paypal.selion.platform.html.TextField;

public class TestSendEmail {
	
	//@org.junit.Test
	public void testSendEmailfunctionality(){
		GmailPage mockGmailPage = mock(GmailPage.class);
		//Test Data
		TextField sampleTextField = new TextField("sample");
		Button sampleButton = new Button("sample");
		//Mock the calls
		when(mockGmailPage.getComposeButton()).thenReturn(sampleButton);
		when(mockGmailPage.getToTextField()).thenReturn(sampleTextField);
		when(mockGmailPage.getSubjectTextField()).thenReturn(sampleTextField);
		when(mockGmailPage.getMessageTextField()).thenReturn(sampleTextField);
		when(mockGmailPage.getSendButton()).thenReturn(sampleButton);
	}

	

}
