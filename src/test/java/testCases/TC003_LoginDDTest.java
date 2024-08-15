package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data valid - login success - test pass - logout
 * Data valid - login fail - test fail
 * 
 * Data invalid - login failed - test pass
 * Data invalid - login success - test failed - logout
 * 
 */

public class TC003_LoginDDTest extends BaseClass{
	
	
@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")	
public void verifyLoginDDT(String email, String pwd, String expResult) {
		
		
	logger.info(" *** Starting TC003_LoginDDTTest ***");
	try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		
		hp.clickLogin();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLoginbttn();
		
		//My Account page
		MyAccountPage map=new MyAccountPage(driver);
		Boolean loginStatus=map.isMyAccountPageExists();
		
		if(expResult.equalsIgnoreCase("Valid")) {
			if(loginStatus==true) {
				map.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
				
			
		}
		if(expResult.equalsIgnoreCase("Invalid")) {
			if(loginStatus==true) {
				map.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		
	}
	catch(Exception e) {
		Assert.fail();
	}
	logger.info(" *** Exceuted TC003_LoginDDTTest ***");

}



}
