package com.mycompany.test.base;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.mycompany.gmail.GmailPage;
import com.mycompany.login.LoginPage;
import com.mycompany.messageStrings.MessageStrings;
import com.paypal.selion.platform.dataprovider.DataProviderFactory;
import com.paypal.selion.platform.dataprovider.DataResource;
import com.paypal.selion.platform.dataprovider.SeLionDataProvider;
import com.paypal.selion.platform.dataprovider.impl.InputStreamResource;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;

public class TestBase {

	final static Logger logger = Logger.getLogger(TestBase.class);

	private static final String pathToTestData = "src/main/resources/TestData/TestData.json" ;
	protected LoginPage logInPage;
	protected GmailPage gmailPage;
	protected MessageStrings messageStrings;

	/**
	 * Initializes the Generated code objects through Spring Dependency 
	 */
	@BeforeClass(alwaysRun = true)
	protected void initPages(){
		ApplicationContext context = new ClassPathXmlApplicationContext("config/spring.xml");
		logInPage = (LoginPage) context.getBean("logInPage");
		gmailPage = (GmailPage) context.getBean("gmailPage");
		messageStrings = (MessageStrings) context.getBean("messageStrings");
	}

	/**
	 * Retrieves the UserData based upon the key specified in the UserData.yaml
	 * file.
	 *
	 * @param key The key representing the test case user.
	 * @throws Exception
	 * @return UserData object
	 * @see com.mycompany.test.base.UserData
	 */
	protected UserData getUserData(int keyVal) throws Exception {
		UserData user = null;
		DataResource resource = 
				new InputStreamResource(new FileInputStream(pathToTestData),
						UserData.class, "json");
		user = (UserData)DataProviderFactory.getDataProvider(resource).getDataByIndex(
				new int[]{keyVal})[0][0];
		return user;
	}

	/**
	 * @DataProvider annotation to mark a method as supplying data for a test method
	 * @throws IOException
	 */
	@DataProvider(name = "jsonDataProvider")
	public Object[][] getJsonDataProvider() throws IOException {
		DataResource resource = 
				new InputStreamResource(new FileInputStream(pathToTestData),
						UserData.class, "json");
		SeLionDataProvider dataProvider =
				DataProviderFactory.getDataProvider(resource);
		Object[][] data = dataProvider.getAllData();
		return data;
	}


	/**
	 * Use this method Login to gmail and the login was sucessfull
	 * 
	 * @param email
	 * @param password
	 */
	protected void loginToApp(String email, String password){
		navigateToGmail();
		logInPage.setEmailTextFieldValue(email);
		logInPage.clickNextButton(logInPage.getPasswrdTextField());
		logInPage.setPasswrdTextFieldValue(password);
		logInPage.clickSignInButton(); 
	}

	/**
	 * Method to launch browser and navigate to gmail
	 * 
	 */
	protected void navigateToGmail(){
		Grid.driver().get("http://www.gmail.com");
		WebDriverWaitUtils.waitUntilElementIsPresent(logInPage.getEmailTextField().getLocator());
	}

}
