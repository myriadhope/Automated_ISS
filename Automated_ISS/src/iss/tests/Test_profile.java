package iss.tests;

import java.util.ArrayList;


public class Test_profile {
	
	private String name;
	private String result;
	private Test_cases test_case;
	private ArrayList<Units> units;
	private int thread_num;
	
	public Test_profile(String thread_name, int thread_num, ArrayList<Units> units,int seq_loop) {
		this.name = units.get(thread_num).get_test_seq().get(seq_loop);
		this.units = units;
		this.thread_num = thread_num;
		this.result = "";
		System.out.println("name = "+ this.name);
		this.test_case = new Test_cases(thread_name, thread_num, units, seq_loop);		
		
	}
	
	public void Test_profile_exec() {	
		try {
			if(this.name.equals("EM_TMS_PROG_VPD")) {
				test_case.open_chrome_browser();
	    		//test_case.load_scanin("http://" + units.get(thread_num).get_ip() + "/PROG_VPD_ODC_scanin.html");
	    		//test_case.start_unit();
				//result = test_case.monitor_test();
				//System.out.println("Unit" + thread_num + "result =" + result);
				test_case.check_out_unit();
				//test_case.close_chrome_browser();
	  		
			} else if (this.name.equals("EM_TMS_SYSTEM")) {
				test_case.open_chrome_browser();	  	
	    		test_case.load_scanin("http://" + units.get(thread_num).get_ip() + "/SYSTEM_ODC_scanin.html");
	    		test_case.start_unit();
	    		result = test_case.monitor_test();
	    		System.out.println("Unit" + thread_num + "result =" + result);
				//test_case.check_out_unit();
				//test_case.retest_unit();
				//test_case.close_chrome_browser();
	  			
			} else if (this.name.equals("EM_TMS_SYS_RECONFIG")) {
				test_case.open_chrome_browser();	  	
	    		test_case.load_scanin("http://" + units.get(thread_num).get_ip() + "/SYSTEM_ODC_scanin.html");
	    		test_case.start_unit();
	    		result = test_case.monitor_test();
	    		System.out.println("Unit" + thread_num + "result =" + result);
	    		test_case.check_out_unit();
	    		test_case.close_chrome_browser();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String get_result() {
		return this.result;
	
	}

}