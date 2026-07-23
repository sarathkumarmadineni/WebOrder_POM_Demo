package com.WebOrders.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.WebOrders.home.WebOrders_SignInPage;

public class WebOrders_BasePage{

	private static WebDriver driver;
	
	//WebElement txtusername = driver.findElement(By.name(""));
	
	@FindBy(id = "ctl00_MainContent_username")
	WebElement txtUsername;

	@FindBy(id = "ctl00_MainContent_password")
	WebElement txtPassword;

	@FindBy(id = "ctl00_MainContent_login_button")
	WebElement btnLogin;
		
	@FindBy(linkText = "Logout")
	WebElement lnLogout;
	
	

	public static WebDriver getDriver() {
		return driver;
	}

	public WebOrders_BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Sign in functionality
	public WebOrders_SignInPage GoToHomePageAndSignIn(String username, String passwd) throws Exception {
		return signInToWebOrders(username, passwd);
	}

	public WebOrders_SignInPage signInToWebOrders(String username, String passwd) {
		enterUserName(username);
		enterPassword(passwd);
		submitButton();
		return new WebOrders_SignInPage(driver);
	}

	public void enterUserName(String username) {
		txtUsername.clear();
		txtUsername.sendKeys(username);
	}

	public void enterPassword(String passwd) {
		txtPassword.clear();
		txtPassword.sendKeys(passwd);
	}

	public void submitButton() {
		btnLogin.click();
	}
	
	public void logout() throws InterruptedException {
		lnLogout.click();
		Thread.sleep(3000);
	
	}

}
