package bank;


import java.util.Scanner;


public class Bank 
{
	private String name;
	//private Manager manager;
	//private Map<String,Employee> employees=new HashMap<>();
	Tables tables;
	//private Map<Integer,Account> accounts=new HashMap<>();
	private OnlineTransactions onlineTransaction;

	public Bank(String name, Tables tables)
	{
		this.name=name;
		this.tables=tables;
		onlineTransaction=new OnlineTransactions(tables);
		addManager();
		//tables.addEmployee(manager);
		addEmployees();

	}

	public void createAccount()
	{
		Account acc=new CustomerMethods(onlineTransaction).create();
		getAccounts().addAccount(acc);
		System.out.println("\nAccount created succesfully");
	}

	public void bankMenu()
	{
		char ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			System.out.println("*************WELCOME TO "+name+" Bank************\n*****************  BANK MENU ************** ");
			System.out.println("Enter your choice \nLogin As\n1.Manager\n2.Employee \n3.Customer\nWant to create account press 4\n5.Exit ");
			System.out.println("*************************************** ");
			ch = sc.nextLine().charAt(0);
			switch (ch) 
			{
			case '1':
			{
				new ManagerMethods().LogIn(tables.getManager());
				break;
			}
			case '2':
			{
				Employee emp;
				System.out.println("Enter your Employee Id");
				String id=sc.nextLine();
				if(!tables.containsEmployee(id))
				{
					System.out.println("Id not found try Again");
					break;
				}
				emp=tables.getEmployee(id);
				new EmployeeMethods().logIn(emp);
				break;
			}
			case '3':
			{
				Account acc;
				System.out.println("Enter your Account Number");
				int id=Integer.parseInt(sc.nextLine());
				if(!tables.containsAccount(id))
				{
					System.out.println("Id not found try Again");
					break;
				}
				acc=tables.getAccount(id);
				acc=new CustomerMethods(onlineTransaction).login(acc);
				if(acc!=null)
					tables.updateAccount(acc);
				break;
			}
			case '4':
			{
				createAccount();
				break;
			}
			case '5':
			{
				choice=false;
				break;
			}
			default:
			{
				System.out.println("invalid input");
			}
			}
		}

	}

	public void addManager()
	{
		Manager manager=new ManagerMethods().create(tables);
		//employees.put(manager.getEmpId(), manager);
		tables.addEmployee(manager);
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
				new ManagerMethods().addEmployee(tables.getManager());
				
			}
		}
	}
	public Tables getAccounts() {
		return tables;
	}
}
