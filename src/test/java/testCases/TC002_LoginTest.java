package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups="Sanity")
	public void verifyLogin() {
		
		try {
		
		logger.info(" *** Starting TC002_LoginTest ***");
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		
		hp.clickLogin();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLoginbttn();
		
		//My Account page
		MyAccountPage map=new MyAccountPage(driver);
		Boolean loginStatus=map.isMyAccountPageExists();
		
		Assert.assertEquals(loginStatus, true, "Login failed");
		Assert.assertTrue(loginStatus);
		
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info(" *** Executed TC002_LoginTest ***");
	}

}
