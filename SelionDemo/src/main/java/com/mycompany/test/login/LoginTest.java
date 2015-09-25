package com.mycompany.test.login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mycompany.test.base.TestBase;
import com.mycompany.test.base.UserData;
import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.asserts.SeLionAsserts;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;

public class LoginTest extends TestBase{
	
	private UserData loginUser = null;
	
	@BeforeTest(alwaysRun = true)
	protected void setUpUserDataSource(){
		try {
			loginUser = getUserData(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 @Test(priority = 1)
	 @WebTest
	  public void happyFlowLogin(){
		loginToApp(loginUser.getEmail(),loginUser.getPassword());
		WebDriverWaitUtils.waitUntilElementIsPresent(gmailPage.
				getSearchButton().getLocator());
		SeLionAsserts.assertTrue(gmailPage.getSearchButton().isElementPresent() &&
				gmailPage.getSearchButton().isVisible(), "You have successfully logged in");
	}
	 
	 @Test(priority = 2)
	 @WebTest
	  public void blankAndInvalidEmailId(){
		 navigateToGmail();
		 //set email as empty string
		 logInPage.setEmailTextFieldValue("");
		 logInPage.getNextButton().click(logInPage.getBlankInvalidEmailErrLabel());
		 SeLionAsserts.assertTrue(logInPage.getEmailTextField().isElementPresent() &&
				 logInPage.getEmailTextField().isVisible(), "Still in logon page due to empty email");
		 SeLionAsserts.assertTrue(logInPage.getBlankInvalidEmailErrLabel().isElementPresent() &&
				 logInPage.getBlankInvalidEmailErrLabel().isVisible(),"Error message for blank email displayed");
		 SeLionAsserts.assertTrue(logInPage.getBlankInvalidEmailErrLabel().getText().
				 contains(messageStrings.getBlankEmailErrLabel().getLocator())); 
		 //set an invalid email id
		 logInPage.setEmailTextFieldValue("12334242");
		 logInPage.getNextButton().click(logInPage.getBlankInvalidEmailErrLabel());
		 SeLionAsserts.assertTrue(logInPage.getEmailTextField().isElementPresent() &&
				 logInPage.getEmailTextField().isVisible(), "Still in logon page due to invalid email");
		 SeLionAsserts.assertTrue(logInPage.getBlankInvalidEmailErrLabel().isElementPresent() &&
				 logInPage.getBlankInvalidEmailErrLabel().isVisible(),"Error message for invalid email displayed");
		 SeLionAsserts.assertTrue(logInPage.getBlankInvalidEmailErrLabel().getText().
				 equalsIgnoreCase(messageStrings.getInvalidEmailErrLabel().getLocator()));
	}
	 
	 @Test(priority = 3)
	 @WebTest
	  public void blankAndInvalidPasswordId(){
		 navigateToGmail();
		 logInPage.setEmailTextFieldValue(loginUser.getEmail());
		 logInPage.getNextButton().click(logInPage.getPasswrdTextField());
		 //set password field as blank
		 logInPage.setPasswrdTextFieldValue("");
		 logInPage.getSignInButton().click(logInPage.getBlankInvalidPasswordErrLabel());
		 SeLionAsserts.assertTrue(logInPage.getPasswrdTextField().isElementPresent() &&
				 logInPage.getPasswrdTextField().isVisible(), "In Password request page due to empty password");
		 SeLionAsserts.assertTrue(logInPage.getBlankInvalidPasswordErrLabel().isElementPresent() &&
				 logInPage.getBlankInvalidPasswordErrLabel().isVisible(),"Error message displayed for empty password");
		 SeLionAsserts.assertTrue(logInPage.getBlankInvalidPasswordErrLabel().getText().
				 contains(messageStrings.getBlankPasswordErrLabel().getLocator())); 
		 //set a invalid password
		 logInPage.setPasswrdTextFieldValue("asqwatd123");
		 logInPage.getSignInButton().click(logInPage.getBlankInvalidPasswordErrLabel());
		 SeLionAsserts.assertTrue(logInPage.getBlankInvalidPasswordErrLabel().isElementPresent() &&
				 logInPage.getBlankInvalidPasswordErrLabel().isVisible(),"Error message displayed for invalid password");
		 SeLionAsserts.assertTrue(logInPage.getBlankInvalidPasswordErrLabel().getText().
				 contains(messageStrings.getInvalidPasswordErrLabel().getLocator())); 
	}

}
