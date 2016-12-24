package iss.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.BufferedReader;

public class Parse_config {

		private String path;
		private ArrayList<Units> units = new ArrayList<Units>();
		public Units unit;
		
		public Parse_config (String file_path) {
			path = file_path;
		}
		
		public ArrayList<Units> Parse_content () throws IOException {
						
			try {
				FileReader input = new FileReader(path);
				BufferedReader input_file = new BufferedReader(input);
				String line;
				while ((line = input_file.readLine()) != null) {
					String unit_pattern = "^Unit\\d+:";
					String serial_pattern = "Serial=(.*)";
					String slot_pattern = "Slot=(.*)";
					String ip_pattern = "Tester_ip=(.*)";
					String end_pattern = "^End:";
					String op_id_pattern = "Operator_ID=(.*)";
					String part_Num_pattern = "Part_Number=(.*)";
					String Enclosure_11S_pattern= "Enclosure_11S=(.*)";
					String test_sequence_pattern= "Test_Sequence=(.*)";
					
					
										
					Pattern unit_p = Pattern.compile(unit_pattern);
					Pattern serial_p = Pattern.compile(serial_pattern);
					Pattern slot_p = Pattern.compile(slot_pattern);
					Pattern ip_p = Pattern.compile(ip_pattern);
					Pattern end_p = Pattern.compile(end_pattern);
					Pattern id_p = Pattern.compile(op_id_pattern);
					Pattern part_p = Pattern.compile(part_Num_pattern);
					Pattern encl_11s_p = Pattern.compile(Enclosure_11S_pattern);
					Pattern test_seq_p = Pattern.compile(test_sequence_pattern);
					
					Matcher unit_m = unit_p.matcher(line);
					Matcher serial_m = serial_p.matcher(line);
					Matcher slot_m = slot_p.matcher(line);
					Matcher ip_m = ip_p.matcher(line);	
					Matcher end_m = end_p.matcher(line);
					Matcher id_m = id_p.matcher(line);
					Matcher part_m = part_p.matcher(line);
					Matcher encl_11s_m = encl_11s_p.matcher(line);	
					Matcher test_seq_m = test_seq_p.matcher(line);
					
					System.out.println("line = " + line);
					
					if (unit_m.matches()) {
						System.out.println("Found Unit");
				        System.out.println("");	
						unit = new Units();
					} else if (serial_m.matches()) {
						System.out.println("Found value: " + serial_m.group(1));
				        System.out.println("");	
				        unit.add_serial(serial_m.group(1));
						
					} else if (slot_m.matches()) {						
						System.out.println("Found value: " + slot_m.group(1));
				        System.out.println("");
				        unit.add_slot(slot_m.group(1));
						
					} else if (ip_m.matches()) {						
						System.out.println("Found value: " + ip_m.group(1));
				        System.out.println("");
				        unit.add_ip(ip_m.group(1));
						
					} else if (id_m.matches()) {						
						System.out.println("Found value: " + id_m.group(1));
				        System.out.println("");
				        unit.add_id(id_m.group(1));
						
					} else if (part_m.matches()) {						
						System.out.println("Found value: " + part_m.group(1));
				        System.out.println("");
				        unit.add_part_num(part_m.group(1));
						
					} else if (encl_11s_m.matches()) {						
						System.out.println("Found value: " + encl_11s_m.group(1));
				        System.out.println("");
				        unit.add_encl_11s(encl_11s_m.group(1));
						
					} else if (test_seq_m.matches()) {						
						System.out.println("Found value: " + test_seq_m.group(1));
				        System.out.println("");
				        unit.add_test_seq(test_seq_m.group(1));
						
					} else if (end_m.matches()) {
						System.out.println("Found End");
				        System.out.println("");	
						units.add(unit);
					} else {
						System.out.println("NO MATCH");
				        System.out.println("");	
						
					}
					
					
				}
				input_file.close();
				return units;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return null;
			}
			
		}
		
		
}
