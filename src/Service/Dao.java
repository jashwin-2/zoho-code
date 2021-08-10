package Service;
import DataBase.*;
import model.*;

import java.util.List;

public class Dao 
{
	AccountsTable accounts;
	EmployeesTable employees;
	static Dao instance;
	private Dao()
	{
		accounts=new AccountsTable();
		employees=new EmployeesTable();
	}

	public static Dao getInstance()
	{
		if(instance==null)
			return new Dao();
		else
			return instance;
	}
	public AccountModel getAccount(int accNo)
	{
		return accounts.getAccountModel(accNo);
	}
	
	public void addAccountModel(AccountModel acc)
	{
		accounts.addAccountModel(acc);
	}
	
	
	public void updateAccountModel(AccountModel acc)
	{
		accounts.update(acc);
	}
	
	
	public boolean containsAccountModel(int accNo)
	{
		return accounts.containsAccountModel(accNo);
	}
	
	public void removeAccountModel(int accNo)
	{
		accounts.remove(accNo);
	}

	public List<AccountModel> getBlockList()
	{
		return accounts.getBlockList();
	}
	
	public void printAccountModelDetails() 
	{
		accounts.printDetails();
	}
	
	public List<EmployeeModel> getEmployees()
	{
		return employees.getEmployeeList();
	}

	public void addEmployeeModel(EmployeeModel emp)
	{
		employees.addEmployeeModel(emp);
	}
	
	public EmployeeModel getEmployeeModel(String empId)
	{
		return employees.getEmployeeModel(empId);
	}
	
	public ManagerModel getManager()
	{
		return employees.getManager();
	}
	
	public void updateEmployeeModel(EmployeeModel emp)
	{
		employees.update(emp);
	}
	public boolean containsEmployeeModel(String empId)
	{
		return employees.containsEmployeeModel(empId);
	}
	public void setStatus(int accNo,boolean status)
	{
		accounts.setStatus(accNo,status);
	}

	public void remove(String empId) 
	{
		employees.remove(empId);
	}
	public List<AccountModel> getAccounts()
	{
		return accounts.getAccounts();
	}

	public String getAccountType(int id) {
	
		return accounts.getAccountModel(id).getType();
	}

	public boolean checkPin(int pin, int accNo) 
	{
		return accounts.getPin(accNo)==pin;
	}
}
