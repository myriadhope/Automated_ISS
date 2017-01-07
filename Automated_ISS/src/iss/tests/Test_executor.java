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
	    	  
	    	int seq_loop = 0;
	    	while (seq_loop < units.get(thread_num).get_total_test_seq()) { 	    	  
	    		
	    		Test_profile test_profile = new Test_profile(thread_name, thread_num, units, seq_loop);
	    		test_profile.Test_profile_exec();	
	    		String test_result = test_profile.get_result();
	    		if (test_result.equals("Passes")) {
	    			
	    		} else if (test_result.equals("Failed")) {
	    			
	    		} else if (test_result.equals("Aborted")) {
	    			break;
	    		} else if (test_result.equals("Error1")) {
	    			break;
	    		} else if (test_result.equals("Error2")) {
	    			break;
	    		} else {
	    			break;
	    		}
	  			
	    		Thread.sleep(500);
	  			seq_loop++;
	    	}
	  		
	      } catch (InterruptedException e) {
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