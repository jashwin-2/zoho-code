package Controller;
import Service.Dao;
import Service.ManagerServices;
import View.ManagerView;
import model.*;

public class ManagerController extends EmployeeController
{
	ManagerView view;
	ManagerServices service;
	public ManagerController(Dao dao)
	{
		super(dao);
		view =new ManagerView(this); 
		service=new ManagerServices(dao);
	}

	public ManagerModel getManager()
	{
		return service.getManger();
	}

	public void menu(ManagerModel manager)
	{
		view.menu(manager);
	}

	public void createManager(String managerName, long mobileNo, int pin)
	{
		ManagerModel manager=service.createManager(managerName,mobileNo,pin);
		view.printDetails(manager);
	}

	public void logIn() 
	{
		view.LogIn();

	}


	public void searchEmployee(String id)
	{
		view.printDetails(service.findEmployee(id));
	}

	public void printAttendence()
	{
		view.pintAttendence(service.getEmployees());
	}

	public void addEmployee()
	{
		view.addEmployee();
	}

	public void createEmployee(String employeeName, long mobileNo, int pin) 
	{
		EmployeeModel emp=service.createEmployee(employeeName, mobileNo, pin);
		view.printDetails(emp);
	}

	public boolean containsEmployee(String empId) 
	{
		return service.containsEmployee(empId);
	}

	public void remove(String empId)
	{
		service.removemployee(empId);
	}

	public void printAccountsDetails() 
	{
		view.printAccountsDetails(service.getAccounts());
	}
	public void printEmployeeDetails()
	{
		view.printEmployeeDetails(service.getEmployees());
	}

	public void changeManager()
	{
		view.changeManager(getManager());
		view.printDetails(getManager());
		service.update(getManager());
	}

	public ManagerModel createManager() 
	{
		view.create();
		return getManager();
	}

	public void createEmployee()
	{
		view.addEmployee();
	}
}
