package iss.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Test_cases {
	
	private String thread_name;
	private int thread_num;
	private ArrayList<Units> units;
	private WebDriver driver;
	private int seq_loop;
	
	public Test_cases(String thread_name, int thread_num, ArrayList<Units> units,int seq_loop) {
		this.thread_name = thread_name;
		this.thread_num = thread_num;
		this.units = units;
		this.seq_loop = seq_loop;
	}
	
	
	public void open_chrome_browser() {
  		//Create Chrome driver to drive the browser
		
		String os = System.getProperty("os.name");
		System.out.println("os=" + os);
		String driver_path = null;
		if (os.equals("Linux")) {
			driver_path = "/opt/Selenium/Selenium/chromedriver/chromedriver";
		} else {
			driver_path = "E:\\Celestica\\temp\\test.txt";
		}
		
		String url = "http://"+units.get(thread_num).get_ip();	
  		System.setProperty("webdriver.chrome.driver", driver_path);
  		DesiredCapabilities dc = new DesiredCapabilities();
  		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
  		this.driver = new ChromeDriver(dc);
  		driver.get(url);
  		units.get(thread_num).calculate_window_size();
  		int width = units.get(thread_num).get_window_width();
  		int height = units.get(thread_num).get_window_height();
  		int x = units.get(thread_num).get_window_x();
  		int y = units.get(thread_num).get_window_y();
  		driver.manage().window().setSize(new Dimension(width,height));
  		driver.manage().window().setPosition(new Point(x,y));
	}
	
	public void close_chrome_browser() {
		driver.quit();
		
	}
	
	public void load_scanin(String url) {
		//initialize 
		Select select;
		
		// move to url
		driver.get(url);
		
		
		//choose the operation and select the option defined in config file.
		WebElement dropdown =driver.findElement(By.name("operation"));
		System.out.println("test =" +dropdown.getText());
		select = new Select(dropdown);
		select.selectByVisibleText(units.get(thread_num).get_test_seq().get(seq_loop));
		
		//fill in the operator ID
		WebElement txtbox =driver.findElement(By.name("operatorid"));
		txtbox.sendKeys(units.get(thread_num).get_id());
		
		//choose the Tester Slot and select the option defined in config file.
		dropdown =driver.findElement(By.name("testerslot"));
		select = new Select(dropdown);
		select.selectByVisibleText(units.get(thread_num).get_slot());
		
		//choose the Tester Slot and select the option defined in config file.
		dropdown =driver.findElement(By.name("testerslot"));
		select = new Select(dropdown);
		select.selectByVisibleText(units.get(thread_num).get_slot());
		
		//choose the Tester Slot and select the option defined in config file.
		dropdown = driver.findElement(By.name("testerslot"));
		select = new Select(dropdown);
		select.selectByVisibleText(units.get(thread_num).get_slot());
				
		// choose the Part Number and select the option defined in config file.
		dropdown = driver.findElement(By.name("part_number"));
		select = new Select(dropdown);
		select.selectByVisibleText(units.get(thread_num).get_part_num());
		
		//fill in the enclosure 1S
		txtbox =driver.findElement(By.name("enclosure_1s"));
		txtbox.sendKeys(units.get(thread_num).get_serial());
		
		
		//fill in the enclosure 11S if exists
		if (!units.get(thread_num).get_encl_11s().equals("")) {
			txtbox =driver.findElement(By.name("enclosure_11s"));
			txtbox.sendKeys(units.get(thread_num).get_encl_11s());
		}
	
		//click submit and wait max 15 sec.
		int flag = 0;
		driver.findElement(By.name("Submit")).click();
		long startTime = System.currentTimeMillis();
		while(false||(System.currentTimeMillis()-startTime)<15000) {
			url = driver.getCurrentUrl();
			if (url.contains("proceed-scanin") || url.contains("build_xml")) {
				flag=1;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				break;
			}
		}
		
		if (flag == 1) {
			//calling Error Handler 
			Error_handler errhandler = new Error_handler(driver);
			errhandler.analyze("scanin");
		} else {
			System.out.println("Loading Scan-in successful");
			// done successfully
		}
		
	}
	
	public void start_unit() {
		// initialize
		Select select;
		
		// move to url
		String url = "http://" + units.get(thread_num).get_ip() + ":8080/iss/view?type=delivery-in&unit=&testSuite=&testSet=&testEvent=";
		driver.get(url);
		
		//select the checkbox
		if (!driver.findElement(By.name(units.get(thread_num).get_serial() +".xml")).isSelected()) {
		     driver.findElement(By.name(units.get(thread_num).get_serial() +".xml")).click();
		}
		
		//click start test
		driver.findElement(By.xpath("/html/body/form/input[@value='Start Test']")).click();
		try {
			Thread.sleep((units.size() * units.size() * 1000) + 3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void check_out_unit() {
		String url = "http://" + units.get(thread_num).get_ip() + ":8080/iss/view?type=delivery-out";
		driver.get(url);
		List<WebElement> finished_units = driver.findElements(By.name(units.get(thread_num).get_serial() +".xml"));

		//select the checkbox
		if (!finished_units.get(1).isSelected()) {		
			finished_units.get(1).click();
		}
		
		try {
			driver.findElement(By.xpath("/html/body/form/input")).click();
			Thread.sleep(1000);
			Alert alert = driver.switchTo().alert();
		    String alertText = alert.getText();
		    System.out.println("Alert data: " + alertText);
		    alert.accept();
			
			Thread.sleep((units.size() * 1000) + 3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("testing");
			e.printStackTrace();
		}
		
				
	}
	
	public void retest_unit() {
		String url = "http://" + units.get(thread_num).get_ip() + ":8080/iss/view?type=delivery-out";
		driver.get(url);
		List<WebElement> finished_units = driver.findElements(By.name(units.get(thread_num).get_serial() +".xml"));

		//select the checkbox
		if (!finished_units.get(2).isSelected()) {		
			finished_units.get(2).click();
		}
		
		//click retest on check-out
		try {
			driver.findElement(By.xpath("/html/body/form/input")).click();
			Thread.sleep(1000);
			
			Thread.sleep((units.size() * 1000) + 3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("testing");
			e.printStackTrace();
		}
		
		//
		
	}
	
	public void disable_HBA_popup() {
		
		
		try {
			//modify unit.properties and list.properties
			String line;
			String unit_path = "/export/home/domain/iss/units/" + units.get(thread_num).get_serial() + "/unit.properties";
			String unit_path_tmp = "/export/home/domain/iss/units/" + units.get(thread_num).get_serial() + "/unit.properties.tmp";
			String list_path = "/export/home/domain/iss/units/" + units.get(thread_num).get_serial() + "/test_suite_" + units.get(thread_num).get_test_seq().get(seq_loop) + "/list.properties";
			String list_path_tmp = "/export/home/domain/iss/units/" + units.get(thread_num).get_serial() + "/test_suite_" + units.get(thread_num).get_test_seq().get(seq_loop) + "/list.properties.tmp";
			FileReader unit_input  = new FileReader(unit_path);
			FileWriter unit_output = new FileWriter(unit_path_tmp);
			
			BufferedReader unit_input_file = new BufferedReader(unit_input);
			BufferedWriter unit_output_file = new BufferedWriter(unit_output);

			String hba_loc_pattern="HBA_Location=(.*)";
			Pattern hba_loc_p = Pattern.compile(hba_loc_pattern);
			int match_flag = 0;
			while ((line = unit_input_file.readLine()) != null) {
				Matcher hba_loc_m = hba_loc_p.matcher(line);
				if (hba_loc_m.matches()) {
					if (hba_loc_m.group(1).equals(units.get(thread_num).get_hba_loc())) {
						
					} else {
						line = "HBA_Location=" + units.get(thread_num).get_hba_loc();
					}
					match_flag = 1;
				} 
				
				unit_output.write(line);
				
			}
			if (match_flag == 0) {
				unit_output.write("HBA_Location=" + units.get(thread_num).get_hba_loc());
			}
			unit_input_file.close();
			unit_output_file.close();
			
			File oldFile = new File(unit_path);
			oldFile.delete();
			File newFile = new File(unit_path_tmp);
			newFile.renameTo(oldFile);
			
			line = "";
			FileReader list_input  = new FileReader(list_path);
			FileWriter list_output = new FileWriter(list_path_tmp);
			
			BufferedReader list_input_file = new BufferedReader(list_input);
			BufferedWriter list_output_file = new BufferedWriter(list_output);

			hba_loc_pattern="HBA_Location=(.*)";
			hba_loc_p = Pattern.compile(hba_loc_pattern);
			match_flag = 0;
			while ((line = unit_input_file.readLine()) != null) {
				Matcher hba_loc_m = hba_loc_p.matcher(line);
				if (hba_loc_m.matches()) {
					if (hba_loc_m.group(1).equals(units.get(thread_num).get_hba_loc())) {
						
					} else {
						line = "HBA_Location=" + units.get(thread_num).get_hba_loc();
					}
					match_flag = 1;
				} 
				
				list_output.write(line);
				
			}
			if (match_flag == 0) {
				list_output.write("HBA_Location=" + units.get(thread_num).get_hba_loc());
			}
			list_input_file.close();
			list_output_file.close();
			
			oldFile = new File(list_path);
			oldFile.delete();
			newFile = new File(list_path_tmp);
			newFile.renameTo(oldFile);
			
			
			//modify attribute.
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public String monitor_test() throws InterruptedException {
		
		String url = "http://" + units.get(thread_num).get_ip() +":8080/iss/view?type=viewer&unit=&testSuite=&testSet=&testEvent=&vt=0";
		
		
		while (true) {
			while (!driver.getCurrentUrl().equals(url)) {
				driver.get(url);
				driver.get(url);
				System.out.println("Unit" + thread_num + " Loading iss/view completed");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int unit_row_num = driver.findElements(By.xpath("/html/body/form[1]/table/tbody/tr")).size();
			String result;
			int search_check = 0;
			for (int i = 2; i <= unit_row_num; i++) {
				String web_serial = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[" + i + "]/td[3]/font/a")).getText();
				String unit_serial = units.get(thread_num).get_serial();
				
				if (web_serial.equals(unit_serial)) {
					search_check = 1;
					result = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[" + i + "]/td[2]/center/b/a/font")).getText();
					if (result.equals("Testing") || result.equals("Failing")) {
						// test is still running.
						// wait 15 secs and 
						//
						Thread.sleep(5000);
						System.out.println("Unit" + thread_num + "Sleep 5sec"); 
					} else if (result.equals("Passes")) {
						return "Passes";
					} else if (result.equals("Aborted")) {
						return "Aborted";						
					} else if (result.equals("Failed")) {
						return "Failed";
					} else {
						System.out.println("Unit" + thread_num + "return-2");
						return "Error2";
					}
					break;
				}
			}
			if (search_check == 0) {
				System.out.println("Unit" + thread_num + "return-1"); 
				return "Error1";
			}
		}
				
	}
	
}