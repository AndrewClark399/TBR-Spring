package com.lbg.TBR.Selenium;

import java.time.Duration;

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

public class SeleniumExplicitWaitProperty {

	private RemoteWebDriver driver;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test
	@Order(1)
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
