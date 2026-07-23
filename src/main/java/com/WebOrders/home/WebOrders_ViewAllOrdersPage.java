package com.WebOrders.home;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebOrders_ViewAllOrdersPage {

	/*
	 * Verification of webtables. Deletion of records. Check all. Uncheck all. Edit
	 * any order.
	 */
	WebDriver driver;
	WebDriverWait wait;

	public WebOrders_ViewAllOrdersPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']")
	WebElement tblAllOrders;

	@FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr")
	List<WebElement> tblRows;

	@FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td/input[@type='image']")
	WebElement btnFirstRecordEdit;

	@FindBy(id = "ctl00_MainContent_btnCheckAll")
	WebElement btnCheckAll;

	@FindBy(id = "ctl00_MainContent_btnUncheckAll")
	WebElement btnUncheckAll;

	@FindBy(id = "ctl00_MainContent_btnDelete")
	WebElement btnDeleteSelected;

	public void validateTableIsDisplayed() {
		tblAllOrders.isDisplayed();
	}

	public WebElement getRowElement(String name, String product) {
		String dynamicXPath = "//table[@id='ctl00_MainContent_orderGrid']//td[contains(text(),'" + name
				+ "')]/following-sibling::td[contains(text(),'" + product + "')]";
		return driver.findElement(By.xpath(dynamicXPath));
	}

	// Edit Button
	// "//table[@id='ctl00_MainContent_orderGrid']//td[contains(text(),'Paul
	// Brown')]/following-sibling::td[contains(text(),'product')]/following-sibling::td//input"
	// Check Box
	// "//table[@id='ctl00_MainContent_orderGrid']//td[contains(text(),'Paul
	// Brown')]/following-sibling::td[contains(text(),'product')]/preceding-sibling::td//input"

	public void clickCheckBox(String name, String product) {
		WebElement rowElement = getRowElement(name, product);
		rowElement.findElement(By.xpath("./preceding-sibling::td//input")).click();
	}

	public WebOrders_EditOrderPage clickEdit(String name, String product) {
		WebElement rowElement = getRowElement(name, product);
		rowElement.findElement(By.xpath("./following-sibling::td//input")).click();
		return new WebOrders_EditOrderPage(driver);
	}

	public WebOrders_EditOrderPage clickEdit() {

		btnFirstRecordEdit.click();

		return new WebOrders_EditOrderPage(driver);
	}

	public void clickCheckAll() {

		wait.until(ExpectedConditions.elementToBeClickable(btnCheckAll)).click();
	}

	public void clickUncheckAll() {
		wait.until(ExpectedConditions.elementToBeClickable(btnUncheckAll)).click();

	}

	public void clickDelete() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDeleteSelected)).click();
	}

	public boolean verifyAllOrdersSelected() {

		// clickCheckAll();
		List<WebElement> checkboxs = tblAllOrders.findElements(By.xpath("//input[@type = 'checkbox']"));
		for (WebElement checked : checkboxs) {
			if (checked.isSelected() != true) {
				return false;
			}

		}
		return true;

	}

	public boolean verifyAllOrdersUnselected() {

		// clickUncheckAll();
		List<WebElement> checkboxs = tblAllOrders.findElements(By.xpath("//input[@type = 'checkbox']"));
		for (WebElement checked : checkboxs) {
			if (checked.isSelected()) {
				return false;
			}

		}
		return true;

	}

	public void deleteOrder(String name, String product) {
		clickCheckBox(name, product);
		clickDelete();

	}

	public boolean verifyDeletedRecord(String name) {
		String pgSource = driver.getPageSource();
		if (pgSource.contentEquals(name)) {
			return false;
		}
		return true;
	}

	public void verifyUpdatedOrder(String... values) {
		for (int i = 1; i < tblRows.size(); i++) { // Start from 1 assuming the first row is a header
			WebElement row = tblRows.get(i);
			List<WebElement> columns = row.findElements(By.tagName("td")); // Assuming "td" is the tag for table cells

			// Create a set to track which values have been found in the current row
			List<String> foundValues = new ArrayList<>();

			// Check each column in the row
			for (int j = 1; j < (columns.size() - 1); j++) {
				String columnVal = columns.get(j).getText();
				System.out.println(columnVal);

				// Check if the current column value matches any of the expected values
				for (String value : values) {
					if (columnVal.equals(value)) {
						//System.out.println(columnVal + " : " + value);

						foundValues.add(value); // Add the matching value to the set
						break; // No need to check other values once a match is found
					}
				}
			}
			System.out.println(values.length + "  " + foundValues.size());
			// After checking all columns in the row, verify if all values are found
			if (foundValues.size() == values.length) {
				// All values are found in this row, you can return or break if only one
				// matching row is needed
				return;
			}
		}

		// If no row contains all the values, throw an error
		throw new AssertionError("No row contains all the provided values: " + Arrays.toString(values));
	}

}
