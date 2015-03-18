import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;

public class Main {

	public static void main(String[] args) {
		Vector<RemoteDevice> devices = new Vector<RemoteDevice>();
		ArrayList<Student> studentList = new ArrayList<Student>();
		try {
			devices = BluetoothTest.discoverDevices();
			for(int i = 0; i < devices.size(); i++){
				RemoteDevice bt = devices.get(i);
				System.out.println(bt.getBluetoothAddress());

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			BufferedReader br = new BufferedReader(new FileReader("roster.csv")); // reader
			String line = null;
			while((line=br.readLine())!=null){			// If current line is not empty 
				String[] s = line.split(", ");			// split the line by using a comma as a delimiter
				Student indv = new Student(s[0],s[1]);	// create student using last name and first name
				for(int i=2; i<s.length ; i++){				// add devices to student based on number of IDs in roster
					indv.addDevice(s[i]);
				}
				studentList.add(indv);					//Add current student to Full list of students
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Student s: studentList){
			System.out.println(s.getFirstName()+","+s.getLastName()+","+s.getDevices());
		}

	}

}
