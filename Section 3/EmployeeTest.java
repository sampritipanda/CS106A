
import acm.program.*;

public class EmployeeTest extends ConsoleProgram{
	
	private static final long serialVersionUID = 1L;

	public void run() {
		Employee CEO = new Employee("Ebenezer Scrooge", 161803399);
		CEO.setSalary(1000);
		CEO.setActive(true);
		CEO.setJobTitle("CEO");
		
		Employee FormerPartner = new Employee("Jacob Marley", 271828182);
		FormerPartner.setSalary(0);
		FormerPartner.setActive(false);
		FormerPartner.setJobTitle("Former Partner");
		
		Employee Clerk = new Employee("Bob Cratchit", 314159265);
		Clerk.setSalary(25);
		Clerk.setActive(true);
		Clerk.setJobTitle("Clerk");
		
		println(CEO);
		println();
		println(FormerPartner);
		println();
		println(Clerk);
		println();
		
		Clerk.setSalary(Clerk.getSalary() * 2);
		println(Clerk.getName() + "'s new salary is £" + Clerk.getSalary());
	}
}
