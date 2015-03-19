import java.util.ArrayList;


public class Student {
	private String lastName = "";
	private String firstName = "";
	private ArrayList<String> devices = new ArrayList<String>();
	private boolean isPresent = false;
	
	public Student(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public Student(String lastName, String firstName, ArrayList<String> devices){
		this.lastName = lastName;
		this.firstName = firstName;
		this.devices = devices;
	}
	
	public void addDevice(String deviceID){
		this.devices.add(deviceID);
	}
	
	public boolean checkID(String deviceID){
		return this.devices.contains(deviceID);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public ArrayList<String> getDevices() {
		return devices;
	}

	public void setDevices(ArrayList<String> devices) {
		this.devices = devices;
	}
	
	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	
	public String getStudentName(){
		return this.firstName+" "+this.lastName;
	}
	
	
}

