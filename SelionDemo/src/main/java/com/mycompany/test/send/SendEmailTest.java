package com.mycompany.test.send;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mycompany.test.base.TestBase;
import com.mycompany.test.base.UserData;
import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.asserts.SeLionAsserts;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;

public class SendEmailTest extends TestBase{
	
	final static Logger logger = Logger.getLogger(com.mycompany.test.send.SendEmailTest.class);
	private UserData sendUser = null;
	private UserData receiveUser = null;
	private UserData secondaryReceiverUser = null;
	private String emailSubject = null;
	private StringBuilder subject = new StringBuilder();
	
	/**
	 * Initializes the User objects stated in  TestData.json
	 */
	@BeforeTest(alwaysRun = true)
	protected void setUpUserDataSource(){
		try {
			sendUser = getUserData(1);
			receiveUser = getUserData(2);
			secondaryReceiverUser = getUserData(3);
			subject.append(System.currentTimeMillis() + " ");
			logger.info("The Users have been intialized");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This Tests the User happy flow of logging in
	 * and sending the email
	 */
	@Test(priority = 1)
	 @WebTest
	  public void happyFlowSingleUserSend(){
		//set the email subject
		subject.append(messageStrings.getEmailSubjectLabel().getLocator());
		emailSubject = subject.toString();
		loginToApp(sendUser.getEmail(),sendUser.getPassword());
		logger.info("Browser launched and the user logged in");
		WebDriverWaitUtils.waitUntilElementIsPresent(gmailPage.
				getSearchButton().getLocator());
		gmailPage.getComposeButton().click(gmailPage.getToTextField());
		logger.debug("Composing the email");
		for (String winHandle : Grid.driver().getWindowHandles()) {
			Grid.driver().switchTo().window(winHandle);
			if(gmailPage.getToTextField().isElementPresent() &&
					gmailPage.getToTextField().isVisible()){
				gmailPage.getToTextField().type(receiveUser.getEmail());
			}
			if(gmailPage.getSubjectTextField().isElementPresent() &&
					gmailPage.getSubjectTextField().isVisible()){
				gmailPage.getSubjectTextField().type(emailSubject);
			}
			if(gmailPage.getMessageTextField().isElementPresent() &&
					gmailPage.getMessageTextField().isVisible()){
				gmailPage.getMessageTextField().type(messageStrings.getEmailMessageBodyLabel().
						getLocator());
			}
			if(gmailPage.getSendButton().isElementPresent() &&
					gmailPage.getSendButton().isVisible()){
				gmailPage.getSendButton().click();
			}
		} 	
		logger.info("Validating successful message sent");
		WebDriverWaitUtils.waitUntilElementIsVisible(gmailPage.getMsgSentLabel().getLocator());
		SeLionAsserts.assertTrue(gmailPage.getMsgSentLabel().getText().
				contains(messageStrings.getSentMessageBodyLabel().getLocator()));
	}
	
	/**
	 * This Tests the User happy flow of logging in
	 * and verifying the receipt of the email
	 */
	@Test(priority = 2)
	@WebTest
	  public void happyFlowSingleUserReceive(){
		loginToApp(receiveUser.getEmail(),receiveUser.getPassword());
		WebDriverWaitUtils.waitUntilElementIsPresent(gmailPage.
				getSearchButton().getLocator());
		int count = gmailPage.getInboxEmailContainer().size();
		boolean didReceive = false;
		for(int i=0; i<count-1; i++){
			gmailPage.getInboxEmailContainer(i).click();
			if(gmailPage.getMailSubjectLabel().getText().contains(emailSubject)){
				didReceive = true;
				break;
			}
			gmailPage.getInBoxLink().click(gmailPage.getInboxEmailContainer());
		}
		SeLionAsserts.assertTrue(didReceive,"The Sent mail was received");
	}

	/**
	 * returns the Send User
	 *
	 * @return UserData object
	 */
	public UserData getSendUser() {
		return sendUser;
	}

	/**
	 * sets the Send User
	 *
	 * @param UserData object
	 */
	public void setSendUser(UserData sendUser) {
		this.sendUser = sendUser;
	}

	/**
	 * returns the ReceiveUser
	 *
	 * @return UserData object
	 */
	public UserData getReceiveUser() {
		return receiveUser;
	}

	/**
	 * sets the Receive User
	 *
	 * @param UserData object
	 */
	public void setReceiveUser(UserData receiveUser) {
		this.receiveUser = receiveUser;
	}

	/**
	 * returns the ReceiveUser for cc or bcc
	 *
	 * @return UserData object
	 */
	public UserData getSecondaryReceiverUser() {
		return secondaryReceiverUser;
	}

	/**
	 * sets the ReceiveUser for cc or bcc
	 *
	 * @param UserData object
	 */
	public void setSecondaryReceiverUser(UserData secondaryReceiverUser) {
		this.secondaryReceiverUser = secondaryReceiverUser;
	}

	/**
	 * returns the email subject
	 *
	 * @return String
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * sets the the email subject
	 *
	 * @param String
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	/**
	 * returns the StringBuilder object to build email subject
	 *
	 * @return StringBuilder
	 */
	public StringBuilder getSubject() {
		return subject;
	}

	/**
	 * sets the the StringBuilder object to build email subject
	 *
	 * @param StringBuilder
	 */
	public void setSubject(StringBuilder subject) {
		this.subject = subject;
	}
	
	
	
	

}
