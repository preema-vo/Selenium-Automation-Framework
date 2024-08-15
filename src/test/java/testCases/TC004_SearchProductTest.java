package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SerachProductLandingPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass{
	
	@Test(groups="Sanity")
	public void verifySearchProduct() {
		try {
			
			logger.info(" *** Starting TC002_LoginTest ***");
		HomePage hp=new HomePage(driver);
		hp.setSearchItem(p.getProperty("searchItem"));
		hp.clickSearchIcon();
		
		SerachProductLandingPage sp=new SerachProductLandingPage(driver);
		Boolean searchStaus=sp.isSearchProductPageExists();
		
		Assert.assertEquals(searchStaus, true, "Search failed");
		Assert.assertTrue(searchStaus);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info(" *** Executed TC002_LoginTest ***");
	}

}
