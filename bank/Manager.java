package bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Manager extends Employee
{
	private Map<String,Employee> employees=new HashMap<>();
	private Map<Integer,Account> accounts=new HashMap<>();
	public Manager(String name,long mobileNo,int pin, Map<Integer, Account> accounts, Map<String, Employee> employees)
	{
		super(name,mobileNo,pin,accounts);
		designation="Manager";
		this.employees=employees;
		this.accounts=accounts;
	}
	public Map<String,Employee> getEmployees() {
		return employees;
	}
	public Map<Integer, Account> getAccounts() {
		return accounts;
	}
	public void searchEmployee(String id) 
	{
		if(employees.containsKey(id))
			employees.get(id).printDetails();
		else
			System.out.println("Invalid Account number can not find the account");
		
	}

	
}

