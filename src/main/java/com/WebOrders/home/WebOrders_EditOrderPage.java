package com.WebOrders.home;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WebOrders_EditOrderPage {
	WebDriver driver;
	WebDriverWait wait;

	public WebOrders_EditOrderPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[normalize-space()='Edit Order']")
	WebElement lblContent;

	@FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
	WebElement ddlProduct;

	@FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
	WebElement txtQuantity;

	@FindBy(xpath = "//input[@value='Calculate']")
	WebElement btnCalculate;

	@FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
	WebElement txtCustName;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
	WebElement txtStreet;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
	WebElement txtCity;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
	WebElement txtState;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
	WebElement txtZip;

	/*
	 * @FindBy(id = "ctl00_MainContent_fmwOrder_cardList") WebElement rdoCard;
	 */

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
	WebElement txtCardNr;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
	WebElement txtExpiry;

	@FindBy(id = "ctl00_MainContent_fmwOrder_UpdateButton")
	WebElement btnUpdate;

	@FindBy(xpath = "//em[normalize-space()=\"Field 'Quantity' cannot be empty.\"]")
	WebElement errEmptyQty;

	@FindBy(xpath = "//span[normalize-space()='Quantity must be greater than zero.']")
	WebElement errInvalidQty;

	@FindBy(xpath = "//span[normalize-space()=\"Field 'Customer name' cannot be empty.\"]")
	WebElement errCustNr;

	@FindBy(xpath = "//span[normalize-space()=\"Field 'Street' cannot be empty.\"]")
	WebElement errStreet;

	@FindBy(xpath = "//span[normalize-space()=\"Field 'City' cannot be empty.\"]")
	WebElement errCity;

	@FindBy(xpath = "//span[normalize-space()=\"Field 'Zip' cannot be empty.\"]")
	WebElement errEmptyZip;

	@FindBy(xpath = "//span[normalize-space()='Invalid format. Only digits allowed.']")
	WebElement errInvalidZip;
	@FindBy(xpath = "//span[normalize-space()='Select a card type.']")
	WebElement errSelectCard;

	@FindBy(xpath = "//span[normalize-space()=\"Field 'Card Nr' cannot be empty.\"]")
	WebElement errEmptyCardNr;

	@FindBy(xpath = "//span[normalize-space()='Invalid format. Only digits allowed.'][@id ='ctl00_MainContent_fmwOrder_RegularExpressionValidator2']")
	WebElement errInvalidCardNr;

	@FindBy(xpath = "//span[normalize-space()=\"Field 'Expire date' cannot be empty.\"]")
	WebElement errEmptyExpiry;

	@FindBy(xpath = "//span[normalize-space()=\"Invalid format. Required format is mm/yy.\"]")
	WebElement errInvalidExpiry;

	// Methods

	public void getContent() {

		String content = wait.until(ExpectedConditions.visibilityOf(lblContent)).getText();
		System.out.println(content);
	}

	public void updateProduct(String product) {
		Select select = new Select(ddlProduct);
		select.selectByVisibleText(product);

	}

	public void updatedQty(String qty) {
		txtQuantity.clear();
		txtQuantity.sendKeys(qty);
	}

	public void updateCustomerName(String custName) {
		txtCustName.clear();
		txtCustName.sendKeys(custName);
	}

	public void updateStreet(String street) {
		txtStreet.clear();
		txtStreet.sendKeys(street);
	}

	public void updateCity(String city) {
		txtCity.clear();
		txtCity.sendKeys(city);
	}

	public void updateState(String state) {
		txtState.clear();
		txtState.sendKeys(state);
	}

	public void updateZip(String zip) {
		txtZip.clear();
		txtZip.sendKeys(zip);
	}

	private WebElement getCardElement(String card) {
		String radioBtnXpath = "//input[@value= '" + card + "']";
		return driver.findElement(By.xpath(radioBtnXpath));

	}

	public void updateCard(String card) {
		getCardElement(card).click();

	}

	public void updateCardNr(String cardNr) {
		txtCardNr.clear();
		txtCardNr.sendKeys(cardNr);
	}

	public void updateExpiry(String expiry) {
		txtExpiry.clear();
		txtExpiry.sendKeys(expiry);
	}

	public void clickUpdate() {
		btnUpdate.click();

	}

	public void clickCalculate() {
		btnCalculate.click();
	}

	public String getEmptyQtyError() {
		return errEmptyQty.getText();
	}

	public String getInvalidQtyError() {
		return errInvalidQty.getText();
	}

	public String getCustNrError() {
		return errCustNr.getText();
	}

	public String getStreetError() {
		return errStreet.getText();
	}

	public String getCityError() {
		return errCity.getText();
	}

	public String getEmptyZipError() {
		return errEmptyZip.getText();
	}

	public String getInvalidZipError() {
		return errInvalidZip.getText();
	}

	public String getEmptyCardError() {
		return errEmptyCardNr.getText();
	}

	public String getInvalidCardError() {
		return errInvalidCardNr.getText();
	}

	public String getCardError() {
		return errSelectCard.getText();
	}

	public String getEmptyExpiryError() {
		return errEmptyExpiry.getText();
	}

	public String getInvalidExpiryError() {
		return errInvalidExpiry.getText();
	}

	public void updateFieldSuccess(String product, String qty, String custName, String street, String city,
			String state, String zip, String card, String cardNr, String expiry) {
		if (product != null)
			updateProduct(product);
		if (qty != null)
			updatedQty(qty);
		if (product != null || qty != null)
			clickCalculate();
		if (custName != null)
			updateCustomerName(custName);
		if (street != null)
			updateStreet(street);
		if (city != null)
			updateCity(city);
		if (state != null)
			updateState(state);
		if (zip != null)
			updateZip(zip);
		if (card != null)
			updateCard(card);
		if (cardNr != null)
			updateCardNr(cardNr);
		if (expiry != null)
			updateExpiry(expiry);

		clickUpdate();

	}

	public void validateError(String product, String qty, String custName, String street, String city, String state,
			String zip, String card, String cardNr, String expiry, String expectedResult) {

		switch (expectedResult) {

		case "sucess":
			break;
		case "empty_quantity":
			updatedQty(qty);
			clickCalculate();
			Assert.assertTrue(errEmptyQty.isDisplayed());
			break;
		case "invalid_quantity":
			updatedQty(qty);
			clickUpdate();
			Assert.assertTrue(errInvalidQty.isDisplayed());
			break;
		case "empty_name":
			updateCustomerName(custName);
			clickUpdate();
			Assert.assertTrue(errCustNr.isDisplayed());
			break;
		case "empty_street":
			updateStreet(street);
			clickUpdate();
			Assert.assertTrue(errStreet.isDisplayed());
			break;
		case "empty_city":
			updateCity(city);
			clickUpdate();
			Assert.assertTrue(errCity.isDisplayed());
			break;
		case "empty_zip":
			updateZip(zip);
			clickUpdate();
			Assert.assertTrue(errEmptyZip.isDisplayed());
			break;
		case "invalid_zip":
			updateZip(zip);
			clickUpdate();
			Assert.assertTrue(errInvalidZip.isDisplayed());
			break;
		case "empty_card":
			updateCard(card);
			clickUpdate();
			Assert.assertTrue(errSelectCard.isDisplayed());
			break;
		case "empty_cardnr":
			updateCardNr(cardNr);
			clickUpdate();
			
			Assert.assertTrue(errEmptyCardNr.isDisplayed());
			break;
		case "invalid_cardnr":
			updateCardNr(cardNr);
			
			clickUpdate();
			//wait.until(ExpectedConditions.textToBePresentInElement(errInvalidCardNr, "Invalid format"));
			
			Assert.assertTrue(errInvalidCardNr.isDisplayed());
			break;
		case "empty_expdate":
			updateExpiry(expiry);
			clickUpdate();
			Assert.assertTrue(errEmptyExpiry.isDisplayed());
			break;
		case "invalid_expdate":
			updateExpiry(expiry);
			clickUpdate();
			Assert.assertTrue(errInvalidExpiry.isDisplayed());
			break;

		}

	}

	/*
	 * public void updateForm(String product, String qty, String custName, String
	 * street, String city, String state, String zip, String card, String cardNr,
	 * String expiry, String expectedResult) { if
	 * (expectedResult.equalsIgnoreCase("success")) { updateFieldSuccess(product,
	 * qty, custName, street, city, state, zip, card, cardNr, expiry); } else {
	 * validateError(product, qty, custName, street, city, state, zip, card, cardNr,
	 * expiry, expectedResult); } }
	 */

}
