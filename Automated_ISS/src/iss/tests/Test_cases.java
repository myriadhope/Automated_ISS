package iss.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
  		this.driver = new ChromeDriver();
  		driver.get(url);
  		units.get(thread_num).calculate_window_size();
  		int width = units.get(thread_num).get_window_width();
  		int height = units.get(thread_num).get_window_height();
  		int x = units.get(thread_num).get_window_x();
  		int y = units.get(thread_num).get_window_y();
  		driver.manage().window().setSize(new Dimension(width,height));
  		driver.manage().window().setPosition(new Point(x,y));
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
	
	public void start_unit(String url) {
		// initialize
		Select select;

		// move to url
		driver.get(url);
		
		//select the checkbox
		if ( !driver.findElement(By.name(units.get(thread_num).get_serial() +".xml")).isSelected() )
		{
		     driver.findElement(By.name(units.get(thread_num).get_serial() +".xml")).click();
		}
		
		//click start test
		driver.findElement(By.xpath("/html/body/form/input[@value='Start Test']")).click();
		
	}
	
	public void check_out_unit() {
		
	}
	
	public void retest_unit() {
		
	}
	
	public void monitor_test() {
		String url = "http://" + units.get(thread_num).get_ip() +":8080/iss/view?type=viewer&unit=&testSuite=&testSet=&testEvent=&vt=0";
		driver.get(url);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.get(url);
		
		System.out.println(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[2]/td[2]/center/b/a/font")).getText());
		///html/body/form[1]/table/tbody/tr[2]/td[3]/font/a
		///html/body/form[1]/table/tbody/tr[2]/td[3]/font/a
		///html/body/form[1]/table/tbody/tr[2]/td[3]/font/a
		
	}
	
}