package iss.tests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
		String url = "http://"+units.get(thread_num).get_ip();	
  		System.setProperty("webdriver.chrome.driver", "E:\\Celestica\\Selenium\\Selenium\\Selenium\\chromedriver\\chromedriver_win32\\chromedriver.exe");
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
		driver.get(url);
		WebElement txtbox =driver.findElement(By.name("operation"));
		//System.out.println("test =" +txtbox.getText());
		
		
	}
	
	public void start_unit() {
		
	}
	
	public void check_out_unit() {
		
	}
	
	public void retest_unit() {
		
	}
	
	public void monitor_test() {
		
	}
	
}
