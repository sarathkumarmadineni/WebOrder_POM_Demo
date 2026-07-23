package com.WebOrders.home;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebOrders_ViewAllProductsPage {

	WebDriver driver;
	WebDriverWait wait;

	public WebOrders_ViewAllProductsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	/*
	 * WebTable Verification
	 * Read Product Name
	 * Read Price
	 * Read Discount
	 */

	@FindBy(xpath = "//table[@class='ProductsTable']")
	WebElement tblProducts;

	@FindBy(xpath = "//table[@class='ProductsTable']//tr")
	List<WebElement> tblRows;

	// Verify table displayed
	public boolean validateProductTableDisplayed() {
		return tblProducts.isDisplayed();
	}

	// Get row based on Product Name
	public WebElement getRowElement(String productName) {

		String dynamicXpath = "//table[@class='ProductsTable']//tr[td[normalize-space()='"
				+ productName + "']]";

		return driver.findElement(By.xpath(dynamicXpath));
	}

	// Get Product Price
	public String getProductPrice(String productName) {

		WebElement row = getRowElement(productName);

		String price = row.findElement(By.xpath("./td[2]")).getText();

		return price;
	}

	// Get Product Discount
	public String getProductDiscount(String productName) {

		WebElement row = getRowElement(productName);

		String discount = row.findElement(By.xpath("./td[3]")).getText();

		return discount;
	}

	// Verify product exists in table
	public boolean verifyProductExists(String productName) {

		try {
			getRowElement(productName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Verify all product details in table
	public void verifyProductDetails(String productName, String price, String discount) {

		String actualPrice = getProductPrice(productName);
		String actualDiscount = getProductDiscount(productName);

		if (!actualPrice.equals(price) || !actualDiscount.equals(discount)) {
			throw new AssertionError(
					"Product details mismatch for " + productName +
					" Expected Price: " + price +
					" Actual Price: " + actualPrice +
					" Expected Discount: " + discount +
					" Actual Discount: " + actualDiscount);
		}
	}

	// Optional: Read entire table (useful for debugging)
	public void printAllProducts() {

		for (int i = 1; i < tblRows.size(); i++) {

			List<WebElement> cols = tblRows.get(i).findElements(By.tagName("td"));

			String product = cols.get(0).getText();
			String price = cols.get(1).getText();
			String discount = cols.get(2).getText();

			System.out.println(product + " | " + price + " | " + discount);
		}
	}

}