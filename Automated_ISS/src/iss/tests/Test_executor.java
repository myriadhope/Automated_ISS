package iss.tests;

import java.util.ArrayList;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_executor implements Runnable {

	private Thread t;
	private String thread_name;
	private int thread_num;
	private ArrayList<Units> units;
	
	public Test_executor (String name, ArrayList<Units> units, int thread_number, int total_units) {
		thread_num = thread_number;
		thread_name = name;
		this.units = units;
		this.units.get(thread_number).add_unit_num(thread_number);
		this.units.get(thread_number).add_total_num(total_units);
		System.out.println("Creating " + thread_name);
		
				
	}
	@Override
	   public void run() {
	      System.out.println("Running " +  thread_name );
	      try {
	    	  
	  		//Create firefox driver to drive the browser
	    	
	  		System.setProperty("webdriver.chrome.driver", "E:\\Celestica\\Selenium\\Selenium\\Selenium\\chromedriver\\chromedriver_win32\\chromedriver.exe");
	  		WebDriver driver = new ChromeDriver();
	  		
	  		//Open Google home page
	  		String url = "http://"+units.get(thread_num).get_ip();
	  		driver.get(url);
	  		units.get(thread_num).calculate_window_size();
	  		int width = units.get(thread_num).get_window_width();
	  		int height = units.get(thread_num).get_window_height();
	  		int x = units.get(thread_num).get_window_x();
	  		int y = units.get(thread_num).get_window_y();
	  		System.out.println("width=" + width);
	  		System.out.println("height=" + height);
	  		System.out.println("x="+x);
	  		System.out.println("y="+y);
	  		driver.manage().window().setSize(new Dimension(width,height));
	  		driver.manage().window().setPosition(new Point(x,y));
	  		Thread.sleep(500);
	  		
	      }catch (InterruptedException e) {
	         System.out.println("Thread " +  thread_name + " interrupted.");
	      }
	      System.out.println("Thread " +  thread_name + " exiting.");
	   }
	   
	   public void start () {
	      System.out.println("Starting " +  thread_name );
	      if (t == null) {
	         t = new Thread (this, thread_name);
	         t.start ();
	      }
	   }

	
	
}
