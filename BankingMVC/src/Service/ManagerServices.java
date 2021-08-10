package Service;
import java.util.List;

import model.*;


public class ManagerServices extends EmployeeService
{
	public ManagerServices(Dao dao)
	{
		super(dao);
	}
	
	public ManagerModel getManger() 
	{
		return dao.getManager();
	}
	public ManagerModel createManager(String managerName, long mobileNo, int pin) 
	{
		ManagerModel manager=new ManagerModel(managerName,mobileNo,pin);
		dao.addEmployeeModel(manager);
		return manager;
	}
	
	
	public AccountModel findAccount(int no) 
	{
		return dao.getAccount(no);
	}
	
	public EmployeeModel findEmployee(String id) 
	{
		return dao.getEmployeeModel(id);
	}
	
	public List<EmployeeModel> getEmployees()
	{
		return dao.getEmployees();
	}

	public EmployeeModel createEmployee(String employeeName, long mobileNo, int pin) 
	{
		EmployeeModel emp=new EmployeeModel(employeeName,mobileNo,pin);
		dao.addEmployeeModel(emp);
		return emp;
	}

	public boolean containsEmployee(String empId)
	{
		return dao.containsEmployeeModel(empId);
	}

	public void removemployee(String empId)
	{
		dao.remove(empId);
		
	}

	public List<AccountModel> getAccounts() 
	{
		return dao.getAccounts();
		
	}
}
