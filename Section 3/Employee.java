/*
 * File: Employee.java
 * ------------------
 * 
 */

public class Employee {

	public Employee(String name, int taxId) {
		employeeName = name;
		employeeID = taxId;
	}

	public String getName() {
		return employeeName;
	}
	
	public void setJobTitle(String title){
		jobTitle = title;
	}
	
	public String getJobTitle(){
		return jobTitle;
	}

	public int getTaxID() {
		return employeeID;
	}

	public void setSalary(int salary) {
		salaryEarned = salary;
	}

	public int getSalary() {
		return salaryEarned;
	}
	
	public void setActive(boolean flag) {
		active = flag;
	}

	public boolean getActive() {
		return active;
	}

	public String toString() {
		String str = jobTitle + ": " + employeeName + " (#" + employeeID + ")" + "; Salary -> £" + salaryEarned;
		if(!(active)){
			str += " --- [inactive]";
		}
		else{
			str += " --- [active]";
		}
		return str;
	}

	private String employeeName; 
	private int employeeID;      
	private int salaryEarned;    
	private boolean active;   
	private String jobTitle;

}