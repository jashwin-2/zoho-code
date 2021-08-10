package View;

import java.util.List;
import java.util.Scanner;

import Controller.ManagerController;
import model.AccountModel;
import model.EmployeeModel;
import model.ManagerModel;


public class ManagerView
{
	public ManagerController controller;
	public EmployeeView view;
	public ManagerView(ManagerController controller)
	{
		this.controller=controller;
		view=new EmployeeView(controller);
	}
	public void LogIn()
	{
		ManagerModel manager=controller.getManager();
		Scanner sc=new Scanner(System.in);
		int count1=3;
		do
		{
			System.out.println("Enter your pin");
			if(controller.checkPin(manager,Integer.parseInt(sc.nextLine()))) {
				controller.menu(manager);
				break;
			}
			else
				System.out.println("\nInvalid Pin Try again you have "+count1+" attempts");
			count1--;
		}	while(count1>=0);

	}
	public void create()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\nCratinng manager profile");
		System.out.println("Enter the Manager name");
		String managerName=sc.nextLine();
		System.out.println("Enter the Manager mobile no");
		long mobileNo=Long.parseLong(sc.nextLine());  
		System.out.println("Create a 4 digit pin");
		int pin;
		while(true)
		{
			pin=Integer.parseInt(sc.nextLine());
			if(String.valueOf(pin).length()<4)
			{
				System.out.println("Invalid input enter atleast 4 numbers");
				continue;
			}break;
		}
		controller.createManager(managerName,mobileNo,pin);
	}

	public void menu(ManagerModel manager)
	{
		int ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			ch=0;
			try {
				System.out.println("\n************* MANAGER MENU ************** \nWELCOME "+manager.getName());
				System.out.println("Enter your choice \n1.Check in\n2.Check out\n3.Edit profile\n4.Search Employee/Account\n5.Attendence\n6.Add employee\n7.Remove Employee\n8.Details of Employees/Accounts in the bank\n9.Change Manger\n10.exit");
				System.out.println("*************************************** ");
				ch = Integer.parseInt(sc.nextLine());}
			catch(Exception e)
			{
				System.out.println(e);
			}
			switch(ch)
			{
			case 1:
			{
				controller.checkIn(manager);
				System.out.println("Checked In succesfully");
				break;
			}
			case 2:
			{
				controller.checkOut(manager);
				System.out.println("Checked Out succesfully");
				break;
			}
			case 3:
			{
				controller.editProfile(manager);
				break;
			}
			case 4:
			{

				System.out.println("1.Search Employee\n2.Search accoount");
				int c=Integer.parseInt(sc.nextLine());
				if(c==2)
				{
					System.out.println("Enter the account no you want to search");
					view.printAccountDetails(controller.searchAccount(Integer.parseInt(sc.nextLine())));
				}
				else
				{
					System.out.println("Enter the Employee id you want to search");
					controller.searchEmployee(sc.nextLine());
				}
				break;
			}
			case 5:
			{
				controller.printAttendence();
				break;
			}
			case 6:
			{
				controller.addEmployee();
				break;
			}
			case 7:
			{
				System.out.println("Enter the Id of the employee of you want to remove");
				String empId=sc.nextLine();
				if(controller.containsEmployee(empId))
				{
					controller.remove(empId);
					System.out.println("Employee removed succesfully");
					break;
				}
				System.out.println("invalid input employee not found");
				break;
			}
			case 8:
			{
				System.out.println("1.Employees details\n2.Accounts Details");
				int c=Integer.parseInt(sc.nextLine());
				if(c==2)
				{
					controller.printAccountsDetails();
					break;
				}
				else
				{
					controller.printEmployeeDetails();
					break;
				}
			}
			case 9:
			{
				controller.changeManager();
			}
			case 10:
			{
				choice=false;
				break;
			}
			default :
				System.out.println("Invalid Input");
			}
		}
	}

	public void printDetails(EmployeeModel manager) 
	{
		System.out.println("\n------------------Employee Details-------------");
		System.out.println("Employee Id :"+manager.getEmpId());
		System.out.println("Name :"+manager.getName());
		System.out.println("Designation :"+manager.getDesignation());
		System.out.println("Mobile No :"+manager.getMobileNo());
		System.out.println("--------------------------------------------------");

	}
	public void pintAttendence(List<EmployeeModel> employees)
	{
		System.out.println("EmployeeModel Id	   Name		Status");
		for (EmployeeModel emp : employees)
		{

			System.out.print(emp.getEmpId()+"	           	"+emp.getName()+"		");
			if(emp.isPresent()==true)
				System.out.println("Present");
			else
				System.out.println("Absent");
		}

	}
	public void addEmployee() 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Employee name");
		String employeeName=sc.nextLine();
		System.out.println("Enter the Employee mobile no");
		long mobileNo=Long.parseLong(sc.nextLine());  
		System.out.println("Enter the Employee's minimum 4 digit pin");
		int pin;
		while(true)
		{
			pin=Integer.parseInt(sc.nextLine());
			if(String.valueOf(pin).length()<4)
			{
				System.out.println("Invalid input enter atleast 4 numbers");
				continue;
			}break;
		}
		controller.createEmployee(employeeName,mobileNo,pin);
		System.out.println("Employee added Successfully");

	}
	public void printAccountsDetails(List<AccountModel> accounts) 
	{
		float total=0;
		System.out.println("Name	Accountno	Account Type		balance");
		for(AccountModel acc : accounts)
		{
			System.out.println(acc.getAccHolderName()+"	"+acc.getAccNo()+" 		"+acc.getType()+"		"+acc.getAccBalance());
			total+=acc.getAccBalance();
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("					Total :"+total);

	}

	public void printEmployeeDetails(List<EmployeeModel> employees)
	{
		System.out.println("Name	Employee Id		Designation		Salary		Mobile No");
		for(EmployeeModel emp : employees)
		{
			System.out.println(emp.getName()+"	"+emp.getEmpId()+" 			"+emp.getDesignation()+" 		"+emp.getSalary()
			+"		"+emp.getMobileNo());
		}
	}
	public void changeManager(ManagerModel obj) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\nCratinng manager profile");
		System.out.println("Enter the Manager name");
		controller.setName(obj, sc.nextLine());
		System.out.println("Enter the Manager mobile no");
		controller.setMobileNo(obj,Long.parseLong(sc.nextLine()));  
		System.out.println("Create a 4 digit pin");
		int pin;
		while(true)
		{
			pin=Integer.parseInt(sc.nextLine());
			if(String.valueOf(pin).length()<4)
			{
				System.out.println("Invalid input enter atleast 4 numbers");
				continue;
			}
			controller.setPin(obj, pin);
			break;
		}
		System.out.println("Manager changed successfully");
	}

}
