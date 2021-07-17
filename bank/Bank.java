package bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Bank 
{
	private String name;
	private Manager manager;
	private Map<String,Employee> employees=new HashMap<>();
	private Map<Integer,Account> accounts=new HashMap<>();
	private CustomerMethods customer;
	private ManagerMethods managerMethods;
	private EmployeeMethods employeeMethods;
	private OnlineTransactions onlineTransaction;

	public Bank(String name)
	{
		this.name=name;
		onlineTransaction=new OnlineTransactions(accounts);
		customer=new CustomerMethods(onlineTransaction);
		managerMethods=new ManagerMethods();
		employeeMethods=new EmployeeMethods();
		addManager();
		employees.put(manager.getEmpId(), manager);
		addEmployees();

	}

	public void createAccount()
	{
		Scanner sc=new Scanner(System.in);
		Account acc=customer.create();
		getAccounts().put(acc.getAccNo(),acc);
		System.out.println("\nAccount created succesfully");
	}

	public void bankMenu()
	{
		int ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			System.out.println("*************WELCOME TO "+name+" Bank************\n*****************  BANK MENU ************** ");
			System.out.println("Enter your choice \nLogin As\n1.Manager\n2.Employee \n3.Customer\nWant to create account press 4\n5.Exit ");
			System.out.println("*************************************** ");
			ch = Integer.parseInt(sc.nextLine());
			switch (ch) 
			{
			case 1:
			{
				managerMethods.LogIn(manager);
				break;
			}
			case 2:
			{
				Employee emp;
				System.out.println("Enter your Employee Id");
				String id=sc.nextLine();
				if(!employees.containsKey(id))
				{
					System.out.println("Id not found try Again");
					break;
				}
				emp=employees.get(id);
				employeeMethods.logIn(emp);
				break;
			}
			case 3:
			{
				Account acc;
				System.out.println("Enter your Account Number");
				int id=Integer.parseInt(sc.nextLine());
				if(!accounts.containsKey(id))
				{
					System.out.println("Id not found try Again");
					break;
				}
				acc=accounts.get(id);
				customer.login(acc);;
				break;
			}
			case 4:
			{
				createAccount();
				break;
			}
			case 5:
			{
				choice=false;
			}
			}
		}

	}

	public void addManager()
	{
		Manager manager=managerMethods.create(accounts, employees);
		this.manager=manager;
		employees.put(manager.getEmpId(), manager);
	}
	 
	public void addEmployees()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Do you want add employees 1-yes 0-no");
		if(Integer.parseInt(sc.nextLine())==1)
		{
			System.out.println("Enter the count of employees you want to add ");
			int count=Integer.parseInt(sc.nextLine());
			for(int i=1;i<=count;i++)
			{
				System.out.println("\nCreating Employee "+i+" profile");
				managerMethods.addEmployee(manager);
			}
		}
	}
	public Map<Integer, Account> getAccounts() {
		return accounts;
	}
}
