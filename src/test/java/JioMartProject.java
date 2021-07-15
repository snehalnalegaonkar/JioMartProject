import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class JioMartProject {
	
	WebDriver driver;
	public final String signinBtn = "//div[@class='logged']";
	public final String mobiletxt = "//div[@class='md-form phinput position-relative']//input";
	public final String submitBtn = "//button[@class='btn-signpass arrowbtn']";
	public final String staplesAction  = "//a[normalize-space()='Staples']";
	public final String staplesAttaProduct = "//li[@class='o-menu']//ul//li//a[contains(text(),'Atta, Flours & Sooji')]";
	public final String attaProduct = "//div[@class='col-md-3 p-0']//img[@alt='Aashirvaad Whole Wheat Atta 1 kg']";
	public final String ProductAddToCart = "//span[@class='txt_btn']";
	public final String dairyAction = "//li[@class='o-menu']//a[contains(text(),'Dairy & Bakery')]";
	public final String dairyCookiesProduct = "//li[@class='o-menu']//ul//li//a[contains(text(),'Baked Cookies')]";
	public final String cookiesProduct = "//div[@class='col-md-3 p-0']//img[@alt='Lovely Coconut Cookies 200 g']";
	public final String cart = "//div[@class='text']";
	public final String plus1 = "(//div[@class='product-qty col float-right']//div//span[@class='plus'])[1]";
	public final String plus2 = "(//div[@class='product-qty col float-right']//div//span[@class='plus'])[2]";
	public final String plus3 = "(//div[@class='product-qty col float-right']//div//span[@class='plus'])[3]";
	public final String Beverages = "//a[contains(@class,'cat-submenu')][normalize-space()='Beverages']";
	public final String Tea = "//a[normalize-space()='Tea']";
	public final String TeaProduct = "//div[@class='col-md-3 p-0']//img[@alt='Red Label Leaf Tea 1 kg']";

	@Test(priority = 0)
	public void LaunchApplication() throws IOException
	{
		System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
		driver=new ChromeDriver();
		System.out.println("Maximize browser");
		driver.manage().window().maximize();
		System.out.println("Get Jio Mart Website on browser");
		driver.get("https://www.jiomart.com/");
		System.out.println("Clicking on Sign in button");
		driver.findElement(By.xpath(signinBtn)).click();
		System.out.println("Entering Mobile number");
		driver.findElement(By.xpath(mobiletxt)).sendKeys("1234567898");
		driver.findElement(By.xpath(submitBtn)).click();
	}

	@Test(priority = 1)
	public void GetTitle()
	{
		SoftAssert softassert = new SoftAssert();
		String actualtitle=driver.getTitle();
		System.out.println("Title of Page is: " +actualtitle);
		String expectedtitle = "Buy Grocery Online at Best Prices Pan India";
		softassert.assertEquals(expectedtitle, actualtitle);
	}

	@Test(priority = 2)
	public void getProductDetails() throws Exception {
		Thread.sleep(2000);
		System.out.println("Creating object of an Actions class");
		Actions action1 = new Actions(driver);
		System.out.println("Mouse hover on Atta");
		WebElement staples = driver.findElement(By.xpath(staplesAction));
		System.out.println("Performing the mouse hover action on the target element.");
		action1.moveToElement(staples).build().perform();
		driver.findElement(By.xpath(staplesAttaProduct)).click();
		driver.findElement(By.xpath(attaProduct)).click();
		System.out.println("Atta Added to the Cart");
		driver.findElement(By.xpath(ProductAddToCart)).click();
		System.out.println("Time require to load page");
		Thread.sleep(2000);
		System.out.println("Creating object of an Actions class");
		Actions action2 = new Actions(driver);
		System.out.println("Mouse hover on Atta");
		WebElement dairy = driver.findElement(By.xpath(dairyAction));
		System.out.println("Performing the mouse hover action on the target element.");
		action2.moveToElement(dairy).build().perform();
		driver.findElement(By.xpath(dairyCookiesProduct)).click();
		driver.findElement(By.xpath(cookiesProduct)).click();
		System.out.println("Cookies Added to the Cart");
		driver.findElement(By.xpath(ProductAddToCart)).click();
		//Tea is not available to Access from Top Menu;
		//Clicking on any one of the top menu items enables left navigation menu
		System.out.println("Click on Staples");
		driver.findElement(By.xpath(staplesAction)).click();
		//Left Navigation Menu has sub menu item Tea under Beverages
		System.out.println("Click on Baverages");
		driver.findElement(By.xpath(Beverages)).click();
		System.out.println("Time require to load page");
		Thread.sleep(2000);
		driver.findElement(By.xpath(Tea)).click();
		System.out.println("click on Tea product");
		driver.findElement(By.xpath(TeaProduct)).click();
		System.out.println("Tea  Added to the Cart");
		driver.findElement(By.xpath(ProductAddToCart)).click();
		System.out.println("click on cart");
		driver.findElement(By.xpath(cart)).click();
		System.out.println("increase quantity of Atta");
		driver.findElement(By.xpath(plus1)).click();
		System.out.println("increase quantity of Cookies");
		try {
			driver.findElement(By.xpath(plus2)).click();
		}catch (Exception ex) {
			driver.findElement(By.xpath(plus2)).click();
	}
		System.out.println("increase quantity of Tea");
		try {
			driver.findElement(By.xpath(plus3)).click();
		}catch (Exception ex) {
			driver.findElement(By.xpath(plus3)).click();
		}
		Thread.sleep(2000);
		System.out.println("Taking Screenshot");
		this.takeSnapShot(driver, "d://test_1.png") ;
		}

	public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
		System.out.println("Convert web driver object to TakeScreenshot");
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		System.out.println("Call getScreenshotAs method to create image file");
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		System.out.println("Move image file to new destination");
		File DestFile=new File(fileWithPath);
		System.out.println("Copy file at destination");
		FileUtils.copyFile(SrcFile, DestFile);
	}

	@Test(priority = 3)
	public void teardown()
	{
		driver.close();
		driver.quit();
	}

}
