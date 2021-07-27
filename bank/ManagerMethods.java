package bank;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ManagerMethods 
{
	public void LogIn(Manager manager)
	{
		Scanner sc=new Scanner(System.in);
		int count1=3;
		do
		{
			System.out.println("Enter your pin");
			if(manager.checkPin(Integer.parseInt(sc.nextLine()))) {
				menu(manager);
				break;
			}
			else
				System.out.println("\nInvalid Pin Try again you have "+count1+" attempts");
			count1--;
		}	while(count1>=0);

	}
	public Manager create( Tables tables)
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
		return new Manager(managerName,mobileNo,pin,tables);
	}

	public void menu(Manager manager)
	{
		int ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			ch=0;
			try {
			System.out.println("\n************* MANAGER MENU ************** \nWELCOME "+manager.name);
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
				manager.setPresent(true);
				System.out.println("Checked In succesfully");
				manager.getTables().updateEmployee(manager);
				break;
			}
			case 2:
			{
				manager.setPresent(false);
				System.out.println("Checked Out succesfully");
				manager.getTables().updateEmployee(manager);
				break;
			}
			case 3:
			{
				manager.editProfile();
				manager.getTables().updateEmployee(manager);
				break;
			}
			case 4:
			{

				System.out.println("1.Search Employee\n2.Search accoount");
				int c=Integer.parseInt(sc.nextLine());
				if(c==2)
				{
					System.out.println("Enter the account no you want to search");
					manager.searchAccount(Integer.parseInt(sc.nextLine()));
				}
				else
				{
					System.out.println("Enter the Employee id you want to search");
					manager.searchEmployee(sc.nextLine());
				}
				break;
			}
			case 5:
			{
				manager.getTables().printAttendeence();
				break;
			}
			case 6:
			{
				addEmployee(manager);
				System.out.println("Crating Employee");
				break;
			}
			case 7:
			{
				System.out.println("Enter the Id of the employee of you want to remove");
				String empId=sc.nextLine();
				if(manager.getTables().containsEmployee(empId))
				{
					manager.getTables().remove(empId);
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
					manager.tables.printAccountDetails();
					break;
				}
				else
				{
					manager.tables.printEmployeeDetails();
					break;
				}

			}
			case 9:
			{
				manager.changeManager();
				manager.getTables().updateEmployee(manager);
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

	public void addEmployee(Manager manager) 
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
		Employee emp=new Employee(employeeName,mobileNo,pin,manager.getAccounts());
		emp.printDetails();
		System.out.println("Employee added Successfully");
		manager.getTables().addEmployee(emp);
		
	}
	
}	
