package iss.tests;

import java.io.IOException;
import java.util.ArrayList;

public class iss_main {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		Parse_config config = new Parse_config("E:\\Celestica\\temp\\test.txt");
		ArrayList<Units> units = config.Parse_content();			
		
		int total_units = units.size();		
		
		System.out.println("total_units = " + total_units);
		
		int thread_count = 0;
		Test_executor test[] = new Test_executor[total_units];
		while (thread_count < total_units) {
			test[thread_count] = new Test_executor( "Unit"+thread_count, units, thread_count, total_units);
			test[thread_count].start();
			
			thread_count++;
		}

		
		//Create firefox driver to drive the browser
		/*
		System.setProperty("webdriver.chrome.driver", "/opt/Selenium/Selenium/chromedriver/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		//Open Google home page
		driver.get("http://10.33.56.43");
		Thread.sleep(500);
		
		if (driver.findElements(By.id("activatebutton")).isEmpty()) {
			System.out.println("testing");
		} else {
			System.out.println("testing2");
		}
		*/
		
	}

}
