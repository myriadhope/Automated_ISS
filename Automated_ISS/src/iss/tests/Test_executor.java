package iss.tests;

import java.util.ArrayList;

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
	    	// below is the test sequence.
	    	// each test is executing from top to bottom
	    	Test_cases test_case = new Test_cases(thread_name, thread_num, units);
	    	test_case.open_chrome_browser();	  	
	    	test_case.load_scanin();
	    	test_case.start_unit();
	    	test_case.monitor_test();
	  		
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
