package com.WebOrders.utils;

import org.testng.annotations.DataProvider;

public class WebOrders_TestData {
	@DataProvider(name = "Update Form")
	public static Object[][] UpdateOrderForm() {

		return new Object[][] {
				{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "12/29",
						"success" },
				{ "MyMoney", "5", "Nguyen", "123 Main St", "Dallas", "IL", "75000", "MasterCard", "123456789", "11/29",
						"success" }, };

	}

	@DataProvider(name = "Update Order Form Error")
	public static Object[][] verifyOrderFormError() {

		return new Object[][] {

				{ "MyMoney", "", "Minh", "123 Main St", "Dallas", "ABC", "75000", "Visa", "123456789", "12/29",
						"empty_quantity" },
				{ "MyMoney", "0", "Minh", "123 Main St", "Dallas", "ILA", "75000", "Visa", "123456789", "12/29",
						"invalid_quantity" },
				{ "MyMoney", "5", "", "123 Main St", "Dallas", "ILA", "75000", "Visa", "123456789", "12/29",
						"empty_name" },
				{ "MyMoney", "5", "Minh", "", "Dallas", "IL", "75000", "Visa", "123456789", "12/29", "empty_street" },
				{ "MyMoney", "5", "Minh", "123 Main St", "", "IL", "75000", "Visa", "123456789", "12/29",
						"empty_city" },
				{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "", "Visa", "123456789", "12/29",
						"empty_zip" },
				{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "abcde", "Visa", "123456789", "12/29",
						"invalid_zip" },
				{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "", "12/29", "empty_cardnr" },
				{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "abcdef", "12/29",
						"invalid_cardnr" },
				{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "",
						"empty_expdate" },
				{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "abcdef",
						"invalid_date" },
				{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "12-23",
						"invalid_date" }, };

	}

	@DataProvider(name = "Reset Form")
	public static Object[][] ResetForm() {

		return new Object[][] {
				{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "12/29",
						"success" },
				{ "MyMoney", "5", "Nguyen", "123 Main St", "Dallas", "IL", "75000", "MasterCard", "123456789", "11/29",
						"success" }, };

	}

	@DataProvider(name = "OrdersExcelData")
	public static Object[][] OrdersDataExcel() throws Exception {
		WebOrders_ExcelParsing excel = new WebOrders_ExcelParsing();
		String RelativePath = System.getProperty("user.dir");
		// Object[][] testObjArray = excel.getExcelData("C:\\Training_Scripts\\Selenium
		// Training
		// Data\\workspace\\Maven_Selenium_WebDriver_4\\TestDataFile\\WebOrder_Login_TestData.xls","Login");
		Object[][] testObjArray = excel.getExcelData(RelativePath + "\\TestDataFile\\WebOrder_Order_Data.xlsx",
				"orders");
		System.out.println(testObjArray);
		return testObjArray;

	}
	
	@DataProvider(name = "ProductPrice")
	public static Object[][] productPrice() {

	    return new Object[][] {
	        {"MyMoney", "$100"},
	        {"FamilyAlbum", "$80"},
	        {"ScreenSaver", "$20"}
	    };

	}

}
