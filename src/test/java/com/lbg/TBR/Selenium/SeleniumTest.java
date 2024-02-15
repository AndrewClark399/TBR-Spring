package com.lbg.TBR.Selenium;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
import com.lbg.TBR.SpringTbrSpringApplication;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = { SpringTbrSpringApplication.class })
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:estateAgent-schema.sql",
		"classpath:estateAgent-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class SeleniumTest {

	private RemoteWebDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();

		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

//	@Test
//	@Order(3)
//	void testCreateEstateAgent() {
//		this.driver.get("http://localhost:3000");
//
//	}

	@Test
	@Order(1)
	void testGetBuyers() {
		this.driver.get("http://localhost:3000/Buyers");

		WebElement itemAppears = this.driver.findElement(
				By.cssSelector("#root > header > div > div > div > div > div:nth-child(1) > div > div > h4"));
		Assertions.assertEquals("Poca Hontas", itemAppears.getText());
	}

	@Test
	@Order(2)
	void testCreateBuyer() {
		this.driver.get("http://localhost:3000/Buyers");
		String firstName = "Meta";
		String lastName = "Phorical";
		String address = "1 Coach Potato";
		String postcode = "C0 8CH";
		String phoneNumber = "0758473937";

		WebElement name = this.driver.findElement(By.id("fn"));
		name.sendKeys(firstName);

		WebElement name2 = this.driver.findElement(By.id("ln"));
		name2.sendKeys(lastName);

		WebElement location = this.driver.findElement(By.id("ad"));
		location.sendKeys(address);

		WebElement code = this.driver.findElement(By.id("pc"));
		code.sendKeys(postcode);

		WebElement number = this.driver.findElement(By.id("pn"));
		number.sendKeys(phoneNumber);

		WebElement addBuyer = this.driver.findElement(By.cssSelector("#root > header > div > form > button"));
		addBuyer.click();

		WebElement createdBuyer = this.driver.findElement(
				By.cssSelector("#root > header > div > div > div > div > div:nth-child(2) > div > div > h4"));

		WebElement createdBuyerAddress = this.driver.findElement(By
				.cssSelector("#root > header > div > div > div > div > div:nth-child(2) > div > div > p:nth-child(2)"));

		WebElement createdBuyerPostCode = this.driver.findElement(By
				.cssSelector("#root > header > div > div > div > div > div:nth-child(2) > div > div > p:nth-child(3)"));

		WebElement createdBuyerPhoneNumber = this.driver.findElement(By
				.cssSelector("#root > header > div > div > div > div > div:nth-child(2) > div > div > p:nth-child(4)"));

		Assertions.assertEquals((firstName + " " + lastName), createdBuyer.getText());
		Assertions.assertEquals((address), createdBuyerAddress.getText());
		Assertions.assertEquals((postcode), createdBuyerPostCode.getText());
		Assertions.assertEquals((phoneNumber), createdBuyerPhoneNumber.getText());

	}

	@Test
	@Order(3)
	void testGetSellers() {
		this.driver.get("http://localhost:3000/Sellers");

		WebElement itemAppears = this.driver
				.findElement(By.cssSelector("#root > header > div > div > div > div > div > div > div > div > h4"));
		Assertions.assertEquals("John Smith", itemAppears.getText());
	}

	@Test
	@Order(4)
	void testCreateSeller() {
		this.driver.get("http://localhost:3000/Sellers");
		String firstName = "Meta";
		String lastName = "Physical";
		String address = "4 Uncertain Potato";
		String postcode = "C0 9CH";
		String phoneNumber = "0758473937";

		WebElement name = this.driver.findElement(By.id("fn"));
		name.sendKeys(firstName);

		WebElement name2 = this.driver.findElement(By.id("ln"));
		name2.sendKeys(lastName);

		WebElement location = this.driver.findElement(By.id("ad"));
		location.sendKeys(address);

		WebElement code = this.driver.findElement(By.id("pc"));
		code.sendKeys(postcode);

		WebElement number = this.driver.findElement(By.id("pn"));
		number.sendKeys(phoneNumber);

		WebElement addSeller = this.driver.findElement(By.cssSelector("#root > header > div > form > button"));
		addSeller.click();

		WebElement createdSeller = this.driver.findElement(
				By.cssSelector("#root > header > div > div > div > div > div > div:nth-child(2) > div > div > h4"));

		WebElement createdSellerAddress = this.driver.findElement(By.cssSelector(
				"#root > header > div > div > div > div > div > div:nth-child(2) > div > div > p:nth-child(2)"));

		WebElement createdSellerPostCode = this.driver.findElement(By.cssSelector(
				"#root > header > div > div > div > div > div > div:nth-child(2) > div > div > p:nth-child(3)"));

		WebElement createdSellerPhoneNumber = this.driver.findElement(By.cssSelector(
				"#root > header > div > div > div > div > div > div:nth-child(2) > div > div > p:nth-child(4)"));

		Assertions.assertEquals((firstName + " " + lastName), createdSeller.getText());
		Assertions.assertEquals((address), createdSellerAddress.getText());
		Assertions.assertEquals((postcode), createdSellerPostCode.getText());
		Assertions.assertEquals((phoneNumber), createdSellerPhoneNumber.getText());
	}

//	@Test
//	@Order(5)
//
//	void testEditSelller() {
//		this.driver.get("http://localhost:3000/Sellers/Edit/1");
//		String firstName = "Bob";
//
//		WebElement fname = this.driver.findElement(By.id("fn"));
//		fname.sendKeys(firstName);
//		WebElement submit = this.driver.findElement(By.cssSelector("#submitBuyer"));
//		submit.click();
//		WebElement checkName = this.driver
//				.findElement(By.cssSelector("#root > header > div > div > div > div > div > div > div > div > h4"));
//		Assertions.assertEquals((firstName), checkName.getText());
//	}

	@Test
	@Order(6)
	void testDeleteSeller() {

		this.driver.get("http://localhost:3000/Sellers");
		WebElement deleteSeller = this.driver.findElement(By
				.cssSelector("#root > header > div > div > div > div > div > div > div > div > button.btn.btn-danger"));
		deleteSeller.click();
		wait.until(ExpectedConditions.invisibilityOf(deleteSeller));
		List<WebElement> sellerCards = this.driver.findElements(By.className("card"));
		Assertions.assertEquals(0, sellerCards.size());
	}

	@AfterEach
	public void tearDown() {



	}

}