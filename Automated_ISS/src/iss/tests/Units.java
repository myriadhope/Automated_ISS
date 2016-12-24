package iss.tests;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Units {

	private int unit_num;
	private int total_units;
	private int window_width;
	private int window_height;
	private int window_x;
	private int window_y;
	
	private String serial;
	private String slot;
	private String ip;
	
	public Units() {
		serial = "";
		slot = "";
		unit_num = 0;
		total_units = 0;
		
		
	}
	
	public Units (String serial, String slot, String ip) {
		this.serial = serial;
		this.slot = slot;
		this.ip = ip;
		
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
	
	public void calculate_window_size() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double monitor_width = screenSize.getWidth();
		double monitor_height = screenSize.getHeight();
		if (this.total_units % 2 == 0) {
			switch(total_units) {
				case 2 :
					System.out.println("serial" + unit_num + "=" + serial);
					System.out.println("monitor_width" + unit_num + "=" + monitor_width);
					System.out.println("monitor_height" + unit_num + "=" + monitor_height);
					this.window_width = (int) monitor_width / 2;
					this.window_height = (int) monitor_height;
					this.window_x = this.window_width * this.unit_num;
					this.window_y = 0;
					
					System.out.println("window_width" + unit_num + "=" + this.window_width);
					System.out.println("window_height" + unit_num + "=" + this.window_height);
					System.out.println("window_x" + unit_num + "=" + this.window_x);
					System.out.println("window_y" + unit_num + "=" + this.window_y);
					break;
				
				case 4 :
					System.out.println("serial11" + unit_num + "=" + serial);
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
					System.out.println("serial12" + unit_num + "=" + serial);
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
					System.out.println("serial13" + unit_num + "=" + serial);
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
					System.out.println("serial14" + unit_num + "=" + serial);
					this.window_width = (int) monitor_width;
					this.window_height = (int) monitor_height;
					break;
				
				case 3 :
					System.out.println("serial15" + unit_num + "=" + serial);
					this.window_width = (int) monitor_width / 2;
					this.window_height = (int) monitor_height / 2;
					break;
				case 5 :
					System.out.println("serial16" + unit_num + "=" + serial);
					this.window_width = (int) monitor_width / 3;
					this.window_height = (int) monitor_height / 2;
					break;
				case 7 :
					System.out.println("serial17" + unit_num + "=" + serial);
					this.window_width = (int) monitor_width / 4;
					this.window_height = (int) monitor_height / 2;
					break;
				case 9 :
					System.out.println("serial18" + unit_num + "=" + serial);
					this.window_width = (int) monitor_width / 4;
					this.window_height = (int) monitor_height / 2;
					break;
			}
		}
		
	}
	
}

