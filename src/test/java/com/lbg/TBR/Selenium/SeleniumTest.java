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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:estateAgent-schema.sql",
		"classpath:estateAgent-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class SeleniumTest {

	private RemoteWebDriver driver;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
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
		Assertions.assertEquals((firstName + " " + lastName), createdBuyer.getText());
	}

}