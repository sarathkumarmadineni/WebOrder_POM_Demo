package com.WebOrders.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.WebOrders.common.WebOrders_BasePage;
import com.WebOrders.common.WebOrders_BaseSetup;
import com.WebOrders.home.WebOrders_EditOrderPage;
import com.WebOrders.home.WebOrders_HomePage;
import com.WebOrders.home.WebOrders_SignInPage;
import com.WebOrders.home.WebOrders_ViewAllOrdersPage;
import com.WebOrders.utils.WebOrders_TestData;

public class WebOrder_ViewAllOrders_Test extends WebOrders_BaseSetup {
	WebOrders_SignInPage signIn;
	WebOrders_HomePage homePage;
	WebOrders_BasePage basePage;
	WebOrders_ViewAllOrdersPage viewAllOrderPage;
	WebOrders_EditOrderPage editOrdersPage;
	private WebDriver driver;

	private String name = "Charles Dodgeson";
	private String product = "MyMoney";

	@BeforeClass
	public void setUp() {
		driver = getDriver();
		basePage = new WebOrders_BasePage(driver);
	}

	@Test(description = "Validate check all button", priority = 1, enabled = true)
	public void verifyCheckAll() throws Exception {
		test = extent.createTest("Test Case 1", "Verify if all orders are seleted if Check All button is clicked");
		signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
		signIn.verifySignInPageText();
		homePage = new WebOrders_HomePage(driver);
		viewAllOrderPage = homePage.clickOnAllOrdersTab();
		viewAllOrderPage.clickCheckAll();
		Assert.assertEquals(true, viewAllOrderPage.verifyAllOrdersSelected(), "All records are not selected.");
	}

	@Test(description = "Validate uncheck all button", priority = 2, enabled = true)
	public void verifyUnCheckAll() throws Exception {
		test = extent.createTest("Test Case 2", "Verify if all orders are unselected if UnCheck All button is clicked");

		viewAllOrderPage.clickUncheckAll();
		Assert.assertEquals(true, viewAllOrderPage.verifyAllOrdersUnselected(), "Records are still selected.");
	}

	@Test(description = "Validate user can delete an order", priority = 3, enabled = true)
	public void verifyDeleteOrder() throws Exception {
		test = extent.createTest("Test Case 3", "Verify if user can delete an order");

		viewAllOrderPage.deleteOrder(name, product);
		Assert.assertEquals(true, viewAllOrderPage.verifyDeletedRecord(name), "Record still Exists.");
	}

	@Test(description = "Validate user can edit an order", dataProvider = "Update Form", dataProviderClass = WebOrders_TestData.class, priority = 4, enabled = true)

	public void verifyEditOrder(String product, String qty, String custName, String street, String city, String state,
			String zip, String card, String cardNr, String expiry, String expectedResult) throws Exception {

		editOrdersPage = viewAllOrderPage.clickEdit();

		editOrdersPage.updateFieldSuccess(product, qty, custName, street, city, state, zip, card, cardNr, expiry);
		viewAllOrderPage = new WebOrders_ViewAllOrdersPage(driver);

		viewAllOrderPage.verifyUpdatedOrder(product, qty, custName, street, city, state, zip, card, cardNr, expiry);

	}

	@Test(description = "Validate error is displayed while editing an order with invalid Data", dataProvider = "Update Order Form Error", dataProviderClass = WebOrders_TestData.class, priority = 5, enabled = true)

	public void verifyError(String product, String qty, String custName, String street, String city, String state,
			String zip, String card, String cardNr, String expiry, String expectedResult) throws Exception {

		editOrdersPage = viewAllOrderPage.clickEdit();

		editOrdersPage.validateError(product, qty, custName, street, city, state, zip, card, cardNr, expiry,
				expectedResult);
		homePage = new WebOrders_HomePage(driver);
		homePage.clickOnAllOrdersTab();
		viewAllOrderPage = new WebOrders_ViewAllOrdersPage(driver);

	}

}
