package com.lbg.TBR.Selenium;

import java.time.Duration;

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

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:estateAgent-schema.sql",
		"classpath:estateAgent-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class SeleniumPropertyTest {

	private RemoteWebDriver driver;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test
	@Order(1)
	void testGetPropertiesForSale() {
		this.driver.get("http://localhost:3000/PropertiesForSale");

	}

	@Test
	@Order(2)
	void testCreatePropertiesForSale() {
		this.driver.get("http://localhost:3000/PropertiesForSale");

		Double price = 200000.00;
		String address = "123 Andrew Clark Close";
		String garden = "yes";
		String type = "flat";
		String postcode = "AC1 1AC";

		WebElement bathroomNew = this.driver.findElement(By.id("bt"));
		bathroomNew.sendKeys("5");

		WebElement bedroomNew = this.driver.findElement(By.id("bd"));
		bedroomNew.sendKeys("3");

		WebElement priceNew = this.driver.findElement(By.id("pr"));
		priceNew.sendKeys("200000.00");

		WebElement addressNew = this.driver.findElement(By.id("ad"));
		addressNew.sendKeys(address);

		WebElement gardenNew = this.driver.findElement(By.id("gn"));
		gardenNew.sendKeys(garden);

		WebElement typeNew = this.driver.findElement(By.id("ty"));
		typeNew.sendKeys(type);

		WebElement postcodeNew = this.driver.findElement(By.id("pc"));
		postcodeNew.sendKeys(postcode);

		WebElement submitP = this.driver.findElement(By.id("submitProperty"));
		submitP.submit();

		WebElement checkProperty = this.driver.findElement(By.cssSelector(
				"#root > header > div > div.container-fluid > div > div:nth-child(2) > div > div > p:nth-child(14)"));
		Assertions.assertEquals(("Address: " + address), checkProperty.getText());

	}

	@Test
	@Order(4)
	void testGetBookingsPropertiesForSale() {
		this.driver.get("http://localhost:3000/PropertiesForSale");

		WebElement bookViewing = this.driver.findElement(By.cssSelector(
				"#root > header > div > div.container-fluid > div > div > div > div > button:nth-child(17)"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", bookViewing);
		bookViewing.click();

	}

	@Test
	@Order(3)
	void testCreateBookingsPropertiesForSale() {
		this.driver.get("http://localhost:3000/PropertiesForSale/BookingSale/1");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String fullName = "Total Genius";
		String email = "Rocking@theworld.com";
		String phone = "0758493748";

		WebElement name = this.driver.findElement(By.id("fn"));
		name.sendKeys(fullName);

		WebElement contactEmail = this.driver.findElement(By.id("ln"));
		contactEmail.sendKeys(email);

		WebElement mobile = this.driver.findElement(By.id("ad"));
		mobile.sendKeys(phone);

		WebElement dateBox = driver.findElement(By.xpath("//*[@id=\"pc\"]"));

		dateBox.sendKeys("10102024");

		WebElement timePick = this.driver
				.findElement(By.cssSelector("#root > header > div > div > div.row > div:nth-child(1) > form > select"));

		timePick.sendKeys("11:00-12:00");

		WebElement submit = this.driver
				.findElement(By.cssSelector("#root > header > div > div > div.row > div:nth-child(1) > form > button"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", submit);
		submit.click();

		WebElement checkBooking = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#root > header > div > div > div.card > table > tbody > tr:nth-child(2) > td:nth-child(2)")));
		Assertions.assertEquals((email), checkBooking.getText());

	}

	@Order(5)
	void PropertyForSale() {
		this.driver.get("http://localhost:3000/PropertiesForSale");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement Bathroom = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bt")));
		Bathroom.sendKeys("5");

		WebElement bedroom = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bd")));
		bedroom.sendKeys("3");

		Bathroom.getText();

		driver.quit();
	}

}