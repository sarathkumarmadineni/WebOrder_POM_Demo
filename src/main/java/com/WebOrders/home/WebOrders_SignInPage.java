package com.WebOrders.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebOrders_SignInPage {

	private WebDriver driver;

	@FindBy(xpath = "//h1[normalize-space()='Web Orders']")
	WebElement txtWebOrder;

	public WebOrders_SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public boolean verifySignInPageURL() {
		String pageURL = driver.getCurrentUrl();
		String expectedPageURL = "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/default.aspx";
		return pageURL.contains(expectedPageURL);
	}

	public boolean verifySignInPageText() {
		String pageText = txtWebOrder.getText();
		String expectedPageText = "Web Orders";
		return pageText.contains(expectedPageText);
	}

	public boolean verifySignInPageTitle() {
		String pageTitle = driver.getTitle();
		String expectedPageTitle = "Web Orders";
		return pageTitle.contains(expectedPageTitle);
	}

}
