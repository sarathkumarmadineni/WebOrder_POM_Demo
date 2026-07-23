package com.WebOrders.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.WebOrders.common.WebOrders_BasePage;
import com.WebOrders.common.WebOrders_BaseSetup;
//import com.webOrders.common.WebOrders_BaseSetup;
import com.WebOrders.home.WebOrders_HomePage;
import com.WebOrders.home.WebOrders_SignInPage;

public class WebOrders_Login extends WebOrders_BaseSetup {
	WebOrders_SignInPage signIn;
	WebOrders_HomePage homePage;
	WebOrders_BasePage basePage;
	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
		basePage = new WebOrders_BasePage(driver);
	}

	@Test(description = "Login and validate the WebOrder Homepage Text", priority = 1)
	public void loginTest() throws Exception {
		test = extent.createTest("Test Case 1", "Verify Text after login");
		signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
		Thread.sleep(3000);
		signIn.verifySignInPageText();
		basePage.logout();
	}

	@Test(description = "validateURL() method call for URL verification", priority = 2)
	public void urlTest() throws Exception {
		test = extent.createTest("Test Case 2", "Verify URL after login");
		signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
		Thread.sleep(3000);
		signIn.verifySignInPageURL();
		basePage.logout();
	}

	@Test(description = "validateTitle() method call for Title verification", priority = 3)
	public void verifyText() throws Exception {
		test = extent.createTest("Test Case 3", "Verify Text after login");
		signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
		Thread.sleep(3000);
		signIn.verifySignInPageTitle();
		basePage.logout();
	}
}
