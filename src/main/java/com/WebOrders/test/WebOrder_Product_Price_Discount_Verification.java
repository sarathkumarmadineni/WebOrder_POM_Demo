package com.WebOrders.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.WebOrders.common.WebOrders_BasePage;
import com.WebOrders.common.WebOrders_BaseSetup;
import com.WebOrders.home.WebOrders_HomePage;
import com.WebOrders.home.WebOrders_OrdersPage;
import com.WebOrders.home.WebOrders_SignInPage;
import com.WebOrders.home.WebOrders_ViewAllProductsPage;
import com.WebOrders.utils.WebOrders_TestData;

public class WebOrder_Product_Price_Discount_Verification extends WebOrders_BaseSetup {

    WebOrders_SignInPage signIn;
    WebOrders_HomePage homePage;
    WebOrders_BasePage basePage;
    WebOrders_OrdersPage orderPage;

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        driver = getDriver();
        basePage = new WebOrders_BasePage(driver);

        // LOGIN ONLY ONCE
        signIn = basePage.GoToHomePageAndSignIn("Tester", "test");

        homePage = new WebOrders_HomePage(driver);
    }

    @Test(description = "Verify Product price in Order Page",
            dataProvider = "ProductPrice",
            dataProviderClass = WebOrders_TestData.class)
    public void verifyProductPrice(String product, String expectedPrice) throws Exception {

        test = extent.createTest("Verify Product Price for " + product);

        // Step 1 → Go to View All Products page
        WebOrders_ViewAllProductsPage productPage = homePage.clickOnAllProductsTab();

        String actualPrice = productPage.getProductPrice(product);

        // Validate price from products table
        Assert.assertEquals(actualPrice, expectedPrice);

        // Step 2 → Go to Order Page
        orderPage = homePage.clickOnOrdersTab();

        orderPage.selectProduct(product);
        orderPage.enterQuantity("1");
        orderPage.clickCalculate();

        // Step 3 → Verify price in Order Page
        Assert.assertEquals(orderPage.getUnitPrice(), expectedPrice.replace("$", ""));
    }

    @AfterClass
    public void tearDown() throws InterruptedException {

        // LOGOUT ONLY ONCE
        basePage.logout();

        driver.quit();
    }
}
    
    


