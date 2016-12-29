package iss.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Error_handler {

	private String error_msg;
	private String stage;
	private WebDriver driver;
	
	public Error_handler (WebDriver driver) {
		this.error_msg = "";
		this.stage = "";
		this.driver = driver;
		
	}
	
	
	public void analyze(String stage) {
		
		if (stage.equals("scanin")) {
			
			System.out.println(driver.findElement(By.tagName("body")).getText());
			this.error_msg = driver.findElement(By.tagName("body")).getText();
		}
		
	}
	
}
