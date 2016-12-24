package iss.tests;

import java.util.ArrayList;


public class Test_profile {
	
	private String name;
	private String result;
	private Test_cases test_case;
	
	
	public Test_profile(String thread_name, int thread_num, ArrayList<Units> units,int seq_loop) {
		this.name = units.get(thread_num).get_test_seq().get(seq_loop);
		System.out.println("name = "+ this.name);
		this.test_case = new Test_cases(thread_name, thread_num, units, seq_loop);		
		
	}
	
	public void Test_profile_exec() {	
		
		if(this.name.equals("EM_TMS_PROG_VPD")) {
			test_case.open_chrome_browser();	  	
    		test_case.load_scanin("http://10.33.56.43/SYSTEM_ODC_scanin.html");
    		test_case.start_unit();
    		test_case.monitor_test();
  		
		} else if (this.name.equals("EM_TMS_SYSTEM")) {
			test_case.open_chrome_browser();	  	
    		test_case.load_scanin("http://10.33.56.43/SYSTEM_ODC_scanin.html");
    		test_case.start_unit();
    		test_case.monitor_test();
  			
		} else if (this.name.equals("EM_TMS_SYS_RECONFIG")) {
			test_case.open_chrome_browser();	  	
    		test_case.load_scanin("http://10.33.56.43/SYSTEM_ODC_scanin.html");
    		test_case.start_unit();
    		test_case.monitor_test();
  				
		}
	}

}