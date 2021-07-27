package bank;

import java.util.List;

public class Tables 
{
	TableAccount accounts;
	TableEmployee employees;
	
	public Tables()
	{
		accounts=new TableAccount();
		employees=new TableEmployee();
	}
	
	public Account getAccount(int accNo)
	{
		return accounts.getAccount(accNo);
	}
	
	public void addAccount(Account acc)
	{
		accounts.addAccount(acc);
	}
	
	public void updateAccount(Account acc)
	{
		accounts.update(acc);
	}
	
	public boolean containsAccount(int accNo)
	{
		return accounts.containsAccount(accNo);
	}
	
	public void removeAccount(int accNo)
	{
		accounts.remove(accNo);
	}

	public List<Account> getBlockList()
	{
		return accounts.getBlockList();
	}
	
	public void printAccountDetails() 
	{
		accounts.printDetails();
	}
	
	public void printAttendeence()
	{
		employees.printAttendende();
	}
	public void printEmployeeDetails()
	{
		employees.printDetails();
	}
	public void addEmployee(Employee emp)
	{
		employees.addEmployee(emp);
	}
	
	public Employee getEmployee(String empId)
	{
		return employees.getEmployee(empId, this);
	}
	
	public Manager getManager()
	{
		return employees.getManager(this);
	}
	
	public void updateEmployee(Employee emp)
	{
		employees.update(emp);
	}
	public boolean containsEmployee(String empId)
	{
		return employees.containsEmployee(empId);
	}
	public void setStatus(int accNo,boolean status)
	{
		accounts.setStatus(accNo,status);
	}

	public void remove(String empId) 
	{
		employees.remove(empId);
		
	}
}
