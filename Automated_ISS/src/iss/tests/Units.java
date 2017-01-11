package iss.tests;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;

public class Units {

	private int unit_num;
	private int total_units;
	private int window_width;
	private int window_height;
	private int window_x;
	private int window_y;
	private int total_test_seq;
	
	
	private String serial;
	private String slot;
	private String ip;
	private String id;
	private String part_num;
	private String encl_11s;
	private String hba_loc;
	private List<String> popup_loc_hba;
	private List<String> popup_loc_bbu;
	private List<String> test_seq;
	
	public Units() {
		serial = "";
		slot = "";
		unit_num = 0;
		total_units = 0;
		id = "";
		part_num = "";
		encl_11s = "";
		total_test_seq = 0;
	}
	
	public void add_serial(String serial) {
		this.serial = serial;		
	}
	
	public void add_slot(String slot) {
		this.slot = slot;		
	}
	
	public void add_ip(String ip) {
		this.ip = ip;		
	}
	
	public void add_id(String id) {
		//operator id
		this.id = id;		
	}
	public void add_part_num(String part_num) {
		this.part_num = part_num;		
	}
	public void add_encl_11s(String encl_11s) {
		this.encl_11s = encl_11s;		
	}
	public void add_test_seq(String test_seq) {
		this.test_seq = Arrays.asList(test_seq.split(","));
		this.total_test_seq = this.test_seq.size();
	}
	public void add_hba_loc(String hba_loc) {
		this.hba_loc = hba_loc;
		
	}
	
	public void add_unit_num(int unit_number) {
		this.unit_num = unit_number;		
	}

	public void add_total_num(int total_units) {
		this.total_units = total_units;	
	}
	
	
	public String get_serial() {
			return this.serial;
	}
	
	public String get_slot() {
		return this.slot;
	}
	
	public String get_ip() {
		return this.ip;
	}

	public String get_id() {
		return this.id;
	}
	
	public String get_encl_11s() {
		return this.encl_11s;
	}

	public String get_part_num() {
		return this.part_num;
	}
	
	public String get_hba_loc() {
		return this.hba_loc;
	}
	public int get_window_width() {
		return this.window_width;
	}

	public int get_window_height() {
		return this.window_height;
	}
	
	public int get_window_x() {
		return this.window_x;
	}
	
	public int get_window_y() {
		return this.window_y;
	}
	
	public List<String> get_test_seq() {
		return this.test_seq;
	}
	
	public int get_total_test_seq() {
		return this.total_test_seq;
	}
	
	public void calculate_window_size() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double monitor_width = screenSize.getWidth();
		double monitor_height = screenSize.getHeight();
		if (this.total_units % 2 == 0) {
			switch(total_units) {
				case 0 :
					System.exit(0);
					break;
				case 2 :
					this.window_width = (int) monitor_width / 2;
					this.window_height = (int) monitor_height;
					this.window_x = this.window_width * this.unit_num;
					this.window_y = 0;
					break;
				
				case 4 :
					this.window_width = (int) monitor_width / 2;
					this.window_height = (int) monitor_height / 2;
					if (this.unit_num < 2) {
						this.window_x = this.window_width * this.unit_num;
						this.window_y = 0;
					} else {
						this.window_x = this.window_width * (this.unit_num - 2);
						this.window_y = this.window_height;
					}
					break;
					
				case 6 :
					this.window_width = (int) monitor_width / 3;
					this.window_height = (int) monitor_height / 2;
					if (this.unit_num < 3) {
						this.window_x = this.window_width * this.unit_num;
						this.window_y = 0;
					} else {
						this.window_x = this.window_width * (this.unit_num - 3);
						this.window_y = this.window_height;
					}
					break;
				case 8 :
					this.window_width = (int) monitor_width / 4;
					this.window_height = (int) monitor_height / 2;
					if (this.unit_num < 4) {
						this.window_x = this.window_width * this.unit_num;
						this.window_y = 0;
					} else {
						this.window_x = this.window_width * (this.unit_num - 4);
						this.window_y = this.window_height;
					}
					break;
			}
			
		} else {
			switch(total_units) {
				case 1 :
					this.window_width = (int) monitor_width;
					this.window_height = (int) monitor_height;
					this.window_x = 0;
					this.window_y = 0;
					break;
				
				case 3 :
					this.window_width = (int) monitor_width / 3;
					this.window_height = (int) monitor_height;
					this.window_x = this.window_width * this.unit_num;
					this.window_y = 0;
					break;
				case 5 :
					this.window_width = (int) monitor_width / 5;
					this.window_height = (int) monitor_height;
					this.window_x = this.window_width * this.unit_num;
					this.window_y = 0;
					break;
				case 7 :
					this.window_width = (int) monitor_width / 7;
					this.window_height = (int) monitor_height;
					this.window_x = this.window_width * this.unit_num;
					this.window_y = 0;
					break;
				case 9 :
					this.window_width = (int) monitor_width / 9;
					this.window_height = (int) monitor_height;
					this.window_x = this.window_width * this.unit_num;
					this.window_y = 0;
					break;
			}
		}
		
	}
	
}
