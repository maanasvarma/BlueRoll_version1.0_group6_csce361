import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class Class {
	
	String name = "";
	String classRosterPath = "";
	String semesterReportPath = "";
	
	public String getClassRosterPath() {
		return classRosterPath;
	}
	public void setClassRosterPath(String classRosterPath) {
		this.classRosterPath = classRosterPath;
	}
	public String getSemesterReportPath() {
		return semesterReportPath;
	}
	public void setSemesterReportPath(String semesterReportPath) {
		this.semesterReportPath = semesterReportPath;
	}
	
	public void addStudent(String lastName, String firstName, String[] devices){
		//Update the Class Roster, assumes roster is ordered lexicographically from lastName column
		String[] newStudent = new String[2+devices.length];
		for(int i = 0; i < devices.length+2; i++){
			if(i == 0){
				newStudent[i] = lastName;
			}else if(i == 1){
				newStudent[i] = firstName;
			}else{
				newStudent[i] = devices[i-2];
			}
		}
		
		try {
			CSVReader reader = new CSVReader(new FileReader(this.classRosterPath));
			List<String[]> allLines = reader.readAll();
			boolean marker = false;
			int i = 1;
			while(marker == false){
				if(allLines.get(i)[0].compareToIgnoreCase(lastName) >= 0){
					allLines.add(i, newStudent);
					marker = true;
				}
				i++;
			}
			reader.close();
			
			CSVWriter writer = new CSVWriter(new FileWriter(this.classRosterPath));
			writer.writeAll(allLines);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Now update the semesterReport, assumes report is ordered lexicographically from lastName column 
		try {
			CSVReader reader = new CSVReader(new FileReader(this.semesterReportPath));
			List<String[]> allLines = reader.readAll();
			boolean marker = false;
			int i = 1;
			String[] newStudentAgain = new String[2+allLines.get(0).length];
			while(marker == false){
				if(allLines.get(i)[0].compareToIgnoreCase(lastName) >= 0){
					allLines.add(i, newStudentAgain);
					marker = true;
				}
				i++;
			}
			reader.close();
			
			CSVWriter writer = new CSVWriter(new FileWriter(this.semesterReportPath));
			writer.writeAll(allLines);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addStudent(String lastName, String firstName, String device){
		String[] dummyArray = new String[1];
		dummyArray[0] = device;
		addStudent(lastName, firstName, dummyArray);
	}
	
	//Need to find ways of implementing these functions
	//What happens in the very rare case of students having the same last and first names?
	public void removeStudent(String lastName, String firstName){
		
	}
	
	public void addStudentDevice(){
		
	}
	
	public void removeStudentDevice(){
		
	}
	
	public void editSemesterReport(){
		
	}
	
}
